package com.yangxp.ad.adsponsor.dao;

import com.yangxp.ad.adsponsor.entity.AdUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * {@link AdUnitRepository}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/30 17:33
 * @Version 1.0
 */

public interface AdUnitRepository extends JpaRepository<AdUnit,Long> {

    AdUnit findByPlanIdAndUnitName(Long planId,String unitName);

    List<AdUnit> findAllByUnitStatus(Integer status);

}
