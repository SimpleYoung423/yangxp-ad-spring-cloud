package com.yangxp.ad.adsponsor.constant;

import lombok.Getter;

/**
 * {@link CreativeMaterialType}
 *
 * @Description: <p>TODO</p>
 * @author: yangxp
 * @Date: 2019/8/30 17:04
 * @Version 1.0
 */

@Getter
public enum CreativeMaterialType {


    JPG(1,"jpg"),
    BMP(2,"bmp"),
    MP4(3,"mp4"),
    AVI(4,"avi"),
    TXT(5,"txt");

    private int type;
    private String desc;

    CreativeMaterialType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
