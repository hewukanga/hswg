package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.IntegralConfigDTO;
import com.jiumai.base.biz.entity.IntegralConfig;
import com.jiumai.base.biz.query.IntegralConfigQuery;
import com.jiumai.base.biz.vo.IntegralConfigVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 积分配置表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface IntegralConfigService extends IService<IntegralConfig> {

    /**
     * 分页查询积分配置表
     * @param query
     * @return
     */
    Page<IntegralConfigVO> findIntegralConfigPage(IntegralConfigQuery query);

    /**
     * 添加或更新积分配置表
     * @param integralConfigDTO
     * @return
     */
    Long saveOrUpdateIntegralConfig(IntegralConfigDTO integralConfigDTO);

    /**
     * 通过id查询成功积分配置表详情
     * @param id
     * @return
     */
    IntegralConfigVO getIntegralConfigById(Long id);
}
