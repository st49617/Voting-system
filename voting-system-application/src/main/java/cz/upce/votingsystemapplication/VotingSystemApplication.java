package cz.upce.votingsystemapplication;

import cz.upce.votingsystemapplication.config.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class VotingSystemApplication {

    
    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/api/voting/*");

        return registrationBean;
    }


    public static void main(String[] args) {
        SpringApplication.run(VotingSystemApplication.class, args);
    }

}
