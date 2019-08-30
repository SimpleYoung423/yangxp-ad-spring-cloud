package com.yangxp.ad.adsponsor.service;

import com.yangxp.ad.adcommon.exception.AdException;
import com.yangxp.ad.adsponsor.entity.AdPlan;
import com.yangxp.ad.adsponsor.vo.AdPlanGetRequest;
import com.yangxp.ad.adsponsor.vo.AdPlanRequest;
import com.yangxp.ad.adsponsor.vo.AdPlanResponse;

import java.util.List;

/**
 * {@link IAdPlanService}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/30 18:32
 * @Version 1.0
 */

public interface IAdPlanService {

    /**
     * @Description 创建推广计划
     * @Author yangxp
     * @Date  2019/8/30 18:33
     * @Param [request]
     * @return {@link AdPlanResponse}
     **/
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    /**
     * @Description 根据IDS获取推广计划
     * @Author yangxp
     * @Date  2019/8/30 18:34
     * @Param [getRequest]
     * @return {@link List< AdPlan>}
     **/
    List<AdPlan> getAdPlansByIds(AdPlanGetRequest getRequest) throws AdException;

    /**
     * @Description 更新推广计划
     * @Author yangxp
     * @Date  2019/8/30 18:35
     * @Param [request]
     * @return {@link AdPlanResponse}
     **/
    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    /**
     * @Description 删除推广计划
     * @Author yangxp
     * @Date  2019/8/30 18:38
     * @Param [request]
     * @return {@link }
     **/
    void deleteAdPlan( AdPlanRequest request) throws AdException;
}
