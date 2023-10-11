package tn.esprit.pidev.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.Config.LogoutService;
import tn.esprit.pidev.entity.User;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthenticationService service;
    private final LogoutService LS;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User request
    ) throws MessagingException {
        return ResponseEntity.ok(service.register(request));
    }

//    @PostMapping("/registerAdmin")
//    public ResponseEntity<AuthenticationResponse> registerAdmin(
//            @RequestBody User request
//    ) throws MessagingException {
//        return ResponseEntity.ok(service.registerAdmin(request));
//    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PutMapping("/logout")
    public String logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication){
       // service.revokeAllUserTokens();
        LS.logout(request,response,authentication);
        return "u ve been kicked out";
    }


}
