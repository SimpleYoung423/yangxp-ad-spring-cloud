package com.yangxp.ad.adsearch.index;

/**
 * {@link IndexAware}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/31 19:16
 * @Version 1.0
 */

public interface IndexAware<K, V> {

    V get(K key);

    void add(K key, V val);

    void update(K key, V val);

    void delete(K key, V val);

}
