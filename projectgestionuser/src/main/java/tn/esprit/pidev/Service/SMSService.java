package tn.esprit.pidev.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Random;

@Service
@Slf4j
public class SMSService {
    @Value("${twilio.account_sid}")
    String ACCOUNT_SID;
    @Value("${twilio.auth_token}")
    String AUTH_TOKEN;
    @Value("${twilio.sender_number}")
    String SMS_NUMBER;

    @PostConstruct
    private void setup()
    {
       // log.info("SID : "+ ACCOUNT_SID);
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
    }
    public String sendSMS(String smsnumber,String msg)
    {
        Message message=Message.creator(
                new PhoneNumber(smsnumber),
                new PhoneNumber(SMS_NUMBER),
                msg).create();
        return message.getStatus().toString();
    }
    public static String generateCode() {

        StringBuilder code = new StringBuilder(8);
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 4; i++) {
            int index = (int)(Math.random() * chars.length());
            code.append(chars.charAt(index));
        }
        return code.toString();

    }
}
