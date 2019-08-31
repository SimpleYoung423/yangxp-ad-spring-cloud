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
     * @return {@link AdPlanResponse}
     * @Description 创建推广计划
     * @Author yangxp
     * @Date 2019/8/30 18:33
     * @Param [request]
     **/
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    /**
     * @return {@link List< AdPlan>}
     * @Description 根据IDS获取推广计划
     * @Author yangxp
     * @Date 2019/8/30 18:34
     * @Param [getRequest]
     **/
    List<AdPlan> getAdPlansByIds(AdPlanGetRequest getRequest) throws AdException;

    /**
     * @return {@link AdPlanResponse}
     * @Description 更新推广计划
     * @Author yangxp
     * @Date 2019/8/30 18:35
     * @Param [request]
     **/
    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    /**
     * @return {@link }
     * @Description 删除推广计划
     * @Author yangxp
     * @Date 2019/8/30 18:38
     * @Param [request]
     **/
    void deleteAdPlan(AdPlanRequest request) throws AdException;
}
