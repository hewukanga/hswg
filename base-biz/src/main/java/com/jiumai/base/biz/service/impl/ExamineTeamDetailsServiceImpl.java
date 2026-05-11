package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ExamineTeamDetailsDTO;
import com.jiumai.base.biz.entity.ExamineTeamDetails;
import com.jiumai.base.biz.mapper.ExamineTeamDetailsMapper;
import com.jiumai.base.biz.query.ExamineTeamDetailsQuery;
import com.jiumai.base.biz.service.ExamineTeamDetailsService;
import com.jiumai.base.biz.vo.ExamineTeamDetailsVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 考核组详情 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class ExamineTeamDetailsServiceImpl extends ServiceImpl<ExamineTeamDetailsMapper, ExamineTeamDetails> implements ExamineTeamDetailsService {

    @Resource
    private ExamineTeamDetailsMapper examineTeamDetailsMapper;

    @Override
    public Page<ExamineTeamDetailsVO> findExamineTeamDetailsPage(ExamineTeamDetailsQuery query) {
        Page<ExamineTeamDetailsVO> page = new Page<>(query.getPage(), query.getSize());
        List<ExamineTeamDetailsVO> list = examineTeamDetailsMapper.findExamineTeamDetailsPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateExamineTeamDetails(ExamineTeamDetailsDTO examineTeamDetailsDTO) {
        ExamineTeamDetails examineTeamDetails = new ExamineTeamDetails();

        BeanUtils.copyProperties(examineTeamDetailsDTO, examineTeamDetails);

        this.saveOrUpdate(examineTeamDetails);

        return examineTeamDetails.getId();
    }

    @Override
    public ExamineTeamDetailsVO getExamineTeamDetailsById(Long id) {
        ExamineTeamDetails examineTeamDetails = this.getById(id);
        if (CommonFuntions.isEmptyObject(examineTeamDetails)) {
            throw new BizException("查询失败，考核组详情不存在");
        }

        ExamineTeamDetailsVO examineTeamDetailsVO = new ExamineTeamDetailsVO();
        BeanUtils.copyProperties(examineTeamDetails, examineTeamDetailsVO);
        return examineTeamDetailsVO;
    }
}
