package com.example.ataaspringbootangular.config;

import com.example.ataaspringbootangular.entity.Utilisateur;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;


@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class ApplicationAuditAware {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return ()->{
            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                return Optional.of(auth.getName());
            }
            return Optional.of("Unknown");
        };
    }
}
