package com.yangxp.ad.adsearch.mysql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * {@link Template}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/9/3 14:21
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Template {

    private String database;
    private List<JsonTable> tableList;

}
