package com.yangxp.ad.adsponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * {@link CreateUserResponse}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/30 17:51
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateUserResponse {

    private Long userId;
    private String username;
    private String token;
    private Date createTime;
    private Date updateTime;




}
