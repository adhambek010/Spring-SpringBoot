package uz.developer.magistratura_ishi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class CreatedOrModifiedUser {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of(((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }

}
