package com.example.springboot.learnspringboot.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix = "currency-service")
@Getter
@Setter
public class CurrencyServiceConfig {
    private String url;
    private String userName;
    private String key;
}
