package com.yangxp.ad.adsearch.index.adplan;

import cn.hutool.core.util.ObjectUtil;
import com.yangxp.ad.adsearch.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link AdPLanIndex}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/31 20:37
 * @Version 1.0
 */
@Slf4j
@Component
public class AdPLanIndex implements IndexAware<Long,AdPlanObject> {

    private static Map<Long,AdPlanObject> objectMap;

    static {

        objectMap = new ConcurrentHashMap<>();
    }

    @Override
    public AdPlanObject get(Long key) {
        return objectMap.get(key);
    }

    @Override
    public void add(Long key, AdPlanObject val) {

        log.info("ad plan objectMap before : {}",objectMap);

        objectMap.put(key,val);

        log.info("ad plan objectMap after : {}",objectMap);
    }

    @Override
    public void update(Long key, AdPlanObject val) {


        AdPlanObject oldObject = objectMap.get(key);
        if (ObjectUtil.isNull(oldObject)){

            objectMap.put(key,val);
        }else {
            oldObject.update(val);
        }



    }

    @Override
    public void delete(Long key, AdPlanObject val) {

        log.info("ad plan objectMap delete before  : {}",objectMap);
        objectMap.remove(key);

        log.info("ad plan objectMap delete after  : {}",objectMap);

    }
}
