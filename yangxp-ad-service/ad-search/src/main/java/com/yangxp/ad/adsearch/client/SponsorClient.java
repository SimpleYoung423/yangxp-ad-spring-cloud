package com.yangxp.ad.adsearch.client;

import com.yangxp.ad.adcommon.vo.CommonResponse;
import com.yangxp.ad.adsearch.client.vo.AdPlan;
import com.yangxp.ad.adsearch.client.vo.AdPlanGetRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * {@link SponsorClient}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/31 18:26
 * @Version 1.0
 */
@FeignClient(value = "eureka-client-ad-search",fallback = SponsorClientHystrix.class)
public interface SponsorClient {

    public static final String PREFIX_URL = "/ad-sponsor";


    @PostMapping( PREFIX_URL + "/get/adPlan")
   public CommonResponse<List<AdPlan>> getAdPlan(@RequestBody AdPlanGetRequest request);

}
