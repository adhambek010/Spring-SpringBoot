package com.example.microservices.limitsservice.controller;

import com.example.microservices.limitsservice.bean.Limits;
import com.example.microservices.limitsservice.configuration.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LimitsController {

    private final Config config;

    @GetMapping("/limits")
    public Limits retieveLimits(){
        return new Limits(config.getMinimum(), config.getMaximum());
//        return new Limits(1, 1000);
    }
}
