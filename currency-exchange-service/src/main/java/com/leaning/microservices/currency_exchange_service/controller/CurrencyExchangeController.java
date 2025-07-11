package com.leaning.microservices.currency_exchange_service.controller;

import com.leaning.microservices.currency_exchange_service.database.entities.CurrencyExchange;
import com.leaning.microservices.currency_exchange_service.database.repository.CurrencyExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {
    private final CurrencyExchangeRepository repository;
    private final Environment environment;

    @GetMapping("/currency-exchange/form/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
        CurrencyExchange optional = repository.findByFromAndTo(from, to);
        if(optional==null){
            throw new RuntimeException("Currency Exchange not found");
        }
        else{
            String port = environment.getProperty("local.server.port");
            optional.setEnvironment(port);
            repository.save(optional);
            return optional;
        }

    }
}
