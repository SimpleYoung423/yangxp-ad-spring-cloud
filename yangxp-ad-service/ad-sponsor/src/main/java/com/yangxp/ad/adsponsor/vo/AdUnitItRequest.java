package com.yangxp.ad.adsponsor.vo;

import com.yangxp.ad.adsponsor.entity.AdUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * {@link AdUnitItRequest}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/30 19:41
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitItRequest {

    private List<UnitIt> unitIts;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UnitIt {

        private Long unitId;
        private String itTag;
    }


}
