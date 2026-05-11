package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ExamineTeamDTO;
import com.jiumai.base.biz.entity.ExamineTeam;
import com.jiumai.base.biz.mapper.ExamineTeamMapper;
import com.jiumai.base.biz.query.ExamineTeamQuery;
import com.jiumai.base.biz.service.ExamineTeamService;
import com.jiumai.base.biz.vo.ExamineTeamVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 考核组 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class ExamineTeamServiceImpl extends ServiceImpl<ExamineTeamMapper, ExamineTeam> implements ExamineTeamService {

    @Resource
    private ExamineTeamMapper examineTeamMapper;

    @Override
    public Page<ExamineTeamVO> findExamineTeamPage(ExamineTeamQuery query) {
        Page<ExamineTeamVO> page = new Page<>(query.getPage(), query.getSize());
        List<ExamineTeamVO> list = examineTeamMapper.findExamineTeamPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateExamineTeam(ExamineTeamDTO examineTeamDTO) {
        ExamineTeam examineTeam = new ExamineTeam();

        BeanUtils.copyProperties(examineTeamDTO, examineTeam);

        this.saveOrUpdate(examineTeam);

        return examineTeam.getId();
    }

    @Override
    public ExamineTeamVO getExamineTeamById(Long id) {
        ExamineTeam examineTeam = this.getById(id);
        if (CommonFuntions.isEmptyObject(examineTeam)) {
            throw new BizException("查询失败，考核组不存在");
        }

        ExamineTeamVO examineTeamVO = new ExamineTeamVO();
        BeanUtils.copyProperties(examineTeam, examineTeamVO);
        return examineTeamVO;
    }
}
