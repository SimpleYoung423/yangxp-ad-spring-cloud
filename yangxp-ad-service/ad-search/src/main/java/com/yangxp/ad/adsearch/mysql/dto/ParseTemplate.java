package com.yangxp.ad.adsearch.mysql.dto;

import com.yangxp.ad.adsearch.mysql.constant.OpType;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * {@link ParseTemplate}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/9/3 14:46
 * @Version 1.0
 */
@Data
public class ParseTemplate {

    private String database;

    private Map<String,TableTemplate>  tableTemplateMap = new HashMap<>();

    public static ParseTemplate parse(Template template){

        ParseTemplate parseTemplate = new ParseTemplate();

        parseTemplate.setDatabase(template.getDatabase());

        for (JsonTable table : template.getTableList()) {

            String name = table.getTableName();
            Integer level = table.getLevel();
            TableTemplate tableTemplate = new TableTemplate();
            tableTemplate.setTableName(name);
            tableTemplate.setLevel(level.toString());

            parseTemplate.tableTemplateMap.put(name,tableTemplate);

            Map<OpType, List<String>> opTypeFiledSetMap = tableTemplate.getOpTypeFiledSetMap();

            for (JsonTable.column column : table.getInsert()) {
                getAndCreateIfNeed(OpType.ADD,opTypeFiledSetMap, ArrayList::new).add(column.getColumn());
            }

            for (JsonTable.column column : table.getUpdate()) {
                getAndCreateIfNeed(OpType.UPDATE,opTypeFiledSetMap, ArrayList::new).add(column.getColumn());

            }

            for (JsonTable.column column : table.getDelete()) {
                getAndCreateIfNeed(OpType.DELETE,opTypeFiledSetMap, ArrayList::new).add(column.getColumn());
            }
        }
        return parseTemplate;
    }

    private static <T,R> R getAndCreateIfNeed(T key, Map<T,R> map, Supplier<R> factory){

        return map.computeIfAbsent(key,k->factory.get());

    }


}
