package com.yangxp.ad.adsponsor.service.impl;

import com.yangxp.ad.adcommon.exception.AdException;
import com.yangxp.ad.adsponsor.dao.CreativeRepository;
import com.yangxp.ad.adsponsor.entity.Creative;
import com.yangxp.ad.adsponsor.service.ICreativeService;
import com.yangxp.ad.adsponsor.vo.CreativeRequest;
import com.yangxp.ad.adsponsor.vo.CreativeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link CreativeServiceImpl}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/31 14:26
 * @Version 1.0
 */
@Service
@Slf4j
public class CreativeServiceImpl implements ICreativeService {

    private final CreativeRepository creativeRepository;

    public CreativeServiceImpl(CreativeRepository creativeRepository) {
        this.creativeRepository = creativeRepository;
    }


    @Override
    public CreativeResponse createCreative(CreativeRequest request) throws AdException {

        Creative creative = creativeRepository.save(request.covertToCreative());

        return new CreativeResponse(creative.getId(), creative.getName());
    }
}
