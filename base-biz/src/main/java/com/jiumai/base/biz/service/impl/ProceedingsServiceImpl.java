package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ProceedingsDTO;
import com.jiumai.base.biz.entity.Proceedings;
import com.jiumai.base.biz.mapper.ProceedingsMapper;
import com.jiumai.base.biz.query.ProceedingsQuery;
import com.jiumai.base.biz.service.ProceedingsService;
import com.jiumai.base.biz.vo.ProceedingsVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 会议议事 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class ProceedingsServiceImpl extends ServiceImpl<ProceedingsMapper, Proceedings> implements ProceedingsService {

    @Resource
    private ProceedingsMapper proceedingsMapper;

    @Override
    public Page<ProceedingsVO> findProceedingsPage(ProceedingsQuery query) {
        Page<ProceedingsVO> page = new Page<>(query.getPage(), query.getSize());
        List<ProceedingsVO> list = proceedingsMapper.findProceedingsPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateProceedings(ProceedingsDTO proceedingsDTO) {
        Proceedings proceedings = new Proceedings();

        BeanUtils.copyProperties(proceedingsDTO, proceedings);

        this.saveOrUpdate(proceedings);

        return proceedings.getId();
    }

    @Override
    public ProceedingsVO getProceedingsById(Long id) {
        Proceedings proceedings = this.getById(id);
        if (CommonFuntions.isEmptyObject(proceedings)) {
            throw new BizException("查询失败，会议议事不存在");
        }

        ProceedingsVO proceedingsVO = new ProceedingsVO();
        BeanUtils.copyProperties(proceedings, proceedingsVO);
        return proceedingsVO;
    }
}
