package com.yangxp.ad.adsponsor.entity;

import com.yangxp.ad.adsponsor.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: AdUser
 * @Description: TODO
 * @Auther: yangxp
 * @Date: 2019/8/30 15:44
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_user")
public class AdUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "token",nullable = false)
    public String token;

    @Column(name = "user_status",nullable = false)
    public Integer userStatus;

    @Column(name = "create_time",nullable = false)
    public Date createTime;

    @Column(name = "update_time",nullable = false)
    public Date updateTime;

    /**
     * @Description <p>初始构造</p>
     * @Author yangxp
     * @Date  2019/8/30 16:03
     * @Param [username, token]
     * @return
     **/
    public AdUser(String username, String token) {
        this.username = username;
        this.token = token;
        this.userStatus = CommonStatus.VALID.getStatus();
        this.createTime  = new Date();
        this.updateTime = this.createTime;

    }
}
