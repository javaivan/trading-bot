package com.trading.util;

import com.trading.entity.Currency;

import java.math.BigDecimal;
import java.util.List;

public class CurrencyUtil {
    public static Currency getMaxCurrency(List<Currency> currencies) {
        Currency maxCurrency = new Currency(new BigDecimal(0));
        for (Currency currency : currencies) {
            if (moreOf(maxCurrency, currency)) {
                maxCurrency = currency;
            }
        }
        return maxCurrency;
    }

    public static Currency getMinCurrency(List<Currency> currencies) {
        Currency minCurrency = new Currency(new BigDecimal(999999999));
        for (Currency currency : currencies) {
            if (BDUtil.notZeroAndLessOf(minCurrency.getPrice(), currency.getPrice())) {
                minCurrency = currency;
            }
        }
        return minCurrency;
    }

    public static Currency getMaxCurrency(Currency one, Currency two) {
        if (BDUtil.moreOf(one.getPrice(), two.getPrice())) {
            return two;
        }
        return one;
    }

    public static Currency getMinCurrency(Currency one, Currency two) {
        if (BDUtil.notZeroAndLessOf(one.getPrice(), two.getPrice())) {
            return two;
        }
        return one;
    }

    public static boolean equals(Currency one, Currency two) {
        return one.getPrice().equals(two.getPrice());
    }

    public static boolean moreOf(Currency one, Currency two) {
        return BDUtil.moreOf(one.getPrice(), two.getPrice());
    }

    public static boolean lessOf(Currency one, Currency two) {
        return BDUtil.lessOf(one.getPrice(), two.getPrice());
    }
}
