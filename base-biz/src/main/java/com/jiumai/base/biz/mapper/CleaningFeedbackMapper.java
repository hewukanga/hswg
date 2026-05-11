package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.CleaningFeedback;
import com.jiumai.base.biz.query.CleaningFeedbackQuery;
import com.jiumai.base.biz.vo.CleaningFeedbackVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 保洁反馈 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface CleaningFeedbackMapper extends BaseMapper<CleaningFeedback> {

    /**
     * 分页查询保洁反馈
     * @param page
     * @param query
     * @return
     */
    List<CleaningFeedbackVO> findCleaningFeedbackPage(Page<CleaningFeedbackVO> page, @Param("query") CleaningFeedbackQuery query);
}
