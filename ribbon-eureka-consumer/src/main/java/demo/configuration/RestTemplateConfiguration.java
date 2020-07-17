package demo.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    @LoadBalanced
    public RestTemplate balanceRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Primary
    public RestTemplate noBalanceRestTemplate() {
        return new RestTemplate();
    }
}
