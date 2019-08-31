package com.yangxp.ad.adsearch.index.interest;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ConcurrentHashSet;
import com.yangxp.ad.adsearch.index.IndexAware;
import com.yangxp.ad.adsearch.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link UnitItIndex}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/31 21:58
 * @Version 1.0
 */
@Slf4j
@Component
public class UnitItIndex implements IndexAware<String, Set<Long>> {

    private static Map<String, Set<Long>> itUnitMap; //通过标签 itTag 找到单元ID unitID
    private static Map<Long, Set<String>> unitItMap;

    static {

        itUnitMap = new ConcurrentHashMap<>();
        unitItMap = new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(String key) {
        return itUnitMap.get(key);
    }

    @Override
    public void add(String key, Set<Long> val) {

        log.info("unit it index before add : {}", unitItMap);

        Set<Long> unitIds = CommonUtils.getOrCreate(key, itUnitMap, ConcurrentHashSet::new);

        unitIds.addAll(val);

        for (Long unitId : val) {
            Set<String> its = CommonUtils.getOrCreate(unitId, unitItMap, ConcurrentHashSet::new);
            its.add(key);
        }
        log.info("unit it index after add : {}", unitItMap);
    }

    @Override
    public void update(String key, Set<Long> val) {

        log.error("不支持更新！");
    }

    @Override
    public void delete(String key, Set<Long> val) {
        log.info("unit it index before delete : {}", unitItMap);

        Set<Long> unitIds = CommonUtils.getOrCreate(key, itUnitMap, ConcurrentHashSet::new);
        unitIds.removeAll(val);

        for (Long unitId : val) {

            Set<String> itTagSet = CommonUtils.getOrCreate(unitId, unitItMap, ConcurrentHashSet::new);
            itTagSet.remove(key);
        }

        log.info("unit it index after delete  : {}", unitItMap);

    }

    public boolean match(Long unitId, List<String> itTags) {

        if (unitItMap.containsKey(unitId)
                && CollectionUtil.isNotEmpty(unitItMap.get(unitId))) {

            Set<String> unitKeywords = unitItMap.get(unitId);
            return CollectionUtils.isSubCollection(itTags, unitKeywords);
        }

        return false;
    }


}
