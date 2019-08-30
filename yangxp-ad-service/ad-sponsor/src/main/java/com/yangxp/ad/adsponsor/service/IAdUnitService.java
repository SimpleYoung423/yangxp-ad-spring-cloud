package com.yangxp.ad.adsponsor.service;

import com.yangxp.ad.adcommon.exception.AdException;
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
     * @Description 创建推广单元
     * @Author yangxp
     * @Date  2019/8/30 19:21
     * @Param [request]
     * @return {@link AdUnitResponse}
     **/
    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;


    /**
     * @Description 创建推广关键词
     * @Author yangxp
     * @Date  2019/8/30 19:55
     * @Param [request]
     * @return {@link AdUnitKeywordResponse}
     **/
    AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException;


    AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException;

    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException;

}
