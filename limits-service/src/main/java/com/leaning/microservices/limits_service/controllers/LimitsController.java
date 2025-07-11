package com.leaning.microservices.limits_service.controllers;

import com.leaning.microservices.limits_service.bean.Limits;
import com.leaning.microservices.limits_service.configuration.Configuration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LimitsController {
    private final Configuration config;

    @GetMapping("/limits")
    public Limits retrieveLimits(){
        return new Limits(config.getMinimum(), config.getMaximum());
    }
}
