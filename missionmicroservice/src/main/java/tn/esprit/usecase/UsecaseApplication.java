package tn.esprit.usecase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;


@SpringBootApplication
@EnableFeignClients("tn.esprit.usecase")
@EnableDiscoveryClient
public class UsecaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsecaseApplication.class, args);
    }


}
