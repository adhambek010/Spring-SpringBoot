package com.leaning.microservices.currency_exchange_service.controllers;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerController {
    private final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
    private static int cnt = 0;
//    @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
//    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
//    @RateLimiter(name = "sample-api")
    @Bulkhead(name = "sample-api")
    @GetMapping("/sample-api")
    public String sampleApi() {

        logger.info("Sample-Api is called {}", ++cnt);
/*
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/circuit-breaker", String.class);
        return forEntity.getBody();
*/
        return "Sample-Api is called";
    }

    public String hardcodedResponse(Throwable throwable) {
        logger.info("Hardcoded response is called");
        return "Hardcoded response is called";
    }
}
