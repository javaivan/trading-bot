package com.trading.service;

import com.trading.entity.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CurrencyService {
    String saveOrUpdate(Currency currency);
    List<Currency> getAllCurrencies();
    void saveCurrencies(List<Currency> currencies);
    List<Currency> getByName(String name);
    Currency getByNameAndUnixTime(String name, Long unixTime);
    List<Currency> getByNameAndUnixTime(String name,  Long startUnixTime, Long finishUnixTim);
    Page<Currency> getByName(String name, Pageable pageableRequest);
}
