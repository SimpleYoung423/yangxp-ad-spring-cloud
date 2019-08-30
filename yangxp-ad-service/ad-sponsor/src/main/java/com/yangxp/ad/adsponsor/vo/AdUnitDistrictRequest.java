package com.yangxp.ad.adsponsor.vo;

import com.yangxp.ad.adsponsor.entity.AdUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * {@link AdUnitDistrictRequest}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/30 19:49
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitDistrictRequest {

    private List<UnitDistrict> unitDistricts;



    public static class UnitDistrict{

        private Long unitId;
        private String province;
        private String city;

    }


}
