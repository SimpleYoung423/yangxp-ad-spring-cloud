package com.yangxp.ad.adsearch.index;

import com.alibaba.fastjson.JSON;
import com.yangxp.ad.adcommon.dump.DConstant;
import com.yangxp.ad.adcommon.dump.table.*;
import com.yangxp.ad.adsearch.handler.AdLevelDataHandler;
import com.yangxp.ad.adsearch.index.creativeunit.CreativeUnitIndex;
import com.yangxp.ad.adsearch.mysql.constant.OpType;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link IndexFileLoader}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/9/2 21:25
 * @Version 1.0
 */
@Component
@DependsOn("dataTable")
public class IndexFileLoader {


    @PostConstruct
    public void init(){

        List<String> adPLanStrings = loadDumpData(String.format("%s%s", DConstant.DATA_ROOT_DIR,DConstant.AD_PLAN));

        adPLanStrings.forEach( p-> AdLevelDataHandler.handleLevel2(JSON.parseObject(p, AdPlanTable.class), OpType.ADD));

        List<String> adCreativeStrings = loadDumpData(String.format("%s%s", DConstant.DATA_ROOT_DIR,DConstant.AD_CREATIVE));

        adCreativeStrings.forEach( p -> AdLevelDataHandler.handleLevel2(JSON.parseObject(p, AdCreativeTable.class), OpType.ADD));

        List<String> adUnitStrings = loadDumpData(String.format("%s%s", DConstant.DATA_ROOT_DIR,DConstant.AD_UNIT));

        adCreativeStrings.forEach( p -> AdLevelDataHandler.handleLevel3(JSON.parseObject( p, AdUnitTable.class), OpType.ADD));

        List<String> adCreativeUnitStrings = loadDumpData(String.format("%s%s", DConstant.DATA_ROOT_DIR,DConstant.AD_CREATIVE_UNIT_));

        adCreativeUnitStrings.forEach( p -> AdLevelDataHandler.handleLevel3(JSON.parseObject( p, AdCreativeUnitTable.class), OpType.ADD));

        List<String> adUnitDistrictStrings = loadDumpData(String.format("%s%s", DConstant.DATA_ROOT_DIR,DConstant.AD_UNIT_DISTRICT));

        adUnitDistrictStrings.forEach( p -> AdLevelDataHandler.handleLevel4(JSON.parseObject( p, AdUnitDistrictTable.class), OpType.ADD));

        List<String> adUnitItStrings = loadDumpData(String.format("%s%s", DConstant.DATA_ROOT_DIR,DConstant.AD_UNIT_IT));

        adUnitItStrings.forEach( p -> AdLevelDataHandler.handleLevel4(JSON.parseObject( p, AdUnitItTable.class), OpType.ADD));

        List<String> adUnitKeywordStrings = loadDumpData(String.format("%s%s", DConstant.DATA_ROOT_DIR,DConstant.AD_UNIT_KEYWORD));

        adUnitKeywordStrings.forEach( p -> AdLevelDataHandler.handleLevel4(JSON.parseObject( p, AdUnitKeywordTable.class), OpType.ADD));


    }



    private List<String> loadDumpData(String fileName){

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))){

            return br.lines().collect(Collectors.toList());

        }catch (IOException ex){
            throw  new RuntimeException(ex.getMessage() )
        }

    }



}
