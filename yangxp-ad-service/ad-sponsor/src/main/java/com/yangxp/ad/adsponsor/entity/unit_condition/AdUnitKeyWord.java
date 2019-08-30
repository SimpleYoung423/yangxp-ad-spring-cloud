package com.yangxp.ad.adsponsor.entity.unit_condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @ClassName: AdUnitKeyWord
 * @Description: TODO
 * @Auther: yangxp
 * @Date: 2019/8/30 16:24
 * @Version 1.0
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_unit_keyword")
public class AdUnitKeyWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "unit_id",nullable = false)
    private Long unitId;

    @Column(name = "keyword",nullable = false)
    private String keyword;


    public AdUnitKeyWord(Long unitId, String keyword) {
        this.unitId = unitId;
        this.keyword = keyword;
    }
}
