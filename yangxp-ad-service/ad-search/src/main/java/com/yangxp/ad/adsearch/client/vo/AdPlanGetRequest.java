package com.yangxp.ad.adsearch.client.vo;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * {@link AdPlanGetRequest}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/30 18:27
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AdPlanGetRequest {

    private Long userId;

    private List<Long> ids;

    public boolean validate() {

        return ObjectUtil.isNotNull(userId) && CollectionUtil.isNotEmpty(ids);
    }


}
