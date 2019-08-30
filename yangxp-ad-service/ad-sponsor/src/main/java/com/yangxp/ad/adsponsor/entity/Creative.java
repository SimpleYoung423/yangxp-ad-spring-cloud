package com.yangxp.ad.adsponsor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: Creative
 * @Description: TODO
 * @Auther: yangxp
 * @Date: 2019/8/30 16:32
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_creative")
public class Creative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "type",nullable = false)
    private Integer type;

    /** 物料类型，如图片是bmp,jpg...*/
    @Column(name = "material_type",nullable =false)
    private Integer materialType;

    @Column(name = "height",nullable = false)
    private Integer height;

    @Column(name = "width",nullable = false)
    private Integer width;

    @Column(name = "size",nullable = false)
    private Long size;

    /** 材料长度，如视频的时长*/
    @Column(name = "duration",nullable = false)
    private Integer duration;

    @Column(name = "audit_status",nullable = false)
    private Integer auditStatus;

    @Column(name = "user_id",nullable = false)
    private Integer userId;

    /** 物料的地址信息*/
    @Column(name = "url",nullable = false)
    private String url;

    @Column(name = "create_time",nullable = false)
    public Date createTime;

    @Column(name = "update_time",nullable = false)
    public Date updateTime;


}
