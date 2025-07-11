package com.leaning.microservices.currency_conversion_service.controllers;

import com.leaning.microservices.currency_conversion_service.database.entities.CurrencyConversion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion getCurrency(@PathVariable String from,
                                          @PathVariable String to,
                                          @PathVariable BigDecimal quantity) {
        return new CurrencyConversion();
    }
}
