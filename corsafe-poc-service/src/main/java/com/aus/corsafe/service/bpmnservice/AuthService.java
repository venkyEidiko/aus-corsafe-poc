package com.aus.corsafe.service.bpmnservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class AuthService {

    private final WebClient.Builder webClient;

    @Value("${zeebe.client.cloud.clientId}")
    private String clientId;

    @Value("${webclient.base-url}")
    private String baseUrl;
    @Value("${zeebe.client.cloud.clientSecret}")
    private String clientSecret;

    public AuthService(WebClient.Builder webClient) {
        this.webClient = webClient;
    }



    public Mono<String> getAuthToken() {
        String body = "grant_type=client_credentials" +
                "&audience=tasklist.camunda.io" +
                "&client_id=" + clientId +
                "&client_secret=" + clientSecret +
                "&scope=tasklist.read";


        return webClient.baseUrl(baseUrl)
                .build().post()
                .uri("/oauth/token")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class);
    }

}
