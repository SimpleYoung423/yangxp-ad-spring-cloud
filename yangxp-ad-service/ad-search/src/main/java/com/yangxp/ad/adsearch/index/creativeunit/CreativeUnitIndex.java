package com.yangxp.ad.adsearch.index.creativeunit;

import com.yangxp.ad.adsearch.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * {@link CreativeUnitIndex}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/9/1 19:23
 * @Version 1.0
 */
@Slf4j
@Component
public class CreativeUnitIndex implements IndexAware<String, CreativeUnitObject> {

    private static Map<String, CreativeUnitObject> objectMap;
    private static Map<Long, Set<Long>> creativeUnitMap;

    private static Map<Long, Set<Long>> unitCreativeMap;

    static {

        objectMap = new ConcurrentHashMap<>();
        creativeUnitMap = new ConcurrentHashMap<>();
        unitCreativeMap = new ConcurrentHashMap<>();
    }

    @Override
    public CreativeUnitObject get(String key) {
        return objectMap.get(key);
    }

    @Override
    public void add(String key, CreativeUnitObject val) {

        log.info(" before add : {}", objectMap);

        objectMap.put(key, val);

        Set<Long> unitSet = creativeUnitMap.get(val.getAdId());

        if (CollectionUtils.isEmpty(unitSet)) {

            unitSet = new ConcurrentSkipListSet<>();

            creativeUnitMap.put(val.getUnitId(), unitSet);

        }
        unitSet.add(val.getUnitId());
        Set<Long> creativeSet = unitCreativeMap.get(val.getUnitId());
        if (CollectionUtils.isEmpty(creativeSet)) {

            creativeSet = new ConcurrentSkipListSet<>();
            unitCreativeMap.put(val.getUnitId(), creativeSet);
        }
        creativeSet.add(val.getAdId());
        log.info(" after add : {}", objectMap);
    }

    @Override
    public void update(String key, CreativeUnitObject val) {
        log.error("不支持修改更新！");
    }

    @Override
    public void delete(String key, CreativeUnitObject val) {

        log.info("delete before : {}", objectMap);
        objectMap.remove(key);

        Set<Long> unitSet = creativeUnitMap.get(val.getAdId());

        if (CollectionUtils.isNotEmpty(unitSet)) {

            unitSet.remove(val.getUnitId());
        }
        Set<Long> creativeSet = creativeUnitMap.get(val.getUnitId());

        if (CollectionUtils.isNotEmpty(creativeSet)) {

            unitSet.remove(val.getAdId());
        }


        log.info("delete after : {}", objectMap);
    }
}
