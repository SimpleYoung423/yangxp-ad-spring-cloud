package com.yangxp.ad.adsponsor.service;

import com.yangxp.ad.adcommon.exception.AdException;
import com.yangxp.ad.adsponsor.vo.CreativeRequest;
import com.yangxp.ad.adsponsor.vo.CreativeResponse;

/**
 * {@link ICreativeService}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/31 14:11
 * @Version 1.0
 */

public interface ICreativeService {

    CreativeResponse createCreative(CreativeRequest request) throws AdException;


}
