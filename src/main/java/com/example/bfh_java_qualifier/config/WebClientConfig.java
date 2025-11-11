package com.example.bfh_java_qualifier.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(
            WebClient.Builder builder,
            @Value("${bfh.baseUrl:https://bfhldevapigw.healthrx.co.in}") String baseUrl) {
        return builder.baseUrl(baseUrl).build();
    }
}
