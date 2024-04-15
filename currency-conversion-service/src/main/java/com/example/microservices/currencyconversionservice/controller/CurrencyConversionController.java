package com.example.microservices.currencyconversionservice.controller;

import com.example.microservices.currencyconversionservice.CurrencyConversion;
import com.example.microservices.currencyconversionservice.proxy.CurrencyExchangeProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    private final CurrencyExchangeProxy proxy;

    public CurrencyConversionController(CurrencyExchangeProxy proxy) {
        this.proxy = proxy;
    }

    @GetMapping("/currency-conversion/form/{from}/to/{to}/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to,
                                                          @PathVariable BigDecimal quantity){

        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversion> response = new RestTemplate().getForEntity
                ("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class, uriVariables);
        var conversion = response.getBody();
        assert conversion != null;
        return new CurrencyConversion(conversion.getId(), from, to, quantity, conversion.getConversionMultiple(),
                quantity.multiply(conversion.getConversionMultiple()),
                conversion.getEnvironment());

    }

    @GetMapping("/currency-conversion-feign/form/{from}/to/{to}/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to,
                                                          @PathVariable BigDecimal quantity){
        var conversion = proxy.retrieveExchangeValue(from, to);
        assert conversion != null;
        return new CurrencyConversion(conversion.getId(), from, to, quantity, conversion.getConversionMultiple(),
                quantity.multiply(conversion.getConversionMultiple()),
                conversion.getEnvironment() + " "+"feign");

    }

}
