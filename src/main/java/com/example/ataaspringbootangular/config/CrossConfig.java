package com.example.ataaspringbootangular.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
@Configuration
@ComponentScan("com.example.ataaspringbootangular")
@RequiredArgsConstructor
public class CrossConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList(
                HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,
                HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,
                HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD,
                HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS,
                HttpHeaders.ORIGIN,
                HttpHeaders.CACHE_CONTROL,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.AUTHORIZATION
        ));
        configuration.setAllowedMethods(Arrays.asList("POST", "PUT", "GET", "DELETE", "PATCH"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
