package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.ModuleOpenAreaConfigDTO;
import com.jiumai.base.biz.entity.ModuleOpenAreaConfig;
import com.jiumai.base.biz.query.ModuleOpenAreaConfigQuery;
import com.jiumai.base.biz.vo.ModuleOpenAreaConfigVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 模块开放区域配置表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ModuleOpenAreaConfigService extends IService<ModuleOpenAreaConfig> {

    /**
     * 分页查询模块开放区域配置表
     * @param query
     * @return
     */
    Page<ModuleOpenAreaConfigVO> findModuleOpenAreaConfigPage(ModuleOpenAreaConfigQuery query);

    /**
     * 添加或更新模块开放区域配置表
     * @param moduleOpenAreaConfigDTO
     * @return
     */
    Long saveOrUpdateModuleOpenAreaConfig(ModuleOpenAreaConfigDTO moduleOpenAreaConfigDTO);

    /**
     * 通过id查询成功模块开放区域配置表详情
     * @param id
     * @return
     */
    ModuleOpenAreaConfigVO getModuleOpenAreaConfigById(Long id);
}
