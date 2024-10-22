package com.aus.corsafe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import com.aus.corsafe.config.ApplicationConfig;
@Configuration
public class WebclientConfig {


    //api tokenSaaS
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(ApplicationConfig.base_Url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + ApplicationConfig.tasklistAccessToken)
                .build();

    }
}
