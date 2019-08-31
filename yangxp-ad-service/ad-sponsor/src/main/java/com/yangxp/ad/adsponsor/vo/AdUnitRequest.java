package com.yangxp.ad.adsponsor.vo;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@link AdUnitRequest}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/30 19:15
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitRequest {

    private Long planId;
    private String unitName;

    private Integer positionType;
    private Long budget;

    public boolean createValidate() {

        return ObjectUtil.isNotNull(planId)
                && StrUtil.isNotEmpty(unitName)
                && ObjectUtil.isNotNull(positionType)
                && ObjectUtil.isNotNull(budget);

    }


}
