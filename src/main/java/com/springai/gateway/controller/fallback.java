package com.springai.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class fallback {

    @GetMapping("/fallback")
    public Mono<ResponseEntity<?>> fallbackUri(){
        return Mono.just(
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("API UNAVAILABLE")
        );
    }
}
