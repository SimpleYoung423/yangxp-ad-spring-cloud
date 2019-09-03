package com.yangxp.ad.adsearch.mysql.dto;

import com.yangxp.ad.adsearch.mysql.constant.OpType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link TableTemplate}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/9/3 14:22
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableTemplate {

    private String tableName;
    private String level;
    private Map<OpType, List<String>> opTypeFiledSetMap = new HashMap<>();

    /** 字段索引 -> 字段名*/
    private Map<Integer,String > posMap = new HashMap<>();
}
