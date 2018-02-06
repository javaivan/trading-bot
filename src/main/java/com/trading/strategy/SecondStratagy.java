package com.trading.strategy;

import com.trading.enums.TypeDeal;
import com.trading.entity.Currency;
import com.trading.util.BDUtil;
import com.trading.util.CurrencyUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SecondStratagy implements CoreStrategy {
    private Long analysisPeriod = 2L;
    private Currency currencyStopLoss;
    private BigDecimal stopLoss = new BigDecimal(0.6);
    private List<Currency> currencies = new LinkedList<>();
    private TypeDeal typeDeal = TypeDeal.PENDING;
    private boolean startAnalytics = false;
    private Currency lastCurrency = new Currency();
    private Currency sellCurrency = new Currency(new BigDecimal(9999999));
    private Currency buyCurrency = new Currency(new BigDecimal(0));
    private Currency minCurrency = new Currency(new BigDecimal(99999999));
    private Currency maxCurrency = new Currency(new BigDecimal(0));

    private List<Currency> perDay = new ArrayList<>();
    private List<BigDecimal> byDays = new ArrayList<>();

    @Override
    public void setAnalysisPeriod(Long analysisPeriod) {
        this.analysisPeriod = analysisPeriod;
    }

    @Override
    public void setStopLoss(Float stopLoss) {
        this.stopLoss = new BigDecimal(stopLoss);
    }

    @Override
    public void setTypeDeal(TypeDeal typeDeal) {
        this.typeDeal = typeDeal;
    }

    @Override
    public TypeDeal getDeal() {
        if(typeDeal.equals(TypeDeal.BUY)){
            if (CurrencyUtil.moreOf(this.buyCurrency, this.lastCurrency) && CurrencyUtil.equals(maxCurrency, lastCurrency)
                    || CurrencyUtil.moreOf(this.lastCurrency, this.currencyStopLoss)){
                updateMinPrice();
                //this.sellCurrency = new Currency(lastCurrency.getPrice().multiply(new BigDecimal(0.9)));
                this.typeDeal = TypeDeal.SELL;
                return TypeDeal.SELL;
            }
        } else {
            if(this.startAnalytics) {
                if (CurrencyUtil.equals(minCurrency, lastCurrency)){
                    if(byDays.size()>3){
                        BigDecimal price = byDays.get(byDays.size()-1);
                        price = price.add(byDays.get(byDays.size()-2));
                        price = price.add(byDays.get(byDays.size()-3));
                        price = price.divide(new BigDecimal(3), 2, BigDecimal.ROUND_HALF_UP);
                        if(BDUtil.moreOf(price,lastCurrency.getPrice())){
                            return TypeDeal.NEXT;
                        }
                    }
                    updateMaxPrice();
                    this.buyCurrency = lastCurrency;
                    this.currencyStopLoss = new Currency(this.buyCurrency.getPrice().multiply(stopLoss));
                    this.typeDeal = TypeDeal.BUY;
                    return TypeDeal.BUY;
                }
            }
        }
        return TypeDeal.NEXT;
    }


    @Override
    public void addCurrency(Currency currency) {
        addAverage(currency);
        boolean updateMaxPrice = false;
        boolean updateMinPrice = false;
        this.currencies.add(currency);
         if(currencies.get(0).getDate().isBefore(currency.getDate().minusHours(analysisPeriod))){
            this.startAnalytics = true;
            while(true){
                if(CurrencyUtil.equals(maxCurrency, this.currencies.get(0))) {
                    updateMaxPrice = true;
                }
                if(CurrencyUtil.equals(minCurrency, this.currencies.get(0))) {
                    updateMinPrice = true;
                }
                this.currencies.remove(0);
                if(!currencies.get(0).getDate().isBefore(currency.getDate().minusHours(analysisPeriod))){
                    break;
                }
            }
        }
        this.lastCurrency = currency;
        if(updateMinPrice){
            updateMinPrice();
        }
        if(updateMaxPrice){
            updateMaxPrice();
        }

        this.maxCurrency = CurrencyUtil.getMaxCurrency(this.maxCurrency, currency);
        this.minCurrency = CurrencyUtil.getMinCurrency(this.minCurrency, currency);
    }


    private void updateMaxPrice(){
        this.maxCurrency = CurrencyUtil.getMaxCurrency(this.currencies);
    }

    private void updateMinPrice(){
        this.minCurrency = CurrencyUtil.getMinCurrency(this.currencies);
    }

    private void addAverage(Currency currency){
        if(perDay.size() == 0){
            perDay.add(currency);
        }
        if(!perDay.get(0).getDate().toLocalDate().equals(currency.getDate().toLocalDate())){
            BigDecimal price = new BigDecimal(0);
            for (Currency c: perDay){
                price = price.add(c.getPrice());
            }
            byDays.add(price.divide(new BigDecimal(perDay.size()), 2, BigDecimal.ROUND_HALF_UP));
            perDay = new ArrayList<>();
        }
        perDay.add(currency);
    }
}
