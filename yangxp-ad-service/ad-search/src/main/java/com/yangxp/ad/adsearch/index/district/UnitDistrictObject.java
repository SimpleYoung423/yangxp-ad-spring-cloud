package com.yangxp.ad.adsearch.index.district;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@link UnitDistrictObject}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/9/1 10:43
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitDistrictObject {

    private Long unitId;
    private String province;
    private String city;

}
