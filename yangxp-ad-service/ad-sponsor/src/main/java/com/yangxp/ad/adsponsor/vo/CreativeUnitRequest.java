package com.yangxp.ad.adsponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * {@link CreativeUnitRequest}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/31 15:38
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreativeUnitRequest {

    private List<CreativeUnitItem> unitItems;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreativeUnitItem {

        private Long creativeId;
        private Long unitId;

    }

}
