package com.yangxp.ad.adsponsor.dao;

import com.yangxp.ad.adsponsor.entity.AdPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * {@link AdPlanRepository}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/30 17:26
 * @Version 1.0
 */

public interface AdPlanRepository extends JpaRepository<AdPlan, Long> {

    /**
     * @return {@link AdPlan}
     * @Description 通过ID和userID查找
     * @Author yangxp
     * @Date 2019/8/30 17:28
     * @Param [id, userId]
     **/
    AdPlan findByIdAndUserId(Long id, Long userId);

    List<AdPlan> findAllByIdInAndUserId(List<Long> ids, Long userId);

    AdPlan findByUserIdAndPlanName(Long userId, String planName);

    List<AdPlan> findAllByPlanStatus(Integer status);

}
