package com.yangxp.ad.adcommon.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * {@link AdPlanTable}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/9/2 14:54
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanTable {

    private Long id;
    private Long userId;
    private Integer planStatus;
    private Date startTime;
    private Date endTime;


}
