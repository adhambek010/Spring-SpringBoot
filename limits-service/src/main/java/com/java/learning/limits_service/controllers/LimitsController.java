package com.java.learning.limits_service.controllers;

import com.java.learning.limits_service.beans.Limits;
import com.java.learning.limits_service.comfiguration.Configuration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LimitsController {
    private final Configuration config;

    @GetMapping("limits")
    public Limits retrieveLimits(){
        return new Limits(config.getMinimum(), config.getMaximum());
    }
}
