package arctic.tn.mohamedalirepo.service;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import freemarker.template.Configuration;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final Configuration freemarkerConfiguration;

    public EmailService(JavaMailSender javaMailSender, Configuration freemarkerConfiguration) {
        this.javaMailSender = javaMailSender;
        this.freemarkerConfiguration = freemarkerConfiguration;
    }

    public void sendSubscriptionExpiredEmail(String recipientName, String recipientEmail) throws MessagingException, IOException, TemplateException {
        // Prepare the email message using the Freemarker template
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setSubject("Your subscription is expiring");
        helper.setTo(recipientEmail);

        Map<String, Object> model = new HashMap<>();
        model.put("name", recipientName);

        Template template = freemarkerConfiguration.getTemplate("email-template.html");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        helper.setText(html, true);

        // Send the email
        javaMailSender.send(message);
    }

    public void sendMissionEmail(String recipientName, String recipientEmail) throws MessagingException, IOException, TemplateException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setSubject("test mission");
        helper.setTo(recipientEmail);

        Map<String, Object> model = new HashMap<>();
        model.put("url", recipientName);

        Template template = freemarkerConfiguration.getTemplate("missionEmail.html");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        helper.setText(html, true);

        // Send the email
        javaMailSender.send(message);

    }



}

