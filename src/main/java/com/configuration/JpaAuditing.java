package com.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditing {

    @Bean
    public AuditorAware<String> auditorProvider(){
        return new AuditorProviderImpl();
    }

    public static class AuditorProviderImpl implements AuditorAware<String>{

        @Override
        public Optional<String> getCurrentAuditor() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication==null) return Optional.of(null);
            return Optional.of(authentication.getName());
        }
    }
}
