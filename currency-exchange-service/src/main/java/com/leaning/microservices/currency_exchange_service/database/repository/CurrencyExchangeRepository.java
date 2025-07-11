package com.leaning.microservices.currency_exchange_service.database.repository;

import com.leaning.microservices.currency_exchange_service.database.entities.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository
        extends JpaRepository<CurrencyExchange, Long> {

    CurrencyExchange findByFromAndTo(String from, String to);
}
