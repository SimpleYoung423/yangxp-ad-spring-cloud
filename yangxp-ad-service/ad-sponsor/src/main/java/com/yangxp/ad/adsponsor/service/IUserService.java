package com.yangxp.ad.adsponsor.service;

import com.yangxp.ad.adcommon.exception.AdException;
import com.yangxp.ad.adsponsor.vo.CreateUserRequest;
import com.yangxp.ad.adsponsor.vo.CreateUserResponse;

/**
 * {@link IUserService}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/30 17:53
 * @Version 1.0
 */

public interface IUserService {

    /**
     * @return {@link CreateUserResponse}
     * @Description <h2>创建用户</h2>
     * @Author yangxp
     * @Date 2019/8/30 17:54
     * @Param [createUserRequest]
     **/
    CreateUserResponse createUser(CreateUserRequest createUserRequest) throws AdException;


}
