package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.NewIntegralConfigDTO;
import com.jiumai.base.biz.entity.NewIntegralConfig;
import com.jiumai.base.biz.query.NewIntegralConfigQuery;
import com.jiumai.base.biz.vo.NewIntegralConfigVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 新积分配置表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface NewIntegralConfigService extends IService<NewIntegralConfig> {

    /**
     * 分页查询新积分配置表
     * @param query
     * @return
     */
    Page<NewIntegralConfigVO> findNewIntegralConfigPage(NewIntegralConfigQuery query);

    /**
     * 添加或更新新积分配置表
     * @param newIntegralConfigDTO
     * @return
     */
    Long saveOrUpdateNewIntegralConfig(NewIntegralConfigDTO newIntegralConfigDTO);

    /**
     * 通过id查询成功新积分配置表详情
     * @param id
     * @return
     */
    NewIntegralConfigVO getNewIntegralConfigById(Long id);
}
