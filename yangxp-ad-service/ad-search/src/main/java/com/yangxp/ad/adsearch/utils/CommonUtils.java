package com.yangxp.ad.adsearch.utils;

import java.util.Map;
import java.util.function.Supplier;

/**
 * {@link CommonUtils}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/31 21:36
 * @Version 1.0
 */

public class CommonUtils {

    public static <K,V> V getOrCreate(K key, Map<K,V> map, Supplier<V> factory){

        return map.computeIfAbsent(key,k -> factory.get());
    }

    public static String stringConcat(String... args){

        StringBuilder result = new StringBuilder();

        for (String arg : args) {

            result.append(arg);
            result.append("-");

        }
        result.deleteCharAt(result.length() -1);
        return result.toString();

    }

}
