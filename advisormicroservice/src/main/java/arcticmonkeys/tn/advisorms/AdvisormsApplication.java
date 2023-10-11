package arcticmonkeys.tn.advisorms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
@EnableFeignClients("arcticmonkeys.tn.advisorms")
@EnableDiscoveryClient
@EnableScheduling
public class AdvisormsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvisormsApplication.class, args);
	}

}
