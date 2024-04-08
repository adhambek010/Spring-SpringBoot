package com.example.security.springsecurityjwt.config;

import com.example.security.springsecurityjwt.auditing.ApplicationAuditAware;
import com.example.security.springsecurityjwt.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepository repository;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> repository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found. Please check credentials")

        );
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return  authProvider;
    }

    @Bean
    public AuditorAware<Integer> auditorAware(){
        return new ApplicationAuditAware();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
        try {
            return configuration.getAuthenticationManager();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}