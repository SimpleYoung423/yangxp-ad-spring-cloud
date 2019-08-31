package com.yangxp.ad.adsponsor.vo;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@link CreateUserRequest}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/30 17:49
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private String username;

    public boolean validate() {
        return !StrUtil.isEmpty(this.username);
    }

}
