package com.yangxp.ad.adcommon.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@link AdUnitDistrictTable}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/9/2 15:01
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitDistrictTable {

    private Long unitId;
    private String province;
    private String city;


}
