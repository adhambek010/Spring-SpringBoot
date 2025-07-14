package com.leaning.microservices.currency_conversion_service.controllers;

import com.leaning.microservices.currency_conversion_service.entities.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="currency-exchange", url = "127.0.0.1:8000")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion getCurrencyConversion(@PathVariable String from, @PathVariable String to);
}
