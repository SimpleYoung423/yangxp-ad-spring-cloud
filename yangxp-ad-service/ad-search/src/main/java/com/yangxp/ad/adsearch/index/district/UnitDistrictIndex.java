package com.yangxp.ad.adsearch.index.district;

import cn.hutool.core.collection.ConcurrentHashSet;
import com.yangxp.ad.adsearch.index.IndexAware;
import com.yangxp.ad.adsearch.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link UnitDistrictIndex}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/9/1 10:45
 * @Version 1.0
 */
@Slf4j
@Component
public class UnitDistrictIndex implements IndexAware<String,Set<Long>> {

    private static Map<String, Set<Long>> districtUnitMap;
    private static Map<Long, Set<String>> unitDistrictMap;

    static {
        districtUnitMap = new ConcurrentHashMap<>();
        unitDistrictMap = new ConcurrentHashMap<>();
    }


    @Override
    public Set<Long> get(String key) {
        return districtUnitMap.get(key);
    }

    @Override
    public void add(String key, Set<Long> val) {

        log.info("unit district index before add : {}", unitDistrictMap);

        Set<Long> unitIds = CommonUtils.getOrCreate(key, districtUnitMap, ConcurrentHashSet::new);

        unitIds.addAll(val);

        for (Long unitId : val) {

            Set<String> districts = CommonUtils.getOrCreate(unitId, unitDistrictMap, ConcurrentHashSet::new);
            districts.add(key);
        }
        log.info("unit district index after add : {}", unitDistrictMap);
    }

    @Override
    public void update(String key, Set<Long> val) {

        log.error("不支持修改更新！");
    }

    @Override
    public void delete(String key, Set<Long> val) {
        log.info("unit district index before delete : {}", unitDistrictMap);
        Set<Long> unitIds = CommonUtils.getOrCreate(key, districtUnitMap, ConcurrentHashSet::new);

        unitIds.removeAll(val);

        for (Long unitId : val) {

            Set<String> districts = CommonUtils.getOrCreate(unitId, unitDistrictMap, ConcurrentHashSet::new);
            districts.remove(key);
        }

        log.info("unit district index after delete : {}", unitDistrictMap);
    }
}
