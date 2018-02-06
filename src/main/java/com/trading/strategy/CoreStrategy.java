package com.trading.strategy;

import com.trading.enums.TypeDeal;
import com.trading.entity.Currency;

public interface CoreStrategy {
    void setAnalysisPeriod(Long analysisPeriod);
    void setStopLoss(Float stopLoss);
    void addCurrency(Currency currency);
    void setTypeDeal(TypeDeal typeDeal);
    TypeDeal getDeal();
}
