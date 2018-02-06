package com.trading.service.impl;

import com.trading.entity.CurrencyHistory;
import com.trading.repository.CurrencyHistoryRepository;
import com.trading.service.CurrencyHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class CurrencyHistoryServiceImpl implements CurrencyHistoryService {

    @Autowired
    private CurrencyHistoryRepository currencyHistoryRepository;


    @Override
    @Transactional
    public void save(CurrencyHistory currencyHistory) {
        CurrencyHistory old = getByName(currencyHistory.getName());
        if(Objects.nonNull(old)){
            old.setSize(currencyHistory.getSize());
            currencyHistoryRepository.save(old);
        } else {
            currencyHistoryRepository.save(currencyHistory);
        }
    }

    @Override
    public List<CurrencyHistory> getAll() {
        return currencyHistoryRepository.findAll();
    }

    @Override
    public CurrencyHistory getByName(String name) {
        return currencyHistoryRepository.findByName(name);
    }
}



