package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ModuleOpenAreaConfigDTO;
import com.jiumai.base.biz.entity.ModuleOpenAreaConfig;
import com.jiumai.base.biz.mapper.ModuleOpenAreaConfigMapper;
import com.jiumai.base.biz.query.ModuleOpenAreaConfigQuery;
import com.jiumai.base.biz.service.ModuleOpenAreaConfigService;
import com.jiumai.base.biz.vo.ModuleOpenAreaConfigVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 模块开放区域配置表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class ModuleOpenAreaConfigServiceImpl extends ServiceImpl<ModuleOpenAreaConfigMapper, ModuleOpenAreaConfig> implements ModuleOpenAreaConfigService {

    @Resource
    private ModuleOpenAreaConfigMapper moduleOpenAreaConfigMapper;

    @Override
    public Page<ModuleOpenAreaConfigVO> findModuleOpenAreaConfigPage(ModuleOpenAreaConfigQuery query) {
        Page<ModuleOpenAreaConfigVO> page = new Page<>(query.getPage(), query.getSize());
        List<ModuleOpenAreaConfigVO> list = moduleOpenAreaConfigMapper.findModuleOpenAreaConfigPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateModuleOpenAreaConfig(ModuleOpenAreaConfigDTO moduleOpenAreaConfigDTO) {
        ModuleOpenAreaConfig moduleOpenAreaConfig = new ModuleOpenAreaConfig();

        BeanUtils.copyProperties(moduleOpenAreaConfigDTO, moduleOpenAreaConfig);

        this.saveOrUpdate(moduleOpenAreaConfig);

        return moduleOpenAreaConfig.getId();
    }

    @Override
    public ModuleOpenAreaConfigVO getModuleOpenAreaConfigById(Long id) {
        ModuleOpenAreaConfig moduleOpenAreaConfig = this.getById(id);
        if (CommonFuntions.isEmptyObject(moduleOpenAreaConfig)) {
            throw new BizException("查询失败，模块开放区域配置表不存在");
        }

        ModuleOpenAreaConfigVO moduleOpenAreaConfigVO = new ModuleOpenAreaConfigVO();
        BeanUtils.copyProperties(moduleOpenAreaConfig, moduleOpenAreaConfigVO);
        return moduleOpenAreaConfigVO;
    }
}
