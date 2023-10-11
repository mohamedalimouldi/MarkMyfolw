package arctic.tn.mohamedalirepo.service;

import arctic.tn.mohamedalirepo.entity.Entreprise;
import arctic.tn.mohamedalirepo.entity.Subscription;
import arctic.tn.mohamedalirepo.repository.EntrepriseRepo;
import arctic.tn.mohamedalirepo.repository.SubscriptionRepo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.chart.ui.VerticalAlignment;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.itextpdf.html2pdf.HtmlConverter;
import java.awt.*;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import org.jfree.chart.title.TextTitle;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;

@Service
@AllArgsConstructor

public class Subscription_service implements Interfacesubsription {

    private EmailService emailService;
    JavaMailSender javaMailSender;
    SubscriptionRepo subscriptionRepo;
    EntrepriseRepo entrepriseRepo;
     Configuration freemarkerConfiguration;

    @Override
    public Subscription addnewsubscription(Subscription subscription) {
        subscription.setDateDebut(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        String etat= subscription.getTypeSubscription().toString();

        if(etat.equals("annuel")){
            subscription.setDateFin(Date.from(LocalDate.now().plusYears(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        } else if (etat.equals("semestriel")) {
            subscription.setDateFin(Date.from(LocalDate.now().plusMonths(6).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        } else if (etat.equals("mensuel")) {
            subscription.setDateFin(Date.from(LocalDate.now().plusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        return subscriptionRepo.save(subscription);
    }

    @Override
    public Subscription editsubscription(Subscription subscription) {
        return subscriptionRepo.save(subscription);
    }

    @Override
    public Subscription findbyIdsubscription(Long id) {
        Subscription subscription = subscriptionRepo.findById(id).orElse(null);
        Date endDate = subscription.getDateFin();
        LocalDate localEndDate = new java.sql.Date(endDate.getTime()).toLocalDate();
        LocalDate today = LocalDate.now();
        if (today.equals(localEndDate)) {
            System.out.println("Yes");
        }
        return subscriptionRepo.findById(id).orElse(null);
    }

    @Override
    public List<Subscription> findAllsubscription() {
        return subscriptionRepo.findAll();
    }

    @Override
    public void deleteSubscription(Long id) {
        subscriptionRepo.deleteById(id);
    }



    @Override
    public List<Map<String, Object>> genererGrapheAbonnementsParEntreprise(Date dateDebut, Date datefin) {
        double totale = 0.0;
        List<Object[]> list = subscriptionRepo.getAbonnementsParEntreprise(dateDebut, datefin);
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Object[] obj : list) {
            Map<String, Object> resultMap = new HashMap<>();
            String name = ((String) obj[0]).trim(); // Trim the string to remove any extra characters
            resultMap.put("name", name); // Add the name key
            resultMap.put("numberSub", obj[1]); // Add the numberSub key
            resultMap.put("abonPrix", obj[2]); // Add the abonPrix key
            resultList.add(resultMap);
            totale += (Double) obj[2]; // Add the 3rd element (prixtotale) to the running total
        }
        Map<String, Object> totaleMap = new HashMap<>();
        // Add the name key for the total object
        totaleMap.put("abonPrixTotal", totale); // Add a separate key for the total prixAbon
        resultList.add(totaleMap);
        return resultList;
    }
    @Override
    public Subscription addInscriptionAndAsignedtoEntreprise(Subscription inscription, Long idCompany) throws IOException, TemplateException {
        Entreprise entreprise = entrepriseRepo.findById(idCompany).orElse(null);
        inscription.setEntreprise(entreprise);
        Subscription subscription= subscriptionRepo.save(inscription);

       if (entreprise != null) {
           Map<String, Object> data = new HashMap<>();
           data.put("id", subscription.getId());
           data.put("entrepriseName", entreprise.getName());
           data.put("codefixcale", entreprise.getCodefiscal());
           data.put("email", entreprise.getEmail());
           data.put("prix", subscription.getPrixAbon());
           data.put("achatDate", subscription.getDateDebut().toString());
           data.put("experDate", subscription.getDateFin().toString());

           Template template = freemarkerConfiguration.getTemplate("invoice.html");
           String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, data);

           // Create a PDF document from the HTML string
           File file = new File("facture.pdf");
           FileOutputStream outputStream = new FileOutputStream(file);
           HtmlConverter.convertToPdf(html, outputStream);
           outputStream.close();
        }
        return subscription;

    }
    @Override
    @Scheduled(fixedRate = 86400000)
    public Set<Subscription> notifateCompany() throws ParseException, MessagingException, TemplateException, IOException {
        String localDate = LocalDate.now().plusWeeks(2).toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(localDate);

        Set<Subscription> subscriptions = subscriptionRepo.findByDateFin(date);
        System.out.println(LocalDate.now().plusWeeks(2));
        for (Subscription s : subscriptions) {
            if (s.getEntreprise() != null) {
                emailService.sendSubscriptionExpiredEmail(s.getEntreprise().getName(), s.getEntreprise().getEmail());
            }
        }
        return subscriptions;
    }
    @Override
    public Entreprise findbyidsub(Long id) {
        Subscription subscription = subscriptionRepo.findById(id).orElse(null);
        Entreprise entreprise = subscription.getEntreprise();
        return  entreprise;
    }
    public String sendMissionEmail(String url,String to) throws ParseException, MessagingException, TemplateException, IOException {
        emailService.sendMissionEmail(url,to);
        return "test";
    }
    @Override
    public List<Map<String, Object>> getEarningsByYearSemesterAndMonth(Date startDate, Date endDate) {
        List<Object[]> list = subscriptionRepo.getEarningsByYearSemesterAndMonth(startDate, endDate);
        List<Map<String, Object>> resultList = new ArrayList<>();
        for(Object[] obj: list){
            Map<String, Object> resultMap = new HashMap<>();

            resultMap.put("year", obj[0]); // Add the name key
            resultMap.put("months", obj[1]); // Add the numberSub key
            resultMap.put("month", obj[2]); // Add the abonPrix key
            resultMap.put("price", obj[3]);
            resultList.add(resultMap);
        }
        return resultList;
    }

}
