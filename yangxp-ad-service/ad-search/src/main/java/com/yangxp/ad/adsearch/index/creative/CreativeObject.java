package com.yangxp.ad.adsearch.index.creative;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@link CreativeObject}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/9/1 19:02
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeObject {

    private Long adId;
    private String name;
    private Integer type;
    private Integer materialType;
    private Integer height;
    private Integer width;
    private Integer auditStatus;
    private String adUrl;

    public void update(CreativeObject newCreativeObject){

        if (ObjectUtil.isNotNull(newCreativeObject.getAdId())){

            this.adId = newCreativeObject.getAdId();
        }
        if (ObjectUtil.isNotNull(newCreativeObject.getName())){

            this.name = newCreativeObject.getName();
        }
        if (ObjectUtil.isNotNull(newCreativeObject.getType())){

            this.type = newCreativeObject.getType();
        }
        if (ObjectUtil.isNotNull(newCreativeObject.getMaterialType())){

            this.materialType = newCreativeObject.getMaterialType();
        }
        if (ObjectUtil.isNotNull(newCreativeObject.getHeight())){

            this.height = newCreativeObject.getHeight();
        }
        if (ObjectUtil.isNotNull(newCreativeObject.getWidth())){

            this.width = newCreativeObject.getWidth();
        }
        if (ObjectUtil.isNotNull(newCreativeObject.getAuditStatus())){

            this.auditStatus = newCreativeObject.getAuditStatus();
        }
        if (ObjectUtil.isNotNull(newCreativeObject.getAdUrl())){

            this.adUrl = newCreativeObject.getAdUrl();
        }

    }

}
