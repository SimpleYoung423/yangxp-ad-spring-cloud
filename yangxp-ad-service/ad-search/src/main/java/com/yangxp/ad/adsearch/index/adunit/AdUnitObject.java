package com.yangxp.ad.adsearch.index.adunit;

import cn.hutool.core.util.ObjectUtil;
import com.yangxp.ad.adsearch.index.adplan.AdPlanObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@link AdUnitObject}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/31 21:16
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AdUnitObject {

    private Long unitId;
    private Integer unitStatus;
    private Integer positionType;
    private Long planId;

    private AdPlanObject adPlanObject;

    void update(AdUnitObject newObject){

        if (ObjectUtil.isNull(newObject.getUnitId())){
            this.unitId = newObject.getUnitId();
        }
        if (ObjectUtil.isNull(newObject.getUnitStatus())){
            this.unitStatus = newObject.getUnitStatus();
        }
        if (ObjectUtil.isNull(newObject.getPositionType())){
            this.positionType = newObject.getPositionType();
        }
        if (ObjectUtil.isNull(newObject.getPlanId())){
            this.planId = newObject.getPlanId();
        }
        if (ObjectUtil.isNull(newObject.getAdPlanObject())){
            this.adPlanObject = newObject.getAdPlanObject();
        }
    }


}
