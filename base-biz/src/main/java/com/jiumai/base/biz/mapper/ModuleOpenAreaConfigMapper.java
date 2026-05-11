package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.ModuleOpenAreaConfig;
import com.jiumai.base.biz.query.ModuleOpenAreaConfigQuery;
import com.jiumai.base.biz.vo.ModuleOpenAreaConfigVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 模块开放区域配置表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ModuleOpenAreaConfigMapper extends BaseMapper<ModuleOpenAreaConfig> {

    /**
     * 分页查询模块开放区域配置表
     * @param page
     * @param query
     * @return
     */
    List<ModuleOpenAreaConfigVO> findModuleOpenAreaConfigPage(Page<ModuleOpenAreaConfigVO> page, @Param("query") ModuleOpenAreaConfigQuery query);
}
