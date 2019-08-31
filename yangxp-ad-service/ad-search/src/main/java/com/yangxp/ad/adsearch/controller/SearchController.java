package com.yangxp.ad.adsearch.controller;

import com.alibaba.fastjson.JSON;
import com.yangxp.ad.adcommon.annotation.IgnoreResponseAdvice;
import com.yangxp.ad.adcommon.vo.CommonResponse;
import com.yangxp.ad.adsearch.client.SponsorClient;
import com.yangxp.ad.adsearch.client.vo.AdPlan;
import com.yangxp.ad.adsearch.client.vo.AdPlanGetRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * {@link SearchController}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/31 18:19
 * @Version 1.0
 */

@RestController
@Slf4j
public class SearchController {

    private final RestTemplate restTemplate;

    private final SponsorClient sponsorClient;

    public SearchController(RestTemplate restTemplate, SponsorClient sponsorClient) {
        this.restTemplate = restTemplate;
        this.sponsorClient = sponsorClient;
    }

    @IgnoreResponseAdvice
    @PostMapping("/getAdPlans")
    public CommonResponse<List<AdPlan>> getAdPlan(@RequestBody AdPlanGetRequest request) {

        log.info("ad-search: get ad plan -> {}" , JSON.toJSON(request));

        return sponsorClient.getAdPlan(request);
    }


}
