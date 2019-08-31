package com.yangxp.ad.adsponsor.controller;

import com.alibaba.fastjson.JSON;
import com.yangxp.ad.adcommon.exception.AdException;
import com.yangxp.ad.adsponsor.service.IAdUnitService;
import com.yangxp.ad.adsponsor.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link AdUnitController}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/31 16:30
 * @Version 1.0
 */
@Slf4j
@RestController
public class AdUnitController {
    private final IAdUnitService adUnitService;

    public AdUnitController(IAdUnitService adUnitService) {
        this.adUnitService = adUnitService;
    }

    @PostMapping("/creat/adUnit")
    public AdUnitResponse createAdUnit(@RequestBody AdUnitRequest request) throws AdException {

        log.info("ad-sponsor: create ad unit -> {}",  JSON.toJSON(request));
        return adUnitService.createUnit(request);
    }

    @PostMapping("/create/adUnitKeyword")
    public AdUnitKeywordResponse createAdUnitKeyword(@RequestBody AdUnitKeywordRequest request) throws AdException{
        log.info("ad-sponsor: create ad unit keyword -> {}",  JSON.toJSON(request));
        return adUnitService.createUnitKeyword(request);
    }

    @PostMapping("/create/adUnitDistrict")
    public AdUnitDistrictResponse createAdUnitDistrict(@RequestBody AdUnitDistrictRequest request) throws AdException{

        log.info("ad-sponsor: create ad unit district -> {}",  JSON.toJSON(request));
        return adUnitService.createUnitDistrict(request);
    }

    @PostMapping("/create/adUnitIt")
    public AdUnitItResponse createAdUnitIt(@RequestBody AdUnitItRequest request) throws AdException{
        log.info("ad-sponsor: create ad unit it -> {}",  JSON.toJSON(request));
        return adUnitService.createUnitIt(request);
    }

    public CreativeUnitResponse createCreativeUnit(@RequestBody CreativeUnitRequest request) throws AdException{

        log.info("ad-sponsor: create ad creative unit  -> {}",  JSON.toJSON(request));
        return adUnitService.createCreativeUnit(request);
    }




}
