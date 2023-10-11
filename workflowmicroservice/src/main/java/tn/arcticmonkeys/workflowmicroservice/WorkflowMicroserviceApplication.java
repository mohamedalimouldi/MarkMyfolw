package tn.arcticmonkeys.workflowmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
@EnableFeignClients("tn.arcticmonkeys.workflowmicroservice")
@EnableDiscoveryClient
@EnableScheduling
public class WorkflowMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkflowMicroserviceApplication.class, args);
    }

}
