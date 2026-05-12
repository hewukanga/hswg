package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ActivitySignUpDTO;
import com.jiumai.base.biz.entity.Activity;
import com.jiumai.base.biz.entity.ActivitySignUp;
import com.jiumai.base.biz.enums.ActivityStatusEnum;
import com.jiumai.base.biz.mapper.ActivitySignUpMapper;
import com.jiumai.base.biz.query.ActivitySignUpQuery;
import com.jiumai.base.biz.service.ActivityService;
import com.jiumai.base.biz.service.ActivitySignUpService;
import com.jiumai.base.biz.vo.ActivitySignUpVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 活动报名表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class ActivitySignUpServiceImpl extends ServiceImpl<ActivitySignUpMapper, ActivitySignUp> implements ActivitySignUpService {

    @Resource
    private ActivitySignUpMapper activitySignUpMapper;

    @Resource
    private ActivityService activityService;

    @Override
    public Page<ActivitySignUpVO> findActivitySignUpPage(ActivitySignUpQuery query) {
        Page<ActivitySignUpVO> page = new Page<>(query.getPage(), query.getSize());
        List<ActivitySignUpVO> list = activitySignUpMapper.findActivitySignUpPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateActivitySignUp(ActivitySignUpDTO activitySignUpDTO) {
        ActivitySignUp activitySignUp = new ActivitySignUp();

        BeanUtils.copyProperties(activitySignUpDTO, activitySignUp);

        this.saveOrUpdate(activitySignUp);

        return activitySignUp.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long signUpActivity(ActivitySignUpDTO activitySignUpDTO) {
        if (CommonFuntions.isEmptyObject(activitySignUpDTO)
                || CommonFuntions.isEmptyObject(activitySignUpDTO.getActivityId())) {
            throw new BizException("活动id不允许为空");
        }
        if (CommonFuntions.isEmptyObject(activitySignUpDTO.getUserId())) {
            throw new BizException("居民id不允许为空");
        }

        Activity activity = activityService.getById(activitySignUpDTO.getActivityId());
        checkActivityCanSignUp(activity);

        LambdaQueryWrapper<ActivitySignUp> repeatWrapper = new LambdaQueryWrapper<>();
        repeatWrapper.eq(ActivitySignUp::getActivityId, activitySignUpDTO.getActivityId())
                .eq(ActivitySignUp::getUserId, activitySignUpDTO.getUserId());
        if (this.count(repeatWrapper) > 0) {
            throw new BizException("请勿重复报名");
        }

        ActivitySignUp activitySignUp = new ActivitySignUp();
        BeanUtils.copyProperties(activitySignUpDTO, activitySignUp);
        activitySignUp.setId(null);
        activitySignUp.setActivityType(activity.getActivityType());
        activitySignUp.setSignUpDate(LocalDateTime.now());
        activitySignUp.setSignInStatus(false);
        activitySignUp.setSignInDate(null);
        if (CommonFuntions.isEmptyObject(activitySignUp.getSource())) {
            activitySignUp.setSource(1);
        }

        LambdaUpdateWrapper<Activity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Activity::getId, activity.getId())
                .and(wrapper -> wrapper.isNull(Activity::getSignUpLimit)
                        .or()
                        .le(Activity::getSignUpLimit, 0)
                        .or()
                        .apply("IFNULL(registered_num, 0) < sign_up_limit"))
                .setSql("registered_num = IFNULL(registered_num, 0) + 1");
        if (!activityService.update(updateWrapper)) {
            throw new BizException("活动报名人数已满");
        }
        if (this.count(repeatWrapper) > 0) {
            throw new BizException("请勿重复报名");
        }

        this.save(activitySignUp);
        return activitySignUp.getId();
    }

    private void checkActivityCanSignUp(Activity activity) {
        if (CommonFuntions.isEmptyObject(activity)) {
            throw new BizException("活动不存在");
        }
        if (!Boolean.TRUE.equals(activity.getOpenSignUp())) {
            throw new BizException("活动未开放报名");
        }
        if (!ActivityStatusEnum.SIGN_UP.getCode().equals(activity.getActivityStatus())) {
            throw new BizException("活动当前不可报名");
        }
        LocalDateTime now = LocalDateTime.now();
        if (activity.getSignUpStartDate() != null && now.isBefore(activity.getSignUpStartDate())) {
            throw new BizException("活动报名未开始");
        }
        if (activity.getSignUpEndDate() != null && now.isAfter(activity.getSignUpEndDate())) {
            throw new BizException("活动报名已结束");
        }
    }

    @Override
    public ActivitySignUpVO getActivitySignUpById(Long id) {
        ActivitySignUp activitySignUp = this.getById(id);
        if (CommonFuntions.isEmptyObject(activitySignUp)) {
            throw new BizException("查询失败，活动报名表不存在");
        }

        ActivitySignUpVO activitySignUpVO = new ActivitySignUpVO();
        BeanUtils.copyProperties(activitySignUp, activitySignUpVO);
        return activitySignUpVO;
    }
}
