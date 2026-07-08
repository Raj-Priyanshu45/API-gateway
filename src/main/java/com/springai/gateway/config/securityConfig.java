package com.springai.gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@RequiredArgsConstructor
public class securityConfig {

    private final ReactiveJwtAuthenticationConverterAdapter reactiveJwtAuthenticationConverterAdapter;

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http, ReactiveJwtAuthenticationConverterAdapter jwtAuthenticationConverter) {

        return http
                .authorizeExchange(exchange -> {
                    exchange.pathMatchers("/actuator/**").permitAll()
                            .pathMatchers("/api/**").hasRole("USER")
                            .anyExchange().authenticated();
                })
                .oauth2ResourceServer(oauth ->
                        oauth.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)
                        ))
                .build();
    }
}
