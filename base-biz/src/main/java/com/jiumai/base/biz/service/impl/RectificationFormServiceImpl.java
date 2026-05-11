package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.RectificationFormDTO;
import com.jiumai.base.biz.entity.RectificationForm;
import com.jiumai.base.biz.mapper.RectificationFormMapper;
import com.jiumai.base.biz.query.RectificationFormQuery;
import com.jiumai.base.biz.service.RectificationFormService;
import com.jiumai.base.biz.vo.RectificationFormVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 整改单 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class RectificationFormServiceImpl extends ServiceImpl<RectificationFormMapper, RectificationForm> implements RectificationFormService {

    @Resource
    private RectificationFormMapper rectificationFormMapper;

    @Override
    public Page<RectificationFormVO> findRectificationFormPage(RectificationFormQuery query) {
        Page<RectificationFormVO> page = new Page<>(query.getPage(), query.getSize());
        List<RectificationFormVO> list = rectificationFormMapper.findRectificationFormPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateRectificationForm(RectificationFormDTO rectificationFormDTO) {
        RectificationForm rectificationForm = new RectificationForm();

        BeanUtils.copyProperties(rectificationFormDTO, rectificationForm);

        this.saveOrUpdate(rectificationForm);

        return rectificationForm.getId();
    }

    @Override
    public RectificationFormVO getRectificationFormById(Long id) {
        RectificationForm rectificationForm = this.getById(id);
        if (CommonFuntions.isEmptyObject(rectificationForm)) {
            throw new BizException("查询失败，整改单不存在");
        }

        RectificationFormVO rectificationFormVO = new RectificationFormVO();
        BeanUtils.copyProperties(rectificationForm, rectificationFormVO);
        return rectificationFormVO;
    }
}
