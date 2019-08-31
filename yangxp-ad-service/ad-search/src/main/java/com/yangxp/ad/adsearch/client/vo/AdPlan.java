package com.yangxp.ad.adsearch.client.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: AdPlan
 * @Description: TODO
 * @Auther: yangxp
 * @Date: 2019/8/30 16:05
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_plan")
public class AdPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "plan_name", nullable = false)
    private String planName;

    @Column(name = "plan_status", nullable = false)
    private Integer planStatus;

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "update_time", nullable = false)
    private Date updateTime;


    public AdPlan(Long userId, String planName, Date startTime, Date endTime) {
        this.userId = userId;
        this.planName = planName;
        this.planStatus = 1;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }
}
