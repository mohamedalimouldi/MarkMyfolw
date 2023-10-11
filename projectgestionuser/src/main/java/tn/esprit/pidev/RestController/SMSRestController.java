package tn.esprit.pidev.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.Repository.UserRepository;
import tn.esprit.pidev.Service.IUser;
import tn.esprit.pidev.Service.SMSService;
import tn.esprit.pidev.Service.UserService;
import tn.esprit.pidev.auth.SMSSendRequest;
import tn.esprit.pidev.entity.User;
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/SMS")
public class SMSRestController {
    private final SMSService SS;
    private final UserRepository UR;
    private final IUser iu;
    @PutMapping("/sendsms")
    public String sendSms(@RequestBody SMSSendRequest sendRequest)
    {
        String codeGenerated=SS.generateCode();
        System.err.println(sendRequest.getPhone());
        User u1=UR.findByPhone(sendRequest.getPhone());
        System.out.println(u1.getPhone());
        u1.setCode(codeGenerated);
        iu.updateUser(u1);
        return SS.sendSMS(sendRequest.getPhone(),"your code is :"+codeGenerated);
    }

    }


