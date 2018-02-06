package com.trading.repository;

import com.trading.entity.Currency;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Currency, String> {
    List<Currency> findByName(String name);
}