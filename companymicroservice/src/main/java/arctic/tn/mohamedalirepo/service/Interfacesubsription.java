package arctic.tn.mohamedalirepo.service;

import arctic.tn.mohamedalirepo.entity.Entreprise;
import arctic.tn.mohamedalirepo.entity.Subscription;
import freemarker.template.TemplateException;
import org.jfree.chart.JFreeChart;
import org.springframework.data.repository.query.Param;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Interfacesubsription {
     Subscription addnewsubscription(Subscription subscription);
     Subscription editsubscription(Subscription subscription);
     Subscription findbyIdsubscription(Long id);
     List<Subscription> findAllsubscription();
     void deleteSubscription(Long id);

     List<Map<String, Object>>  genererGrapheAbonnementsParEntreprise(Date dateDebut, Date datefin) ;
     Subscription addInscriptionAndAsignedtoEntreprise(Subscription inscription, Long idCompany) throws IOException, TemplateException;
     Set<Subscription> notifateCompany() throws ParseException, MessagingException, TemplateException, IOException;
     Entreprise findbyidsub(Long id);

     public String sendMissionEmail(String url,String to) throws ParseException, MessagingException, TemplateException, IOException;
     List<Map<String, Object>> getEarningsByYearSemesterAndMonth(Date startDate, Date endDate);
}
