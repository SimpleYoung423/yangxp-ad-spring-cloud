package com.yangxp.ad.adsearch.index.creative;

import cn.hutool.core.util.ObjectUtil;
import com.yangxp.ad.adsearch.index.IndexAware;
import com.yangxp.ad.adsearch.index.adplan.AdPlanObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link CreativeIndex}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/9/1 19:18
 * @Version 1.0
 */
@Slf4j
@Component
public class CreativeIndex implements IndexAware<Long,CreativeObject> {


    private static Map<Long,CreativeObject> objectMap;
    static {
        objectMap = new ConcurrentHashMap<>();
    }


    @Override
    public CreativeObject get(Long key) {
        return objectMap.get(key);
    }

    @Override
    public void add(Long key, CreativeObject val) {

        objectMap.put(key,val);
    }

    @Override
    public void update(Long key, CreativeObject val) {

        CreativeObject oldObject = objectMap.get(key);
        if (ObjectUtil.isNull(oldObject)){

            objectMap.put(key,val);
        }else {
            oldObject.update(val);
        }

    }

    @Override
    public void delete(Long key, CreativeObject val) {

        log.info("creative objectMap delete before  : {}",objectMap);
        objectMap.remove(key);

        log.info("creative plan objectMap delete after  : {}",objectMap);

    }
}
