package com.trading.util;

import java.math.BigDecimal;

public class BDUtil {
    public static boolean moreOf(BigDecimal one, BigDecimal two){
       if(one.compareTo(two) < 0){
           return true;
       }
       return false;
    }
    public static boolean lessOf(BigDecimal one, BigDecimal two){
        if(one.compareTo(two) > 0){
            return true;
        }
        return false;
    }
    public static boolean isZero(BigDecimal bigDecimal){
        if(bigDecimal.compareTo(BigDecimal.ZERO) == 0 ){
            return true;
        }
        return false;
    }
    public static boolean notZero(BigDecimal bigDecimal){
        if(bigDecimal.compareTo(BigDecimal.ZERO) != 0 ){
            return true;
        }
        return false;
    }
    public static  boolean notZeroAndLessOf(BigDecimal one, BigDecimal two){
        if(notZero(two) && lessOf(one, two)){
            return true;
        }
        return false;
    }
}