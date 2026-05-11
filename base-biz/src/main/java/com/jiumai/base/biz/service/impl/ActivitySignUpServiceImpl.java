package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ActivitySignUpDTO;
import com.jiumai.base.biz.entity.ActivitySignUp;
import com.jiumai.base.biz.mapper.ActivitySignUpMapper;
import com.jiumai.base.biz.query.ActivitySignUpQuery;
import com.jiumai.base.biz.service.ActivitySignUpService;
import com.jiumai.base.biz.vo.ActivitySignUpVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
