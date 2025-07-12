package com.leaning.microservices.currency_conversion_service;

import com.leaning.microservices.currency_conversion_service.database.entities.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "currency-exchange", url = "127.0.0.1:8000")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/form/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String from,
                                          @PathVariable String to);
}
