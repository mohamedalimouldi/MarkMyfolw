package tn.esprit.pidev.auth;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.Config.JwtService;
import tn.esprit.pidev.Repository.RoleRepository;
import tn.esprit.pidev.Repository.UserRepository;
import tn.esprit.pidev.entity.Role;
import tn.esprit.pidev.entity.User;
import tn.esprit.pidev.token.Token;
import tn.esprit.pidev.token.TokenRepository;
import tn.esprit.pidev.token.TokenType;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.*;


@Service
@RequiredArgsConstructor

public class AuthenticationService {


    private  final UserRepository repository;

     private final TokenRepository tokenRepository;

    private final  PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final RoleRepository RR;

    private final EmailService emailService;

    public AuthenticationResponse register(User user) throws MessagingException {

//        try {
//            emailService.sendVerificationEmail("springapimail2023@gmail.com","verify","hi chika");
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = repository.save(user);
        //Sing up as a user
        Role role = RR.findRoleByRoleName("USER");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRoles(userRoles);
        String jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        emailService.sendEmail(user.getEmail(),"Added Successfully" ,
                "your login: "+user.getUsername()+"\n \n" +
                        "http://localhost:8000/userservice/verify/"+jwtToken);
        //Searching about the valid token with Advanced spring method
//        Token t11 =tokenRepository.findDistinctByUserAndExpiredIsFalseAndRevokedIsFalse(user);
//        System.out.println(t11);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
//    public static String generateCode() {
//        Random random = new Random();
//        int code = 100000 + random.nextInt(900000); // génère un nombre aléatoire entre 100000 et 999999
//        String SmsCode = Integer.toString(code);
//        return SmsCode;
//    }

//    public AuthenticationResponse registerAdmin(User user) throws MessagingException {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        User savedUser = repository.save(user);
//        //Sing up as a user
//        Role role = RR.findRoleByRoleName("ADMIN");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(role);
//        user.setRoles(userRoles);
//        String jwtToken = jwtService.generateToken(user);
//        saveUserToken(savedUser, jwtToken);
//        emailService.sendEmail(user.getEmail(),"Added Successfully" ,
//                "your login: "+user.getUsername()+"\n \n" +
//                        "http://localhost:8000/userservice/verify/"+jwtToken);
//        //Searching about the valid token with Advanced spring method
////        Token t11 =tokenRepository.findDistinctByUserAndExpiredIsFalseAndRevokedIsFalse(user);
////        System.out.println(t11);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }




    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Boolean usernameFound=serachValidbyUsername(request.getUsername());
        Boolean emailFound=serachValidbyEmail(request.getUsername());
        Boolean phoneFound=serachValidbyPhone(request.getUsername());
//        System.out.println(usernameFound);
//        System.out.println(emailFound);
//        System.out.println(phoneFound);
        User user=new User();
        if(usernameFound==true) {
            user = repository.findByUsername(request.getUsername());
            System.out.println(user.getEmail() + "this works");
            List<GrantedAuthority> authorities = getAuthorities(repository.findByUsername(request.getUsername()).getRoles());
        }else if (emailFound==true)
        {
            user=repository.findByEmail(request.getUsername());
            List<GrantedAuthority> authorities = getAuthorities(repository.findByEmail(request.getUsername()).getRoles());
        }else if (phoneFound==true)
        {
            user=repository.findByPhone(request.getUsername());
            List<GrantedAuthority> authorities = getAuthorities(repository.findByPhone(request.getUsername()).getRoles());
        }


        String  jwtToken = jwtService.generateToken(user);

      //  if (user.getIsVerified()==1)

        //revokeAllUserTokens();
        // revokeAllUserTokens(user);
        //here we need to revoke all user's token from token (tokenRepo method to set expired and revoked)
        //to provide (saved for me to handle it ) a new token authentification to use 4 the user
        // //revokeAllUserTokens(user);
        //revoke and expired setted successfully
        // //saveUserToken(user, jwtToken);
        //call of advanced method in authenticate
        //Integer nmb=tokenRepository.countAllByUser(user);
       // System.out.println(tokenRepository.findAllNoneValidToken().toString());
        //System.out.println(nmb);


// *********** Test ****
//        List<Token> LUT=tokenRepository.retrieveUserFromToken();
//        System.out.println(LUT);
        //;
//        AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//        Token t11=tokenRepository.retrieveUserFromToken(user.getIdUser());
//        System.out.println(t11.toString());
        //********************************************************************************
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
         // else throw NoscuhelementException("Not found");
    }

    private List<GrantedAuthority> getAuthorities(Set<Role> auths) {
        List<GrantedAuthority> list = new ArrayList<>();
        for (Role auth : auths) {
            list.add(new SimpleGrantedAuthority(auth.getRoleName()));
        }
        return list;
    }

    private void saveUserToken(User user, String jwtToken) {
        var token=Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
            System.out.println("Hi chika token saved");
            tokenRepository.save(token);
    }
    public void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user);
        System.out.println(validUserTokens.toString());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
    boolean serachValidbyEmail(String username)
    {
        User uv=repository.findByEmail(username);
        Boolean ok=false;
        if (uv!=null){
            ok=true;
            return ok;
        }
        return ok;
    }
    boolean serachValidbyPhone(String username)
    {
        User uv=repository.findByPhone(username);
        Boolean ok=false;
        if (uv!=null){
            ok=true;
            return ok;
        }
        return ok;
    }
    boolean serachValidbyUsername(String username)
    {
        User uv=repository.findByPhone(username);
        Boolean ok=false;
        if (uv!=null){
            ok=true;
            return ok;
        }
        return ok;
    }


    }



