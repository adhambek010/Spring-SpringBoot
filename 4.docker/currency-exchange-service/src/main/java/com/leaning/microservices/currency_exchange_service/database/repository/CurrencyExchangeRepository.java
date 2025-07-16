package com.leaning.microservices.currency_exchange_service.database.repository;

import com.leaning.microservices.currency_exchange_service.database.entities.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Integer> {
    CurrencyExchange findByFromAndTo(String from, String to);
}
