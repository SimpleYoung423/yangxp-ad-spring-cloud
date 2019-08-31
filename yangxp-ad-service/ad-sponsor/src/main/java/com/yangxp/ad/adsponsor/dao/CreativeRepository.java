package com.yangxp.ad.adsponsor.dao;

import com.yangxp.ad.adsponsor.entity.Creative;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * {@link CreativeRepository}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/30 17:36
 * @Version 1.0
 */

public interface CreativeRepository extends JpaRepository<Creative, Long> {
}
