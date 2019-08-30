package com.yangxp.ad.adsponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * {@link AdUnitKeywordRequest}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/30 19:36
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AdUnitKeywordRequest {

    private List<UnitKeyword> unitKeywords ;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UnitKeyword{

        private Long unitId;
        private String keyword;

    }


}
