package com.example.microservices.currencyexchangeservice.repository;

import com.example.microservices.currencyexchangeservice.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Integer> {
    CurrencyExchange findByFromAndTo(String from, String to);
}
