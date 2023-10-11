package arctic.tn.mohamedalirepo.restcontroller;

import arctic.tn.mohamedalirepo.entity.Subscription;
import arctic.tn.mohamedalirepo.service.Interfacesubsription;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;

import org.jfree.chart.JFreeChart;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/subscription")
public class SubscriptionController  {
    Interfacesubsription interfacesubsription;

    @PostMapping("/add")
    public Subscription addnewsubscription(@RequestBody Subscription subscription ) {
        return interfacesubsription.addnewsubscription(subscription);
    }

    @PutMapping("/update")
    public Subscription editsubscription(@RequestBody Subscription subscription) {
        return interfacesubsription.editsubscription(subscription);
    }

    @GetMapping("/getbyid/{id}")
    public Subscription findbyIdsubscription(@PathVariable("id") Long id) {
        return interfacesubsription.findbyIdsubscription(id);
    }
    @GetMapping("/getAll")
    public List<Subscription> findAllsubscription() {
        return interfacesubsription.findAllsubscription();
    }

    @DeleteMapping("delete/{id}")
    public void deleteSubscription(@PathVariable("id") Long id) {
        interfacesubsription.deleteSubscription(id);
    }
   
    @GetMapping(value="/graph",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> generationgrphe(
            @RequestParam("dateDebut") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateDebut,
            @RequestParam("dateFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFin
    ){
        return interfacesubsription.genererGrapheAbonnementsParEntreprise(dateDebut, dateFin);


    }
    @PostMapping("/addandassignedskieur/{identreprise}")
    public Subscription addInscriptionandAssignied(@RequestBody Subscription inscri, @PathVariable("identreprise")
    Long id) throws TemplateException, IOException {

        return interfacesubsription.addInscriptionAndAsignedtoEntreprise(inscri, id);
    }


    @PostMapping("/testmail")
    public String sendMissionNotif(@RequestBody Map<String, String> requestMap) throws ParseException, MessagingException, TemplateException, IOException {
        String url = requestMap.get("url");
        String recipient = requestMap.get("recipient");
        return  interfacesubsription.sendMissionEmail(url,recipient);
    }
    @GetMapping("/earnings-by-month")
    public List<Map<String, Object>> getEarningsByMonth(@RequestParam("year") int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        Date start = java.sql.Date.valueOf(startDate);
        Date end = java.sql.Date.valueOf(endDate);
        return interfacesubsription.getEarningsByYearSemesterAndMonth(start, end);
    }
}
