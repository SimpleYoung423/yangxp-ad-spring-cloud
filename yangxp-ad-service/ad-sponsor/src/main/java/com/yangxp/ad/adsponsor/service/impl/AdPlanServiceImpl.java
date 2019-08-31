package com.yangxp.ad.adsponsor.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.yangxp.ad.adcommon.exception.AdException;
import com.yangxp.ad.adsponsor.constant.CommonStatus;
import com.yangxp.ad.adsponsor.constant.Constants;
import com.yangxp.ad.adsponsor.dao.AdPlanRepository;
import com.yangxp.ad.adsponsor.dao.AdUserRepository;
import com.yangxp.ad.adsponsor.entity.AdPlan;
import com.yangxp.ad.adsponsor.entity.AdUser;
import com.yangxp.ad.adsponsor.service.IAdPlanService;
import com.yangxp.ad.adsponsor.vo.AdPlanGetRequest;
import com.yangxp.ad.adsponsor.vo.AdPlanRequest;
import com.yangxp.ad.adsponsor.vo.AdPlanResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * {@link AdPlanServiceImpl}
 *
 * @Description: TODO
 * @Author: yangxp
 * @Date: 2019/8/30 18:39
 * @Version 1.0
 */
@Service
@Slf4j
public class AdPlanServiceImpl implements IAdPlanService {

    private final AdUserRepository userRepository;
    private final AdPlanRepository planRepository;

    public AdPlanServiceImpl(AdUserRepository userRepository, AdPlanRepository planRepository) {
        this.userRepository = userRepository;
        this.planRepository = planRepository;
    }


    @Override
    @Transactional
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {

        if (!request.createValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        //确保关联 User 是存在的
        Optional<AdUser> adUser = userRepository.findById(request.getUserId());

        if (!adUser.isPresent()) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        AdPlan oldPlan = planRepository.findByUserIdAndPlanName(request.getUserId(), request.getPlanName());

        if (ObjectUtil.isNotNull(oldPlan)) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_PLAN_ERROR);
        }

        AdPlan newPlan = planRepository.save(new AdPlan(
                request.getUserId(),
                request.getPlanName(),
                DateUtil.parseTime(request.getStartTime()),
                DateUtil.parseTime(request.getEndTime())));


        return new AdPlanResponse(newPlan.getId(), newPlan.getPlanName());
    }


    @Override
    public List<AdPlan> getAdPlansByIds(AdPlanGetRequest getRequest) throws AdException {

        if (!getRequest.validate()) {

            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        return planRepository.findAllByIdInAndUserId(getRequest.getIds(), getRequest.getUserId());
    }


    @Override
    @Transactional
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {

        if (!request.updateValidate()) {

            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan plan = planRepository.findByIdAndUserId(request.getId(), request.getUserId());

        if (ObjectUtil.isNull(plan)) {

            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        if (ObjectUtil.isNotNull(request.getPlanName())) {

            plan.setPlanName(request.getPlanName());
        }
        if (ObjectUtil.isNotNull(request.getStartTime())) {

            plan.setStartTime(DateUtil.parse(request.getStartTime()));
        }
        if (ObjectUtil.isNotNull(request.getEndTime())) {

            plan.setEndTime(DateUtil.parse(request.getEndTime()));
        }

        plan.setUpdateTime(DateUtil.date());
        plan = planRepository.save(plan);

        return new AdPlanResponse(plan.getId(), plan.getPlanName());
    }


    @Override
    @Transactional
    public void deleteAdPlan(AdPlanRequest request) throws AdException {

        if (!request.deleteValidate()) {

            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan plan = planRepository.findByIdAndUserId(request.getId(), request.getUserId());

        if (ObjectUtil.isNull(plan)) {

            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        plan.setPlanStatus(CommonStatus.INVALID.getStatus());
        plan.setUpdateTime(DateUtil.date());

        planRepository.save(plan);

    }
}
