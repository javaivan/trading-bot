package com.trading.entity;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public class Deal {
    @Id
    private String id;
    private Currency buyCurrency;
    private Currency sellCurrency;
    private BigDecimal buyMainCurrencyBefore;
    private BigDecimal buyMainCurrencyAfter;
    private BigDecimal sellMainCurrencyBefore;
    private BigDecimal sellMainCurrencyAfter;
    private BigDecimal buyDealCurrency;
    private BigDecimal sellDealCurrency;

    public Deal(Currency buyCurrency, BigDecimal buyMainCurrencyBefore, BigDecimal buyMainCurrencyAfter, BigDecimal buyDealCurrency) {
        this.buyCurrency = buyCurrency;
        this.buyMainCurrencyBefore = buyMainCurrencyBefore;
        this.buyMainCurrencyAfter = buyMainCurrencyAfter;
        this.buyDealCurrency = buyDealCurrency;
    }

    public void setSellDeal(Currency sellCurrency, BigDecimal sellMainCurrencyBefore, BigDecimal sellMainCurrencyAfter, BigDecimal sellDealCurrency) {
        this.sellCurrency = sellCurrency;
        this.sellMainCurrencyBefore = sellMainCurrencyBefore;
        this.sellMainCurrencyAfter = sellMainCurrencyAfter;
        this.sellDealCurrency = sellDealCurrency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Currency getBuyCurrency() {
        return buyCurrency;
    }

    public void setBuyCurrency(Currency buyCurrency) {
        this.buyCurrency = buyCurrency;
    }

    public Currency getSellCurrency() {
        return sellCurrency;
    }

    public void setSellCurrency(Currency sellCurrency) {
        this.sellCurrency = sellCurrency;
    }

    public BigDecimal getBuyMainCurrencyBefore() {
        return buyMainCurrencyBefore;
    }

    public void setBuyMainCurrencyBefore(BigDecimal buyMainCurrencyBefore) {
        this.buyMainCurrencyBefore = buyMainCurrencyBefore;
    }

    public BigDecimal getBuyMainCurrencyAfter() {
        return buyMainCurrencyAfter;
    }

    public void setBuyMainCurrencyAfter(BigDecimal buyMainCurrencyAfter) {
        this.buyMainCurrencyAfter = buyMainCurrencyAfter;
    }

    public BigDecimal getSellMainCurrencyBefore() {
        return sellMainCurrencyBefore;
    }

    public void setSellMainCurrencyBefore(BigDecimal sellMainCurrencyBefore) {
        this.sellMainCurrencyBefore = sellMainCurrencyBefore;
    }

    public BigDecimal getSellMainCurrencyAfter() {
        return sellMainCurrencyAfter;
    }

    public void setSellMainCurrencyAfter(BigDecimal sellMainCurrencyAfter) {
        this.sellMainCurrencyAfter = sellMainCurrencyAfter;
    }

    public BigDecimal getBuyDealCurrency() {
        return buyDealCurrency;
    }

    public void setBuyDealCurrency(BigDecimal buyDealCurrency) {
        this.buyDealCurrency = buyDealCurrency;
    }

    public BigDecimal getSellDealCurrency() {
        return sellDealCurrency;
    }

    public void setSellDealCurrency(BigDecimal sellDealCurrency) {
        this.sellDealCurrency = sellDealCurrency;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "id='" + id + '\'' +
                ", buyCurrency=" + buyCurrency +
                ", sellCurrency=" + sellCurrency +
                ", buyMainCurrencyBefore=" + buyMainCurrencyBefore +
                ", buyMainCurrencyAfter=" + buyMainCurrencyAfter +
                ", sellMainCurrencyBefore=" + sellMainCurrencyBefore +
                ", sellMainCurrencyAfter=" + sellMainCurrencyAfter +
                ", buyDealCurrency=" + buyDealCurrency +
                ", sellDealCurrency=" + sellDealCurrency +
                '}';
    }
}
