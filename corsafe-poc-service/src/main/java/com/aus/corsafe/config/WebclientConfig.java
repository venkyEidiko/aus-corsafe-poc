package com.aus.corsafe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebclientConfig {


    //api tokenSaaS
    @Bean
    public WebClient.Builder webClient() {
        return WebClient.builder()
                .baseUrl(ApplicationConfig.BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + ApplicationConfig.TASKLIST_ACCESS_TOKEN);

    }
}
