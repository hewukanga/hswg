package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.CleaningFeedbackDTO;
import com.jiumai.base.biz.entity.CleaningFeedback;
import com.jiumai.base.biz.query.CleaningFeedbackQuery;
import com.jiumai.base.biz.vo.CleaningFeedbackVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 保洁反馈 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface CleaningFeedbackService extends IService<CleaningFeedback> {

    /**
     * 分页查询保洁反馈
     * @param query
     * @return
     */
    Page<CleaningFeedbackVO> findCleaningFeedbackPage(CleaningFeedbackQuery query);

    /**
     * 添加或更新保洁反馈
     * @param cleaningFeedbackDTO
     * @return
     */
    Long saveOrUpdateCleaningFeedback(CleaningFeedbackDTO cleaningFeedbackDTO);

    /**
     * 通过id查询成功保洁反馈详情
     * @param id
     * @return
     */
    CleaningFeedbackVO getCleaningFeedbackById(Long id);
}
