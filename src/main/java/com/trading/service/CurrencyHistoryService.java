package com.trading.service;

import com.trading.entity.CurrencyHistory;

import java.util.List;

public interface CurrencyHistoryService {
    void save(CurrencyHistory currencyHistory);
    List<CurrencyHistory> getAll();
    CurrencyHistory getByName(String name);
}
