package com.trading.repository;

import com.trading.entity.CurrencyHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CurrencyHistoryRepository extends MongoRepository<CurrencyHistory, String> {
    CurrencyHistory findByName(String name);
}