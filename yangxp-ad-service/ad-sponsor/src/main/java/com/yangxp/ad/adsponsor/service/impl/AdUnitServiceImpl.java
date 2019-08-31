package com.yangxp.ad.adsponsor.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.netflix.discovery.converters.Auto;
import com.yangxp.ad.adcommon.exception.AdException;
import com.yangxp.ad.adsponsor.constant.Constants;
import com.yangxp.ad.adsponsor.dao.AdPlanRepository;
import com.yangxp.ad.adsponsor.dao.AdUnitRepository;
import com.yangxp.ad.adsponsor.dao.CreativeRepository;
import com.yangxp.ad.adsponsor.dao.unit_condition.AdUnitDistrictRepository;
import com.yangxp.ad.adsponsor.dao.unit_condition.AdUnitItRepository;
import com.yangxp.ad.adsponsor.dao.unit_condition.AdUnitKeywordRepository;
import com.yangxp.ad.adsponsor.dao.unit_condition.CreativeUnitRepository;
import com.yangxp.ad.adsponsor.entity.AdPlan;
import com.yangxp.ad.adsponsor.entity.AdUnit;
import com.yangxp.ad.adsponsor.entity.unit_condition.AdUnitDistrict;
import com.yangxp.ad.adsponsor.entity.unit_condition.AdUnitIt;
import com.yangxp.ad.adsponsor.entity.unit_condition.AdUnitKeyWord;
import com.yangxp.ad.adsponsor.entity.unit_condition.CreativeUnit;
import com.yangxp.ad.adsponsor.service.IAdUnitService;
import com.yangxp.ad.adsponsor.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * {@link AdUnitServiceImpl}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/30 19:21
 * @Version 1.0
 */
@Slf4j
@Service
public class AdUnitServiceImpl implements IAdUnitService {

    private final AdUnitRepository unitRepository;
    private final AdPlanRepository planRepository;
    private final AdUnitKeywordRepository unitKeywordRepository;
    private final AdUnitItRepository unitItRepository;
    private final AdUnitDistrictRepository unitDistrictRepository;
    private final CreativeRepository creativeRepository;
    private final CreativeUnitRepository creativeUnitRepository;

    public AdUnitServiceImpl(AdUnitRepository unitRepository, AdPlanRepository planRepository, AdUnitKeywordRepository unitKeywordRepository, AdUnitItRepository unitItRepository, AdUnitDistrictRepository unitDistrictRepository, CreativeRepository creativeRepository, CreativeUnitRepository creativeUnitRepository) {
        this.unitRepository = unitRepository;
        this.planRepository = planRepository;
        this.unitKeywordRepository = unitKeywordRepository;
        this.unitItRepository = unitItRepository;
        this.unitDistrictRepository = unitDistrictRepository;
        this.creativeRepository = creativeRepository;
        this.creativeUnitRepository = creativeUnitRepository;
    }

    @Override
    public AdUnitResponse createUnit(AdUnitRequest request) throws AdException {

        if (!request.createValidate()) {

            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        Optional<AdPlan> plan = planRepository.findById(request.getPlanId());
        if (!plan.isPresent()) {

            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        AdUnit oldUnit = unitRepository.findByPlanIdAndUnitName(request.getPlanId(), request.getUnitName());

        if (ObjectUtil.isNotNull(oldUnit)) {

            throw new AdException(Constants.ErrorMsg.SAME_NAME_UNIT_ERROR);
        }

        AdUnit newUnit = unitRepository.save(new AdUnit(request.getPlanId(), request.getUnitName(), request.getPositionType(), request.getBudget()));


        return new AdUnitResponse(newUnit.getId(), newUnit.getUnitName());
    }


    @Override
    public AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException {

        List<Long> unitIds = request.getUnitKeywords().stream().map(AdUnitKeywordRequest.UnitKeyword::getUnitId).collect(Collectors.toList());

        if (!isRelatedUnitExist(unitIds)) {

            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }


        List<AdUnitKeyWord> unitKeyWords = new ArrayList<>();

        if (CollectionUtil.isNotEmpty(request.getUnitKeywords())) {
            request.getUnitKeywords().forEach(i -> unitKeyWords.add(new AdUnitKeyWord(i.getUnitId(), i.getKeyword())));
        }

        List<Long> ids = unitKeywordRepository.saveAll(unitKeyWords).stream().map(AdUnitKeyWord::getId).collect(Collectors.toList());

        return new AdUnitKeywordResponse(ids);
    }

    @Override
    public AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException {

        List<Long> unitIds = request.getUnitIts().stream().map(AdUnitItRequest.UnitIt::getUnitId).collect(Collectors.toList());
        if (!isRelatedUnitExist(unitIds)) {

            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<AdUnitIt> units = new ArrayList<>();
        request.getUnitIts().forEach(i -> units.add(new AdUnitIt(i.getUnitId(), i.getItTag())));
        List<Long> ids = unitItRepository.saveAll(units).stream().map(AdUnitIt::getId).collect(Collectors.toList());

        return new AdUnitItResponse(ids);
    }

    @Override
    public AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException {

        List<AdUnitDistrictRequest.UnitDistrict> districts = request.getUnitDistricts();

        List<Long> unitIds = districts.stream().map(AdUnitDistrictRequest.UnitDistrict::getUnitId).collect(Collectors.toList());

        if (!isRelatedUnitExist(unitIds)) {

            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<AdUnitDistrict> unitDistricts = new ArrayList<>();

        districts.forEach(i -> unitDistricts.add(new AdUnitDistrict(i.getUnitId(), i.getProvince(), i.getCity())));


        List<Long> ids = unitDistrictRepository.saveAll(unitDistricts).stream().map(AdUnitDistrict::getId).collect(Collectors.toList());
        return new AdUnitDistrictResponse(ids);
    }

    @Override
    public CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException {

        List<CreativeUnitRequest.CreativeUnitItem> unitItems = request.getUnitItems();

        List<Long> unitIds = unitItems.stream().map(CreativeUnitRequest.CreativeUnitItem::getUnitId).collect(Collectors.toList());

        List<Long> creativeIds = unitItems.stream().map(CreativeUnitRequest.CreativeUnitItem::getCreativeId).collect(Collectors.toList());

        if (!(isRelatedUnitExist(unitIds)
                && isRelatedCreativeExist(creativeIds))) {

            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<CreativeUnit> creativeUnits = new ArrayList<>();

        unitItems.forEach(i -> creativeUnits.add(new CreativeUnit(i.getCreativeId(), i.getUnitId())));

        List<Long> ids = creativeUnitRepository.saveAll(creativeUnits).stream().map(CreativeUnit::getId).collect(Collectors.toList());


        return new CreativeUnitResponse(ids);
    }


    private boolean isRelatedUnitExist(List<Long> unitIds) {

        if (CollectionUtil.isEmpty(unitIds)) {

            return false;
        }

        return unitRepository.findAllById(unitIds).size() == new HashSet<>(unitIds).size();
    }

    private boolean isRelatedCreativeExist(List<Long> creativeIds) {

        if (CollectionUtil.isEmpty(creativeIds)) {

            return false;
        }

        return creativeRepository.findAllById(creativeIds).size() == new HashSet<>(creativeIds).size();

    }


}
