package com.yangxp.ad.adsponsor.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.yangxp.ad.adcommon.exception.AdException;
import com.yangxp.ad.adsponsor.constant.Constants;
import com.yangxp.ad.adsponsor.dao.AdUserRepository;
import com.yangxp.ad.adsponsor.entity.AdUser;
import com.yangxp.ad.adsponsor.service.IUserService;
import com.yangxp.ad.adsponsor.vo.CreateUserRequest;
import com.yangxp.ad.adsponsor.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link UserServiceImpl}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/30 17:55
 * @Version 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    private final AdUserRepository userRepository;

    @Autowired
    public UserServiceImpl(AdUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) throws AdException {

        if (!createUserRequest.validate()) {

            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        String username = createUserRequest.getUsername();

        AdUser oldUser = userRepository.findByUsername(username);

        if (ObjectUtil.isNotEmpty(oldUser)) {

            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }

        AdUser newUser = userRepository.save(new AdUser(username, SecureUtil.md5(username)));
        return new CreateUserResponse(newUser.getId(), newUser.getUsername(), newUser.getToken(), newUser.getCreateTime(), newUser.getUpdateTime());
    }
}
