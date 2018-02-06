package com.trading.service;

import com.trading.entity.Deal;
import com.trading.strategy.CoreStrategy;

import java.math.BigDecimal;
import java.util.List;

public interface DemoService {
    BigDecimal demoRun(CoreStrategy stratagy, BigDecimal dollar, Long analysisPeriod, Float stopLoss);
    List<Deal> getDeals(CoreStrategy stratagy, BigDecimal dollar, Long analysisPeriod, Float stopLoss);
}
