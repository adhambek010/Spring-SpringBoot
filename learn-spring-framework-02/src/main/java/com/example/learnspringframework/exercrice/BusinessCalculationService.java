package com.example.learnspringframework.exercrice;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@ComponentScan
@Configuration
@Service
public class BusinessCalculationService {
    private DataService service;

    public BusinessCalculationService(DataService service) {
        this.service = service;
    }

    public int findMax(){
        return Arrays.stream(service.retrieveData()).max().orElse(0);
    }

}
