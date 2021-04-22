package com.alvorada.tec.hrpayroll.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    // Com esta implementação estamos implementando o padrão de projeto Singleton. Para ter a disposição um objeto
    // RestTemplate para poder injetar em outros serviços
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
