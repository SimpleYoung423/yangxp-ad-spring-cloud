package com.yangxp.ad.adsponsor.vo;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@link AdPlanRequest}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/30 18:20
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AdPlanRequest {

    private Long id;
    private Long userId;
    private String planName;
    private String startTime;
    private String endTime;


    public boolean createValidate() {

        return ObjectUtil.isNotNull(userId)
                && StrUtil.isNotEmpty(planName)
                && StrUtil.isNotEmpty(startTime)
                && StrUtil.isNotEmpty(endTime);

    }

    public boolean updateValidate() {

        return ObjectUtil.isNotNull(id) && ObjectUtil.isNotNull(userId);
    }

    public boolean deleteValidate() {

        return ObjectUtil.isNotNull(id) && ObjectUtil.isNotNull(userId);
    }


}
