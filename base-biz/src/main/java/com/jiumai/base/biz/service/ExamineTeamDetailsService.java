package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.ExamineTeamDetailsDTO;
import com.jiumai.base.biz.entity.ExamineTeamDetails;
import com.jiumai.base.biz.query.ExamineTeamDetailsQuery;
import com.jiumai.base.biz.vo.ExamineTeamDetailsVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 考核组详情 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ExamineTeamDetailsService extends IService<ExamineTeamDetails> {

    /**
     * 分页查询考核组详情
     * @param query
     * @return
     */
    Page<ExamineTeamDetailsVO> findExamineTeamDetailsPage(ExamineTeamDetailsQuery query);

    /**
     * 添加或更新考核组详情
     * @param examineTeamDetailsDTO
     * @return
     */
    Long saveOrUpdateExamineTeamDetails(ExamineTeamDetailsDTO examineTeamDetailsDTO);

    /**
     * 通过id查询成功考核组详情详情
     * @param id
     * @return
     */
    ExamineTeamDetailsVO getExamineTeamDetailsById(Long id);
}
