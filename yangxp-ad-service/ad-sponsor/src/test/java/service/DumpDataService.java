package service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.netflix.discovery.CommonConstants;
import com.sun.org.apache.bcel.internal.generic.DCONST;
import com.yangxp.ad.adcommon.dump.DConstant;
import com.yangxp.ad.adcommon.dump.table.*;
import com.yangxp.ad.adsponsor.AdSponsorApplication;
import com.yangxp.ad.adsponsor.constant.CommonStatus;
import com.yangxp.ad.adsponsor.dao.AdPlanRepository;
import com.yangxp.ad.adsponsor.dao.AdUnitRepository;
import com.yangxp.ad.adsponsor.dao.CreativeRepository;
import com.yangxp.ad.adsponsor.dao.unit_condition.AdUnitDistrictRepository;
import com.yangxp.ad.adsponsor.dao.unit_condition.AdUnitItRepository;
import com.yangxp.ad.adsponsor.dao.unit_condition.AdUnitKeywordRepository;
import com.yangxp.ad.adsponsor.dao.unit_condition.CreativeUnitRepository;
import com.yangxp.ad.adsponsor.entity.AdPlan;
import com.yangxp.ad.adsponsor.entity.AdUnit;
import com.yangxp.ad.adsponsor.entity.Creative;
import com.yangxp.ad.adsponsor.entity.unit_condition.AdUnitDistrict;
import com.yangxp.ad.adsponsor.entity.unit_condition.AdUnitIt;
import com.yangxp.ad.adsponsor.entity.unit_condition.AdUnitKeyWord;
import com.yangxp.ad.adsponsor.entity.unit_condition.CreativeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.rmi.runtime.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link DumpDataService}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/9/2 15:11
 * @Version 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdSponsorApplication.class,AdSponsorApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DumpDataService {
    @Autowired
    private AdPlanRepository adPlanRepository;
    @Autowired
    private AdUnitRepository adUnitRepository;
    @Autowired
    private CreativeRepository creativeRepository;
    @Autowired
    private CreativeUnitRepository creativeUnitRepository;
    @Autowired
    private AdUnitDistrictRepository adUnitDistrictRepository;
    @Autowired
    private AdUnitKeywordRepository adUnitKeywordRepository;
    @Autowired
    private AdUnitItRepository adUnitItRepository;

    private void dumpAdPlanTbale(String fileName) {

        List<AdPlan> adPlans = adPlanRepository.findAllByPlanStatus(CommonStatus.VALID.getStatus());
        if (CollectionUtil.isEmpty(adPlans)) {
            return;
        }
        List<AdPlanTable> planTables = new ArrayList<>();
        adPlans.forEach(i -> planTables.add(new AdPlanTable(i.getId(), i.getUserId(), i.getPlanStatus(), i.getStartTime(), i.getEndTime())));
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {

            for (AdPlanTable planTable : planTables) {
                writer.write(JSON.toJSONString(planTable));
                writer.newLine();
            }
        } catch (IOException ex) {
            log.error("dump ad plan table error!");
        }

    }

    private void dumpAdUnitTable(String fileName) {

        List<AdUnit> adUnits = adUnitRepository.findAllByUnitStatus(CommonStatus.VALID.getStatus());

        if (CollectionUtil.isEmpty(adUnits)) {
            return;
        }

        List<AdUnitTable> unitTables = new ArrayList<>();

        adUnits.forEach(i -> unitTables.add(new AdUnitTable(i.getId(), i.getUnitStatus(), i.getPositionType(), i.getPlanId())));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {

            for (AdUnitTable unitTable : unitTables) {
                writer.write(JSON.toJSONString(unitTable));
                writer.newLine();
            }
        } catch (IOException ex) {
            log.error("dump ad unit table error!");
        }

    }

    private void dumpAdCreativeTable(String fileName) {

        List<Creative> allCreative = creativeRepository.findAll();

        if (CollectionUtil.isEmpty(allCreative)) {
            return;
        }
        List<AdCreativeTable> creativeTables = new ArrayList<>();

        allCreative.forEach(i -> creativeTables.add(new AdCreativeTable(
                i.getId(), i.getName(), i.getType(), i.getMaterialType(), i.getHeight(), i.getWidth(), i.getAuditStatus(), i.getUrl())));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {

            for (AdCreativeTable creativeTable : creativeTables) {
                writer.write(JSON.toJSONString(creativeTable));
                writer.newLine();
            }
        } catch (IOException ex) {
            log.error("dump ad creative table error!");
        }

    }

    private void dumpAdCreativeUnitTable(String fileName) {

        List<CreativeUnit> allCreativeUnit = creativeUnitRepository.findAll();
        if (CollectionUtil.isEmpty(allCreativeUnit)) {
            return;
        }

        List<AdCreativeUnitTable> adCreativeUnitTables = new ArrayList<>();

        allCreativeUnit.forEach(i -> adCreativeUnitTables.add(new AdCreativeUnitTable(i.getCreativeId(), i.getUnitId())));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {

            for (AdCreativeUnitTable creativeUnitTable : adCreativeUnitTables) {
                writer.write(JSON.toJSONString(creativeUnitTable));
                writer.newLine();
            }
        } catch (IOException ex) {
            log.error("dump ad creative unit  table error!");
        }

    }

    private void dumpAdUnitDistrictTable(String fileName) {

        List<AdUnitDistrict> districtList = adUnitDistrictRepository.findAll();
        if (CollectionUtil.isEmpty(districtList)) {
            return;
        }

        List<AdUnitDistrictTable> adUnitDistrictTables = new ArrayList<>();

        districtList.forEach(i -> adUnitDistrictTables.add(new AdUnitDistrictTable(i.getUnitId(), i.getProvince(), i.getCity())));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {

            for (AdUnitDistrictTable adUnitDistrictTable : adUnitDistrictTables) {

                writer.write(JSON.toJSONString(adUnitDistrictTable));
                writer.newLine();
            }

        } catch (IOException ex) {
            log.error("dump ad  district  table error!");
        }

    }


    private void dumpAdUnitItTable(String fileName) {

        List<AdUnitIt> adUnitIts = adUnitItRepository.findAll();

        if (CollectionUtil.isEmpty(adUnitIts)) {
            return;
        }

        List<AdUnitItTable> adUnitItTables = new ArrayList<>();

        adUnitIts.forEach(i -> adUnitItTables.add(new AdUnitItTable(i.getUnitId(), i.getItTag())));

        Path path = Paths.get(fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {

            for (AdUnitItTable unitItTable : adUnitItTables) {

                writer.write(JSON.toJSONString(adUnitItTables));
                writer.newLine();
            }

        } catch (IOException ex) {
            log.error("dump ad  unit interest  table error!");
        }

    }

    private void dumpAdUnitKeywordTable(String fileName) {

        List<AdUnitKeyWord> unitKeyWords = adUnitKeywordRepository.findAll();

        if (CollectionUtil.isEmpty(unitKeyWords)) {
            return;
        }

        List<AdUnitKeywordTable> unitKeywordTables = new ArrayList<>();

        unitKeyWords.forEach(i -> unitKeywordTables.add(new AdUnitKeywordTable(i.getUnitId(), i.getKeyword())));

        Path path = Paths.get(fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdUnitKeywordTable unitKeywordTable : unitKeywordTables) {

                writer.write(JSON.toJSONString(unitKeywordTable));
                writer.newLine();

            }
        }catch (IOException ex){

            log.error("dump ad  unit keyword  table error!");
        }


    }

    @Test
    public void dumpAdTableData(){

        dumpAdPlanTbale(StrUtil.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_PLAN));
        dumpAdUnitTable(StrUtil.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT));
        dumpAdUnitItTable(StrUtil.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT_IT));
        dumpAdCreativeTable(StrUtil.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_CREATIVE));
        dumpAdCreativeUnitTable(StrUtil.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_CREATIVE_UNIT_));
        dumpAdUnitDistrictTable(StrUtil.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT_DISTRICT));
        dumpAdUnitKeywordTable(StrUtil.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT_KEYWORD));
    }





}
