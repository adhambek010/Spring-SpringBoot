package com.java.learning.limits_service.comfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("limits-service")
public class Config {
    private int minimum;
    private int maximum;
}
