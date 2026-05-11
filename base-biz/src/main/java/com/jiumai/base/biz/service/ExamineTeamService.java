package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.ExamineTeamDTO;
import com.jiumai.base.biz.entity.ExamineTeam;
import com.jiumai.base.biz.query.ExamineTeamQuery;
import com.jiumai.base.biz.vo.ExamineTeamVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 考核组 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ExamineTeamService extends IService<ExamineTeam> {

    /**
     * 分页查询考核组
     * @param query
     * @return
     */
    Page<ExamineTeamVO> findExamineTeamPage(ExamineTeamQuery query);

    /**
     * 添加或更新考核组
     * @param examineTeamDTO
     * @return
     */
    Long saveOrUpdateExamineTeam(ExamineTeamDTO examineTeamDTO);

    /**
     * 通过id查询成功考核组详情
     * @param id
     * @return
     */
    ExamineTeamVO getExamineTeamById(Long id);
}
