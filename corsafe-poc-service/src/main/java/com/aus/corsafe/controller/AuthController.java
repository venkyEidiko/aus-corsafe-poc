package com.aus.corsafe.controller;

import com.aus.corsafe.service.bpmnservice.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/get-token")
    public Mono<ResponseEntity<String>> getToken(
            @RequestParam String clientId,
            @RequestParam String clientSecret) {
        return authService.getAuthToken(clientId, clientSecret)
                .map(token -> ResponseEntity.ok(token))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
