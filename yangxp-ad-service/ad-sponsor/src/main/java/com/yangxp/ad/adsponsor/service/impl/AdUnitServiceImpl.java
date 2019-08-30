package com.yangxp.ad.adsponsor.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.yangxp.ad.adcommon.exception.AdException;
import com.yangxp.ad.adsponsor.constant.Constants;
import com.yangxp.ad.adsponsor.dao.AdPlanRepository;
import com.yangxp.ad.adsponsor.dao.AdUnitRepository;
import com.yangxp.ad.adsponsor.dao.unit_condition.AdUnitDistrictRepository;
import com.yangxp.ad.adsponsor.dao.unit_condition.AdUnitItRepository;
import com.yangxp.ad.adsponsor.dao.unit_condition.AdUnitKeywordRepository;
import com.yangxp.ad.adsponsor.entity.AdPlan;
import com.yangxp.ad.adsponsor.entity.AdUnit;
import com.yangxp.ad.adsponsor.entity.unit_condition.AdUnitKeyWord;
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

    public AdUnitServiceImpl(AdUnitRepository unitRepository, AdPlanRepository planRepository, AdUnitKeywordRepository unitKeywordRepository, AdUnitItRepository unitItRepository, AdUnitDistrictRepository unitDistrictRepository) {
        this.unitRepository = unitRepository;
        this.planRepository = planRepository;
        this.unitKeywordRepository = unitKeywordRepository;
        this.unitItRepository = unitItRepository;
        this.unitDistrictRepository = unitDistrictRepository;
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

        List<Long> ids = Collections.emptyList();

        List<AdUnitKeyWord> unitKeyWords = new ArrayList<>();

        if (CollectionUtil.isNotEmpty(request.getUnitKeywords())) {
            request.getUnitKeywords().forEach(i -> unitKeyWords.add(new AdUnitKeyWord(i.getUnitId(),i.getKeyword())));
        }

        ids = unitKeywordRepository.saveAll(unitKeyWords).stream().map(AdUnitKeyWord::getId).collect(Collectors.toList());

        return new AdUnitKeywordResponse(ids);
    }

    @Override
    public AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException {
        return null;
    }

    @Override
    public AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException {
        return null;
    }

    public boolean isRelatedUnitExist(List<Long> unitIds) {

        if (CollectionUtil.isEmpty(unitIds)) {

            return false;
        }

        return unitRepository.findAllById(unitIds).size() == new HashSet<>(unitIds).size();
    }


}
