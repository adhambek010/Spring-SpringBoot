package com.example.springboot.learnspringboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConfig {

    @Autowired
    private CurrencyServiceConfig config;

    @RequestMapping("/currency-config")
    public CurrencyServiceConfig retrieveAllServices(){
        return config;
    }
}
