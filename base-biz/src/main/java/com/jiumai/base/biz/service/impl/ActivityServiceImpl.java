package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ActivityDTO;
import com.jiumai.base.biz.entity.Activity;
import com.jiumai.base.biz.mapper.ActivityMapper;
import com.jiumai.base.biz.query.ActivityQuery;
import com.jiumai.base.biz.service.ActivityService;
import com.jiumai.base.biz.vo.ActivityVO;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * activity 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    private static final Integer ACTIVITY_STATUS_OFFLINE = 5;

    @Resource
    private ActivityMapper activityMapper;

    @Override
    public Page<ActivityVO> findActivityPage(ActivityQuery query) {
        Page<ActivityVO> page = new Page<>(query.getPage(), query.getSize());
        List<ActivityVO> list = activityMapper.findActivityPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateActivity(ActivityDTO activityDTO) {
        Activity activity = new Activity();

        BeanUtils.copyProperties(activityDTO, activity);

        this.saveOrUpdate(activity);

        return activity.getId();
    }

    @Override
    public ActivityVO getActivityById(Long id) {
        Activity activity = this.getById(id);
        if (CommonFuntions.isEmptyObject(activity)) {
            throw new BizException("查询失败，activity不存在");
        }

        ActivityVO activityVO = new ActivityVO();
        BeanUtils.copyProperties(activity, activityVO);
        return activityVO;
    }

    @Override
    public Boolean offlineActivityByIds(List<Long> ids) {
        Activity activity = new Activity();
        activity.setActivityStatus(ACTIVITY_STATUS_OFFLINE);
        LambdaUpdateWrapper<Activity> wrapper = new LambdaUpdateWrapper<>();
        wrapper.in(Activity::getId, ids);
        return this.update(activity, wrapper);
    }
}
