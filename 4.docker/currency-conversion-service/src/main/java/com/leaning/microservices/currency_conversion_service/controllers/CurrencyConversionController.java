package com.leaning.microservices.currency_conversion_service.controllers;

import com.leaning.microservices.currency_conversion_service.entities.CurrencyConversion;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class CurrencyConversionController {

    private final Environment environment;
    private final CurrencyExchangeProxy proxy;
    private final RestTemplate restTemplate;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calulateCurrencyConversion(
            @PathVariable String from, @PathVariable String to,
            @PathVariable BigDecimal quantity){

        HashMap<String, String > uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversion> response = restTemplate.getForEntity("http://127.0.0.1:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class, uriVariables);

        String port = environment.getProperty("local.server.port");
        CurrencyConversion currencyConversion = response.getBody();
        if (currencyConversion == null){
            throw new RuntimeException("Currency Conversion Not Found");
        }
        return new CurrencyConversion(currencyConversion.getId(), from, to, quantity,
                currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()),
                port + " Rest template");
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(
            @PathVariable String from, @PathVariable String to,
            @PathVariable BigDecimal quantity){
        String port = environment.getProperty("local.server.port");

        CurrencyConversion  currencyConversion = proxy.getCurrencyConversion(from, to);

        return new CurrencyConversion(currencyConversion.getId(), from, to, quantity,
                currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()),
                port + " Feign");
    }
}
