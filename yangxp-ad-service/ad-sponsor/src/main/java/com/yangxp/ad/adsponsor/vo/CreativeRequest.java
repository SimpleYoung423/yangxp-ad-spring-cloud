package com.yangxp.ad.adsponsor.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.yangxp.ad.adsponsor.constant.CommonStatus;
import com.yangxp.ad.adsponsor.entity.Creative;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@link CreativeRequest}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/31 14:11
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeRequest {

    private String name;
    private Integer type;
    private Integer materialType;
    private Integer height;
    private Integer width;
    private Long size;
    private Integer duration;
    private Integer userId;
    private String url;

    public void toCreative() {

        BeanUtil.copyProperties(Creative.class, CreativeRequest.class);
    }

    public Creative covertToCreative() {

        Creative creative = new Creative();

        creative.setName(this.name);
        creative.setType(this.type);
        creative.setMaterialType(this.materialType);
        creative.setHeight(this.height);
        creative.setWidth(this.width);
        creative.setSize(this.size);
        creative.setDuration(this.duration);
        creative.setUserId(this.userId);
        creative.setUrl(this.url);
        creative.setAuditStatus(CommonStatus.VALID.getStatus());
        creative.setCreateTime(DateUtil.date());
        creative.setUpdateTime(creative.getCreateTime());

        return creative;
    }

}
