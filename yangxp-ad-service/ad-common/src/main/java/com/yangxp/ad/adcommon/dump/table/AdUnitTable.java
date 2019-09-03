package com.yangxp.ad.adcommon.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@link AdUnitTable}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/9/2 14:57
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitTable {

    private Long unitId;
    private Integer unitStatus;
    private Integer positionType;
    private Long planId;


}
