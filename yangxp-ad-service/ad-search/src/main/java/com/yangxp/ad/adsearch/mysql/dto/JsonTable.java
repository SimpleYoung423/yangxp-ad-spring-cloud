package com.yangxp.ad.adsearch.mysql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * {@link JsonTable}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/9/3 14:17
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonTable {

    private String tableName;
    private Integer level;

    private List<column> insert;

    private List<column> update;

    private List<column> delete;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class column {

        private String column;

    }

}
