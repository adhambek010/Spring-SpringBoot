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

    private final Environment environment;
    private final CurrencyExchangeRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
        String port = environment.getProperty("local.server.port");

        CurrencyExchange exchange = repository.findByFromAndTo(from, to);
        if(exchange==null){
            throw new RuntimeException("Unable to find data for " +  from + " to " + to);
        }
        exchange.setEnvironment(port);

        return repository.save(exchange);
    }
    @GetMapping("/test")
    public String test() {
        return "Currency Conversion Service is up!";
    }

}
