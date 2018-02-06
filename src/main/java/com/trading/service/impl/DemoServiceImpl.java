package com.trading.service.impl;

import com.trading.enums.TypeDeal;
import com.trading.entity.Currency;
import com.trading.entity.Deal;
import com.trading.service.DemoService;
import com.trading.strategy.CoreStrategy;
import com.trading.util.ReadFile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {
    MathContext mc = new MathContext(20);
    BigDecimal step = new BigDecimal(100);

    @Override
    public BigDecimal demoRun(CoreStrategy stratagy, BigDecimal dollar, Long analysisPeriod, Float stopLoss) {
        BigDecimal bitkoin = new BigDecimal(0);
        stratagy.setAnalysisPeriod(analysisPeriod);
        stratagy.setStopLoss(stopLoss);
        boolean bought = false;

        List<Deal> deals = new ArrayList<>();

        for (Currency currency : ReadFile.getCurrencies()) {
            stratagy.addCurrency(currency);
            TypeDeal typeDeal = stratagy.getDeal();

            if (typeDeal.equals(TypeDeal.SELL)) {
                BigDecimal before = dollar;
                dollar = commission(bitkoin.multiply(currency.getPrice()));
                bitkoin = new BigDecimal(0);
                bought = false;
                deals.get(deals.size() - 1).setSellDeal(currency, before, dollar, bitkoin);
                System.out.println("s " + dollar + " " + currency.getPrice() + " " + currency.getDate());
            }else if (typeDeal.equals(TypeDeal.BUY)) {
                BigDecimal before = dollar;
                System.out.println("b " + dollar + " " + currency.getPrice() + " " + currency.getDate());
                bitkoin = commission(dollar.divide(currency.getPrice(), 2, BigDecimal.ROUND_HALF_UP));
                dollar = new BigDecimal(0);
                deals.add(new Deal(currency,before, dollar, bitkoin));
                bought = true;
            }

        }

        if (bought) {
            BigDecimal before = dollar;
            Currency currency = ReadFile.getCurrencies().get(ReadFile.getCurrencies().size() - 1);
            dollar = dollar.add(commission(bitkoin.multiply(currency.getPrice())), mc);
            bitkoin = new BigDecimal(0);
            deals.get(deals.size() - 1).setSellDeal(currency, before, dollar, bitkoin);
        }

        return dollar;
    }

    @Override
    public List<Deal> getDeals(CoreStrategy stratagy, BigDecimal dollar, Long analysisPeriod, Float stopLoss){
        BigDecimal bitkoin = new BigDecimal(0);
        stratagy.setAnalysisPeriod(analysisPeriod);
        stratagy.setStopLoss(stopLoss);
        boolean bought = false;

        List<Deal> deals = new ArrayList<>();

        List<Currency> currencies =  ReadFile.getCurrencies();
        for (Currency currency : currencies) {
            stratagy.addCurrency(currency);
            TypeDeal typeDeal = stratagy.getDeal();

            if (typeDeal.equals(TypeDeal.SELL)) {
                BigDecimal before = dollar;
                dollar = commission(bitkoin.multiply(currency.getPrice()));
                bitkoin = new BigDecimal(0);
                bought = false;
                deals.get(deals.size() - 1).setSellDeal(currency, before, dollar, bitkoin);
            }else if (typeDeal.equals(TypeDeal.BUY)) {
                BigDecimal before = dollar;
                bitkoin = commission(dollar.divide(currency.getPrice(), 2, BigDecimal.ROUND_HALF_UP));
                dollar = new BigDecimal(0);
                deals.add(new Deal(currency, before, dollar, bitkoin));
                bought = true;
            }

        }
        if (bought) {
            BigDecimal before = dollar;
            Currency currency = currencies.get(currencies.size()-1);
            dollar = commission(bitkoin.multiply(currency.getPrice()));
            bitkoin = new BigDecimal(0);
            deals.get(deals.size() - 1).setSellDeal(currency, before, dollar, bitkoin);
        }

        return deals;
    }



    private static BigDecimal commission(BigDecimal bigDecimal) {
        return bigDecimal.multiply(new BigDecimal("0.9975"));
    }
}
