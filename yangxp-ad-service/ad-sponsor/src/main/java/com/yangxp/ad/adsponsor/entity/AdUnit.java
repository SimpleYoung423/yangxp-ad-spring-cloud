package com.yangxp.ad.adsponsor.entity;

import com.yangxp.ad.adsponsor.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: AdUnit
 * @Description: TODO
 * @Auther: yangxp
 * @Date: 2019/8/30 16:15
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_unit")
public class AdUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "plan_id",nullable = false)
    private Long planId;

    @Column(name = "unit_name",nullable = false)
    private String  unitName;

    @Column(name = "unit_status",nullable = false)
    private Integer unitStatus;

    /**  广告位类型（开屏、贴片...） */
    @Column(name = "position_type",nullable = false)
    private Integer positionType;

    @Column(name = "budget",nullable = false)
    private Long budget;

    @Column(name = "create_time",nullable = false)
    public Date createTime;

    @Column(name = "update_time",nullable = false)
    public Date updateTime;

    public AdUnit(Long planId, String unitName, Integer positionType, Long budget) {
        this.planId = planId;
        this.unitName = unitName;
        this.positionType = positionType;
        this.budget = budget;
        this.unitStatus = CommonStatus.VALID.getStatus();
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }
}
