package com.mj.search.common.util;

public class NumberUtil {

    public static int defaultIfNull(Integer defaultValue, Integer targetValue){
        if (targetValue == null){
            return defaultValue;
        }
        return targetValue;
    }
}
