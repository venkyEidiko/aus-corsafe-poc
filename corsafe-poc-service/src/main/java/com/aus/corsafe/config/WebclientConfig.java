package com.aus.corsafe.config;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebclientConfig {


//    @Bean
//    public WebClient webClient() {
//        return WebClient.builder()
//                .baseUrl(ApplicationConfig.BASE_URL)
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + ApplicationConfig.TASKLIST_ACCESS_TOKEN)
//                .build();
//    }
//    
    @Value("${webclient.base-url}")
    private String baseUrl;

  

    @Bean
    public WebClient webClient() {
        return WebClient
                .builder().baseUrl(ApplicationConfig.BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + ApplicationConfig.TASKLIST_ACCESS_TOKEN)

                .build();

    }
}
