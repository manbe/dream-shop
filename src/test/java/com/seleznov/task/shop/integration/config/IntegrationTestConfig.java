package com.seleznov.task.shop.integration.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author illcko
 */
@Configuration
@EnableAutoConfiguration
public class IntegrationTestConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
