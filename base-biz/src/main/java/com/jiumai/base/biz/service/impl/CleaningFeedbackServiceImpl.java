package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.CleaningFeedbackDTO;
import com.jiumai.base.biz.entity.CleaningFeedback;
import com.jiumai.base.biz.mapper.CleaningFeedbackMapper;
import com.jiumai.base.biz.query.CleaningFeedbackQuery;
import com.jiumai.base.biz.service.CleaningFeedbackService;
import com.jiumai.base.biz.vo.CleaningFeedbackVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 保洁反馈 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class CleaningFeedbackServiceImpl extends ServiceImpl<CleaningFeedbackMapper, CleaningFeedback> implements CleaningFeedbackService {

    @Resource
    private CleaningFeedbackMapper cleaningFeedbackMapper;

    @Override
    public Page<CleaningFeedbackVO> findCleaningFeedbackPage(CleaningFeedbackQuery query) {
        Page<CleaningFeedbackVO> page = new Page<>(query.getPage(), query.getSize());
        List<CleaningFeedbackVO> list = cleaningFeedbackMapper.findCleaningFeedbackPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateCleaningFeedback(CleaningFeedbackDTO cleaningFeedbackDTO) {
        CleaningFeedback cleaningFeedback = new CleaningFeedback();

        BeanUtils.copyProperties(cleaningFeedbackDTO, cleaningFeedback);

        this.saveOrUpdate(cleaningFeedback);

        return cleaningFeedback.getId();
    }

    @Override
    public CleaningFeedbackVO getCleaningFeedbackById(Long id) {
        CleaningFeedback cleaningFeedback = this.getById(id);
        if (CommonFuntions.isEmptyObject(cleaningFeedback)) {
            throw new BizException("查询失败，保洁反馈不存在");
        }

        CleaningFeedbackVO cleaningFeedbackVO = new CleaningFeedbackVO();
        BeanUtils.copyProperties(cleaningFeedback, cleaningFeedbackVO);
        return cleaningFeedbackVO;
    }
}
