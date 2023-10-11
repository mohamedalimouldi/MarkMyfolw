package tn.esprit.pidev.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tn.esprit.pidev.Repository.UserRepository;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

private final UserRepository repository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> repository.findByUsername(username);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        //if the @Bean "line 23" of userdetailsService "that returns a user found by username "
        //returns a user= null
        // then call for load USER by email or by phone
        //this is the provider who has the power to put the user by "username,mail,phone"
        // TO DO                              <     !     >
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public PasswordPolicyBean passwordPolicyBean() {
//        PasswordPolicyBean bean = new PasswordPolicyBean();
//        bean.setMinLength(8);
//        bean.setRequiresUppercase(true);
//        bean.setRequiresSpecialCharacter(true);
//        return bean;
//    }


}
