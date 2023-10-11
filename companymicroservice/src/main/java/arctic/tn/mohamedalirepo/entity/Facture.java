package arctic.tn.mohamedalirepo.entity;

import lombok.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.context.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.persistence.Entity;
import java.util.Date;
import com.itextpdf.html2pdf.HtmlConverter;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Facture {
    private Long id;
    private String entrepriseName;
    private String codefixcale;
    private String email;
    private float prix;
    private Date achatDate;
    private Date experDate;
    public Facture(Long id, String entrepriseName, String codefixcale, String email, float prix, Date achatDate, Date experDate) {
        this.id = id;
        this.entrepriseName = entrepriseName;
        this.codefixcale = codefixcale;
        this.email = email;
        this.prix = prix;
        this.achatDate = achatDate;
        this.experDate = experDate;
    }
    public void generatePdfInvoice() throws IOException {
        // Create a Thymeleaf template engine
        TemplateEngine templateEngine = new SpringTemplateEngine();

        // Create a context object and populate it with the dynamic data
        Context context = new Context();
        context.setVariable("entrepriseName", entrepriseName);
        context.setVariable("codefixcale", codefixcale);
        context.setVariable("email", email);
        context.setVariable("prix", prix);
        context.setVariable("achatDate", achatDate);
        context.setVariable("experDate", experDate);

        // Process the template with the context to generate the final HTML code
        String html = templateEngine.process("invoice", context);

        // Convert the HTML code to a PDF file using iText library
        HtmlConverter.convertToPdf(html, new FileOutputStream(new File("invoice.pdf")));
    }
}
