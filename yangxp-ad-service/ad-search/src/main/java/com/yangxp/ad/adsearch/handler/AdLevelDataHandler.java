package com.yangxp.ad.adsearch.handler;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.yangxp.ad.adcommon.dump.table.*;
import com.yangxp.ad.adsearch.client.vo.AdPlan;
import com.yangxp.ad.adsearch.index.DataTable;
import com.yangxp.ad.adsearch.index.IndexAware;
import com.yangxp.ad.adsearch.index.adplan.AdPLanIndex;
import com.yangxp.ad.adsearch.index.adplan.AdPlanObject;
import com.yangxp.ad.adsearch.index.adunit.AdUnitIndex;
import com.yangxp.ad.adsearch.index.adunit.AdUnitObject;
import com.yangxp.ad.adsearch.index.creative.CreativeIndex;
import com.yangxp.ad.adsearch.index.creative.CreativeObject;
import com.yangxp.ad.adsearch.index.creativeunit.CreativeUnitIndex;
import com.yangxp.ad.adsearch.index.creativeunit.CreativeUnitObject;
import com.yangxp.ad.adsearch.index.district.UnitDistrictIndex;
import com.yangxp.ad.adsearch.index.interest.UnitItIndex;
import com.yangxp.ad.adsearch.index.unitKeyword.UnitKeywordIndex;
import com.yangxp.ad.adsearch.mysql.constant.OpType;
import com.yangxp.ad.adsearch.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * {@link AdLevelDataHandler}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/9/2 17:57
 * @Version 1.0
 */
@Slf4j
public class AdLevelDataHandler {

    public static void handleLevel2(AdPlanTable planTable, OpType type) {
        AdPlanObject planObject = new AdPlanObject(
                planTable.getId(),
                planTable.getUserId(),
                planTable.getPlanStatus(),
                planTable.getStartTime(),
                planTable.getEndTime()
        );
        handleBinlogEvent(DataTable.of(AdPLanIndex.class), planObject.getPlanId(), planObject, type);

    }


    public static void handleLevel2(AdCreativeTable creativeTable, OpType type) {

        CreativeObject creativeObject = new CreativeObject(
                creativeTable.getAdId(),
                creativeTable.getName(),
                creativeTable.getType(),
                creativeTable.getMaterialType(),
                creativeTable.getHeight(),
                creativeTable.getWidth(),
                creativeTable.getAuditStatus(),
                creativeTable.getAdUrl()
        );
        handleBinlogEvent(DataTable.of(CreativeIndex.class), creativeObject.getAdId(), creativeObject, type);

    }


    public static void handleLevel3(AdUnitTable unitTable, OpType type) {

        AdPlanObject adPlanObject = DataTable.of(AdPLanIndex.class).get(unitTable.getPlanId());

        if (ObjectUtil.isNull(adPlanObject)) {
            log.error("handle level 3 not found ad plan object! , planId : {}", unitTable.getPlanId());
            return;
        }
        AdUnitObject adUnitObject = new AdUnitObject(unitTable.getUnitId(), unitTable.getUnitStatus(), unitTable.getPositionType(), unitTable.getPlanId(), adPlanObject);

        handleBinlogEvent(DataTable.of(AdUnitIndex.class), unitTable.getUnitId(), adUnitObject, type);

    }

    public static void handleLevel3(AdCreativeUnitTable creativeUnitTable, OpType type) {

        if (type == OpType.UPDATE) {
            log.error("ad creative unit index 不支持更新！");
            return;
        }

        AdUnitObject unitObject = DataTable.of(AdUnitIndex.class).get(creativeUnitTable.getUnitId());
        CreativeObject creativeObject = DataTable.of(CreativeIndex.class).get(creativeUnitTable.getAdId());

        if (ObjectUtil.isNull(unitObject) || ObjectUtil.isNull(creativeObject)) {
            log.error("ad creative unit table index error : {}", JSON.toJSON(creativeUnitTable));
            return;
        }

        CreativeUnitObject creativeUnitObject = new CreativeUnitObject(creativeUnitTable.getAdId(), creativeUnitTable.getUnitId());
        handleBinlogEvent(DataTable.of(
                CreativeUnitIndex.class),
                CommonUtils.stringConcat(creativeUnitObject.getAdId().toString(), creativeUnitObject.getUnitId().toString()),
                creativeUnitObject,
                type
        );


    }

    public static void handleLevel4(AdUnitDistrictTable unitDistrictTable, OpType type) {

        if (type == OpType.UPDATE){
            log.error("district index is not support update!");
            return;
        }
        AdUnitObject unitObject = DataTable.of(AdUnitIndex.class).get(unitDistrictTable.getUnitId());
        if (ObjectUtil.isNull(unitObject)){
            log.error("ad unit district table index error: {}",unitDistrictTable.getUnitId());
        }
        String key = CommonUtils.stringConcat(unitDistrictTable.getProvince(),unitDistrictTable.getCity());

        Set<Long > value = new HashSet<>(Collections.singleton(unitDistrictTable.getUnitId()));

        handleBinlogEvent(DataTable.of(UnitDistrictIndex.class),key,value,type);

    }

    public static void handleLevel4(AdUnitItTable unitItTable,OpType type){

        if ( type== OpType.UPDATE){
            log.error(" unit it index is not support update!");
            return;
        }
        AdUnitObject unitObject = DataTable.of(AdUnitIndex.class).get(unitItTable.getUnitId());
        if (ObjectUtil.isNull(unitObject)){
            log.error("ad unit district table index error: {}", unitItTable.getUnitId());
            return;
        }
        Set<Long > value = new HashSet<>(Collections.singleton(unitItTable.getUnitId()));

        handleBinlogEvent(DataTable.of(UnitItIndex.class),unitItTable.getItTag(),value,type);

    }


    public static void handleLevel4(AdUnitKeywordTable keywordTable,OpType type){
        if ( type== OpType.UPDATE){
            log.error(" unit keyword index is not support update!");
            return;
        }

        AdUnitObject unitObject = DataTable.of(AdUnitIndex.class).get(keywordTable.getUnitId());
        if (ObjectUtil.isNull(unitObject)){
            log.error("ad unit district table index error: {}", keywordTable.getUnitId());
            return;
        }

        Set<Long>  value = new HashSet<>(Collections.singleton(keywordTable.getUnitId()));

        handleBinlogEvent(DataTable.of(UnitKeywordIndex.class),keywordTable.getKeyword(),value,type);

    }



    private static <K, V> void handleBinlogEvent(IndexAware<K, V> index,
                                                 K key,
                                                 V val,
                                                 OpType type) {

        switch (type) {
            case ADD:
                index.add(key, val);
                break;
            case DELETE:
                index.delete(key, val);
                break;
            case UPDATE:
                index.update(key, val);
                break;
            default:
                break;
        }

    }

}
