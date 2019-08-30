package com.yangxp.ad.adsponsor.constant;

import lombok.Getter;

/**
 * @Description: <p>TODO</p>
 * @Auther: yangxp
 * @Date: 2019/8/30 16:51
 * @Version 1.0
 */
@Getter
public enum CreativeType {

    IMAGE(1,"图片"),
    VIDEO(2,"视频"),
    TEXT(3,"文本");

    private int status;
    private String desc;

    CreativeType(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
