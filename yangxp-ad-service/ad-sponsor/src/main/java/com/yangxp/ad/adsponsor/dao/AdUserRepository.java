package com.yangxp.ad.adsponsor.dao;

import com.yangxp.ad.adsponsor.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * {@link AdUserRepository}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/30 17:19
 * @Version 1.0
 */

public interface AdUserRepository extends JpaRepository<AdUser, Long> {

     /**
      * @Description //TODO
      * @Author yangxp
      * @Date  2019/8/30 17:25
      * @Param [username]
      * @return {@link AdUser}
      **/
    AdUser findByUsername(String username);

}
