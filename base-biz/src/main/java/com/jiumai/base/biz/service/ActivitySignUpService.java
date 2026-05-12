package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.ActivitySignUpDTO;
import com.jiumai.base.biz.entity.ActivitySignUp;
import com.jiumai.base.biz.query.ActivitySignUpQuery;
import com.jiumai.base.biz.vo.ActivitySignUpVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 活动报名表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ActivitySignUpService extends IService<ActivitySignUp> {

    /**
     * 分页查询活动报名表
     * @param query
     * @return
     */
    Page<ActivitySignUpVO> findActivitySignUpPage(ActivitySignUpQuery query);

    /**
     * 添加或更新活动报名表
     * @param activitySignUpDTO
     * @return
     */
    Long saveOrUpdateActivitySignUp(ActivitySignUpDTO activitySignUpDTO);

    /**
     * Resident activity sign-up.
     * @param activitySignUpDTO sign-up information
     * @return sign-up id
     */
    Long signUpActivity(ActivitySignUpDTO activitySignUpDTO);

    /**
     * 通过id查询成功活动报名表详情
     * @param id
     * @return
     */
    ActivitySignUpVO getActivitySignUpById(Long id);
}
