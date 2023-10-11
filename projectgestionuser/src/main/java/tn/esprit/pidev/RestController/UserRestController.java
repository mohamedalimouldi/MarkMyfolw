package tn.esprit.pidev.RestController;

import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.Repository.UserRepository;
import tn.esprit.pidev.Service.IUser;
import tn.esprit.pidev.Service.SMSService;
import tn.esprit.pidev.auth.EmailService;
import tn.esprit.pidev.auth.SMSSendRequest;
import tn.esprit.pidev.entity.User;
import tn.esprit.pidev.token.Token;
import tn.esprit.pidev.token.TokenRepository;
import javax.mail.MessagingException;
import java.util.List;
@RestController
@AllArgsConstructor
//@RequestMapping("/Admin")

public class UserRestController {

    private final  IUser iu;
    private final TokenRepository TR;
    private final UserRepository UR;
    private final EmailService emailService;
    private final SMSService SS;
    private final PasswordEncoder passwordEncoder;
   // @PreAuthorize("hasRole('USER')")
    @GetMapping("/AllUsers")
    public List<User> getAllUsers(){

        //System.out.println(UR.findDistinctByPhone("22345543").getPassword());
        return iu.getAllUsers();
    }
   // @PostMapping("/register")
   // public User registerNewUser(@RequestBody User user) {
   //     return iu.registerUser(user);
 //   }
    @GetMapping("/getUser/{username}")
public User getUserByUsername(@PathVariable String username )
    {
        return UR.findByUsername(username);
    }

    @PutMapping("/verify/{verificationToken}")
    public String activateAccount(@PathVariable String verificationToken) throws Exception {
        System.out.println(verificationToken.toString());
        //  Token t =TR.findDistinctByUserAndExpiredIsFalseAndRevokedIsFalse();
        //All Valid Tokens
        List<Token> LTs = TR.findAllValidToken();
        System.out.println(LTs.toString());
        System.out.println(LTs.size());
        Token t11 = null;
        for (var v : LTs) {
            if (v.token.equals(verificationToken)) {
                t11 = v;
            }
            // need to return an exception or whatever to see the invalid "Exception + return" not working 4me
        }
        System.out.println(t11.toString());
        //Token t11=TR.findTokenByToken(verificationToken); "All Tokens"
        //Here the question is it better to use all the Tokens or th Valid Ones ;)
        System.out.println(t11.expired + "/n" + t11.revoked);
        User u11 = t11.getUser();
        u11.setIsVerified(1);
        iu.updateUser(u11);
        //

        if (u11 != null) {
            String to = u11.getEmail();
            String subject = "Account Verified";
            try {
                emailService.sendEmailTemplateSuccessMsg(to, subject);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            return ("Congratulations " + u11.getUsername() + " Your account has been activated successfully");
        } else {
            return ("there was an error verifying your account, please make sure you have entered the right token and that the token hasn't expried");
        }
    }
//        List<Token> LTs = TR.findAllValidToken();
//        System.out.println(LTs.toString());
//        System.out.println(LTs.size());
//        Token t11 = null;
//        for (var v : LTs) {
//            if (v.token.equals(verificationToken)) {
//                t11 = v;
//            }
//            // need to return an exception or whatever to see the invalid "Exception + return" not working 4me
//        }
//        System.out.println(t11.toString());
//        //Token t11=TR.findTokenByToken(verificationToken); "All Tokens"
//        //Here the question is it better to use all the Tokens or th Valid Ones ;)
//        System.out.println(t11.expired + "/n" + t11.revoked);
//        User u11 = t11.getUser();
//
//        //



    @PutMapping("/resetbysms/{code}/{newpassword}")
    public ResponseEntity<String> resetBySms(@RequestBody SMSSendRequest sendRequest, @PathVariable("code") String code , @PathVariable("newpassword") String newpassword) {
        System.out.println(sendRequest.getPhone());
        User u12 = UR.findByPhone(sendRequest.getPhone());
        System.out.println(u12.toString());
        if (code.equals(u12.getCode()) & u12.getCode() != null) {
            u12.setPassword(passwordEncoder.encode(newpassword));
            u12.setCode(null);
            iu.updateUser(u12);
        }
            //here the exception or message if the code is not match to == yes approved
//        and the message whether u want it dynamic from the request body or static your choice " ;=) "
//            return SS.sendSMS(sendRequest.getPhone(), sendRequest.getMessage());
        return new ResponseEntity<String>(SS.sendSMS(sendRequest.getPhone(), "Am Saying that your password has changed successfully  "), HttpStatus.OK);
        }

        @DeleteMapping("/delete/{username}")
    public User deleteUser(@PathVariable("username") String username){
        return this.iu.deleteUser(username);
        }

}
