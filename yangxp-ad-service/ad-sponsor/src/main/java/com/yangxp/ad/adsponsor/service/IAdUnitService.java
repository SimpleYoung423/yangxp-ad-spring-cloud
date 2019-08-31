package com.yangxp.ad.adsponsor.service;

import com.yangxp.ad.adcommon.exception.AdException;
import com.yangxp.ad.adsponsor.entity.unit_condition.CreativeUnit;
import com.yangxp.ad.adsponsor.vo.*;

/**
 * {@link IAdUnitService}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/30 19:15
 * @Version 1.0
 */

public interface IAdUnitService {


    /**
     * @return {@link AdUnitResponse}
     * @Description 创建推广单元
     * @Author yangxp
     * @Date 2019/8/30 19:21
     * @Param [request]
     **/
    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;


    /**
     * @return {@link AdUnitKeywordResponse}
     * @Description 创建推广关键词
     * @Author yangxp
     * @Date 2019/8/30 19:55
     * @Param [request]
     **/
    AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException;


    AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException;

    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException;


    CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException;

}
