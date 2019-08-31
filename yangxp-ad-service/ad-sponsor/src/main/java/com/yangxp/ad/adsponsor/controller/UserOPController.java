package com.yangxp.ad.adsponsor.controller;

import com.alibaba.fastjson.JSON;
import com.yangxp.ad.adcommon.exception.AdException;
import com.yangxp.ad.adsponsor.service.IUserService;
import com.yangxp.ad.adsponsor.vo.CreateUserRequest;
import com.yangxp.ad.adsponsor.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link UserOPController}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/31 16:13
 * @Version 1.0
 */
@RestController
@Slf4j
public class UserOPController {

    private final IUserService userService;

    public UserOPController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create/user")
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) throws AdException {

        log.info("ad-sponsor: create user -> {}", JSON.toJSON(request));

        return userService.createUser(request);
    }


}
