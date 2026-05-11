package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.ActivityDTO;
import com.jiumai.base.biz.entity.Activity;
import com.jiumai.base.biz.query.ActivityQuery;
import com.jiumai.base.biz.vo.ActivityVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * activity 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ActivityService extends IService<Activity> {

    /**
     * 分页查询activity
     * @param query
     * @return
     */
    Page<ActivityVO> findActivityPage(ActivityQuery query);

    /**
     * 添加或更新activity
     * @param activityDTO
     * @return
     */
    Long saveOrUpdateActivity(ActivityDTO activityDTO);

    /**
     * 通过id查询成功activity详情
     * @param id
     * @return
     */
    ActivityVO getActivityById(Long id);

    /**
     * 根据ID批量下架活动
     * @param ids
     * @return
     */
    Boolean offlineActivityByIds(List<Long> ids);
}
