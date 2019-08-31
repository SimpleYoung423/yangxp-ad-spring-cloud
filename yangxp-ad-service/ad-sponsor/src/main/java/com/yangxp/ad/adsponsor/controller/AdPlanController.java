package com.yangxp.ad.adsponsor.controller;

import com.alibaba.fastjson.JSON;
import com.yangxp.ad.adcommon.exception.AdException;
import com.yangxp.ad.adsponsor.entity.AdPlan;
import com.yangxp.ad.adsponsor.service.IAdPlanService;
import com.yangxp.ad.adsponsor.vo.AdPlanGetRequest;
import com.yangxp.ad.adsponsor.vo.AdPlanRequest;
import com.yangxp.ad.adsponsor.vo.AdPlanResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * {@link AdPlanController}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/31 16:20
 * @Version 1.0
 */
@Slf4j
@RestController
public class AdPlanController {

    private final IAdPlanService adPlanService;
    public AdPlanController(IAdPlanService adPlanService) {
        this.adPlanService = adPlanService;
    }

    @PostMapping("/create/adPlan")
    public AdPlanResponse createAdPlan(@RequestBody AdPlanRequest request) throws AdException {

        log.info("ad-sponsor: create ad plan -> {}", JSON.toJSON(request));

        return adPlanService.createAdPlan(request);
    }

    @PostMapping("/get/adPlan")
    public List<AdPlan> getAdPlanByIds(@RequestBody AdPlanGetRequest request) throws AdException{

        log.info("ad-sponsor: get ad plan -> {}", JSON.toJSON(request));

        return adPlanService.getAdPlansByIds(request);
    }

    @PutMapping("/update/adPlan")
    public AdPlanResponse updateAdPlan(@RequestBody AdPlanRequest request) throws AdException{

        log.info("ad-sponsor: update ad plan -> {}", JSON.toJSON(request));
        return adPlanService.updateAdPlan(request);
    }

    @DeleteMapping("/delete/adPlan")
    public void deleteAdPlan(@RequestBody AdPlanRequest request) throws AdException{

        log.info("ad-sponsor: delete ad plan -> {}", JSON.toJSON(request));
        adPlanService.deleteAdPlan(request);
    }


}
