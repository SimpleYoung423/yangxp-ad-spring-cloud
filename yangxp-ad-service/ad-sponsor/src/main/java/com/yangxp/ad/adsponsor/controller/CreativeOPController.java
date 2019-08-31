package com.yangxp.ad.adsponsor.controller;

import com.alibaba.fastjson.JSON;
import com.yangxp.ad.adcommon.exception.AdException;
import com.yangxp.ad.adsponsor.service.ICreativeService;
import com.yangxp.ad.adsponsor.vo.CreativeRequest;
import com.yangxp.ad.adsponsor.vo.CreativeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link CreativeOPController}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/31 16:44
 * @Version 1.0
 */

@Slf4j
@RestController
public class CreativeOPController {

    private final ICreativeService creativeService;

    public CreativeOPController(ICreativeService creativeService) {
        this.creativeService = creativeService;
    }

    @PostMapping("/create/creative")
    public CreativeResponse createCreative(@RequestBody CreativeRequest request) throws AdException{

        log.info("ad-sponsor: create ad creative -> {}",  JSON.toJSON(request));
        return creativeService.createCreative(request);
    }


}
