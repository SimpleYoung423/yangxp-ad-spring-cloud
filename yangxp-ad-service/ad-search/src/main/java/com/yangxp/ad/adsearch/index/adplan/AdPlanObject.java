package com.yangxp.ad.adsearch.index.adplan;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * {@link AdPlanObject}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/31 20:19
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdPlanObject {

    private Long planId;
    private Long userId;
    private Integer planStatus;
    private Date startTime;
    private Date endTime;

    public void update(AdPlanObject newObject){

        if (ObjectUtil.isNotNull(newObject.getPlanId())){
            this.planId  = newObject.getPlanId();
        }
        if (ObjectUtil.isNotNull(newObject.getUserId())){
            this.userId  = newObject.getUserId();
        }
        if (ObjectUtil.isNotNull(newObject.getPlanStatus())){
            this.planStatus  = newObject.getPlanStatus();
        }
        if (ObjectUtil.isNotNull(newObject.getStartTime())){
            this.startTime  = newObject.getStartTime();
        }
        if (ObjectUtil.isNotNull(newObject.getEndTime())){
            this.endTime  = newObject.getEndTime();
        }


    }

}
