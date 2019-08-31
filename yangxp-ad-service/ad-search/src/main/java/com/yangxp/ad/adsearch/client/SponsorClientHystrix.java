package com.yangxp.ad.adsearch.client;

import com.yangxp.ad.adcommon.vo.CommonResponse;
import com.yangxp.ad.adsearch.client.vo.AdPlan;
import com.yangxp.ad.adsearch.client.vo.AdPlanGetRequest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * {@link SponsorClientHystrix}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/31 18:43
 * @Version 1.0
 */

@Component
public class SponsorClientHystrix implements SponsorClient{


    @Override
    public CommonResponse<List<AdPlan>> getAdPlan(AdPlanGetRequest request) {
        return new CommonResponse<>(-1,"eureka-client-ad-sponsor error");
    }
}
