package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.LawEnforcementInstrumentManageDTO;
import com.jiumai.base.biz.entity.LawEnforcementInstrumentManage;
import com.jiumai.base.biz.mapper.LawEnforcementInstrumentManageMapper;
import com.jiumai.base.biz.query.LawEnforcementInstrumentManageQuery;
import com.jiumai.base.biz.service.LawEnforcementInstrumentManageService;
import com.jiumai.base.biz.vo.LawEnforcementInstrumentManageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 执法仪管理 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class LawEnforcementInstrumentManageServiceImpl extends ServiceImpl<LawEnforcementInstrumentManageMapper, LawEnforcementInstrumentManage> implements LawEnforcementInstrumentManageService {

    @Resource
    private LawEnforcementInstrumentManageMapper lawEnforcementInstrumentManageMapper;

    @Override
    public Page<LawEnforcementInstrumentManageVO> findLawEnforcementInstrumentManagePage(LawEnforcementInstrumentManageQuery query) {
        Page<LawEnforcementInstrumentManageVO> page = new Page<>(query.getPage(), query.getSize());
        List<LawEnforcementInstrumentManageVO> list = lawEnforcementInstrumentManageMapper.findLawEnforcementInstrumentManagePage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateLawEnforcementInstrumentManage(LawEnforcementInstrumentManageDTO lawEnforcementInstrumentManageDTO) {
        LawEnforcementInstrumentManage lawEnforcementInstrumentManage = new LawEnforcementInstrumentManage();

        BeanUtils.copyProperties(lawEnforcementInstrumentManageDTO, lawEnforcementInstrumentManage);

        this.saveOrUpdate(lawEnforcementInstrumentManage);

        return lawEnforcementInstrumentManage.getId();
    }

    @Override
    public LawEnforcementInstrumentManageVO getLawEnforcementInstrumentManageById(Long id) {
        LawEnforcementInstrumentManage lawEnforcementInstrumentManage = this.getById(id);
        if (CommonFuntions.isEmptyObject(lawEnforcementInstrumentManage)) {
            throw new BizException("查询失败，执法仪管理不存在");
        }

        LawEnforcementInstrumentManageVO lawEnforcementInstrumentManageVO = new LawEnforcementInstrumentManageVO();
        BeanUtils.copyProperties(lawEnforcementInstrumentManage, lawEnforcementInstrumentManageVO);
        return lawEnforcementInstrumentManageVO;
    }
}
