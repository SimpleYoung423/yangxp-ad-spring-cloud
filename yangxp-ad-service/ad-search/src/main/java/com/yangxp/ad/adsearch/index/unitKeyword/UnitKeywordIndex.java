package com.yangxp.ad.adsearch.index.unitKeyword;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ConcurrentHashSet;
import cn.hutool.core.util.StrUtil;
import com.yangxp.ad.adsearch.index.IndexAware;
import com.yangxp.ad.adsearch.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link UnitKeywordIndex}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/31 21:27
 * @Version 1.0
 */
@Slf4j
@Component
public class UnitKeywordIndex implements IndexAware<String, Set<Long>> {

    private static Map<String, Set<Long>> keywordUnitMap;
    private static Map<Long, Set<String>> unitKeywordMap;

    static {
        keywordUnitMap = new ConcurrentHashMap<>();
        unitKeywordMap = new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(String key) {

        if (StrUtil.isEmpty(key)) {
            return Collections.emptySet();
        }
        Set<Long> result = keywordUnitMap.get(key);
        if (CollUtil.isEmpty(result)) {
            return Collections.emptySet();
        }
        return result;
    }

    @Override
    public void add(String key, Set<Long> val) {

        Set<Long> unitIdSet = CommonUtils.getOrCreate(key, keywordUnitMap, ConcurrentHashSet::new);

        unitIdSet.addAll(val);
        for (Long unitId : val) {

            Set<String> keywordSet = CommonUtils.getOrCreate(unitId, unitKeywordMap, ConcurrentHashSet::new);
            keywordSet.add(key);
        }
    }

    @Override
    public void update(String key, Set<Long> val) {

        log.error("不支持更新！");
    }

    @Override
    public void delete(String key, Set<Long> val) {

        log.info("unit keyword index,before delete :{}", unitKeywordMap);
        Set<Long> unitIds = CommonUtils.getOrCreate(key, keywordUnitMap, ConcurrentHashSet::new);
        unitIds.removeAll(val);

        for (Long unitId : val) {

            Set<String> keywordSet = CommonUtils.getOrCreate(unitId, unitKeywordMap, ConcurrentHashSet::new);
            keywordSet.remove(key);
        }

        log.info("unit keyword index,after delete: {}", unitKeywordMap);
    }

    public boolean match(Long unitId, List<String> keywords) {

        if (unitKeywordMap.containsKey(unitId)
                && CollectionUtil.isNotEmpty(unitKeywordMap.get(unitId))) {  //判断Map里面有Key为unitId的元素，且它的值不能为空

            Set<String> unitKeywords = unitKeywordMap.get(unitId);

            return CollectionUtils.isSubCollection(keywords, unitKeywords); //keywords为unitKeywords的子集时返回 true
        }

        return false;
    }

}
