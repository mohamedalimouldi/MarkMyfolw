package arcticmonkeys.tn.advisorms.configs;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {

    private String openaiSecretKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                template.header("Content-Type", "application/json");
                template.header("Authorization", "Bearer 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" );
            }
        };
    }
}