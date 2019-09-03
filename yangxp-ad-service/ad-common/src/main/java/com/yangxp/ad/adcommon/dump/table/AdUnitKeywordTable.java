package com.yangxp.ad.adcommon.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@link AdUnitKeywordTable}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/9/2 15:00
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitKeywordTable {

    private Long unitId;
    private String keyword;

}
