package com.springai.gateway.config;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;

@Configuration
public class keycloakConfig {

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {

        //first make converter
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        //extract the roles as map and then make it in the format of SimpleGrantedAuthority

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
                    Map<String,Object> roles = jwt.getClaimAsMap("realm_access");

                    if(roles == null || roles.get("roles") == null) {
                        return List.of();
                    }

                    //get the roles in a list

                    List<String> role = (List<String>) roles.get("roles");

                    return role.stream()
                            .map(rol -> new SimpleGrantedAuthority("ROLE_"+rol))
                            .collect(Collectors.toList());
        }
        );

        return converter;
    }

    @Bean
    //take the converter make it suitable for reactive programming using reactive jwt Authentication Converter Adapter
    public ReactiveJwtAuthenticationConverterAdapter reactiveJwtAuthenticationConverter(JwtAuthenticationConverter jwtAuthenticationConverter) {
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }


}
