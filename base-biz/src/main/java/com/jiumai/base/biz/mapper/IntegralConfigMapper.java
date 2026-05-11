package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.IntegralConfig;
import com.jiumai.base.biz.query.IntegralConfigQuery;
import com.jiumai.base.biz.vo.IntegralConfigVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 积分配置表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface IntegralConfigMapper extends BaseMapper<IntegralConfig> {

    /**
     * 分页查询积分配置表
     * @param page
     * @param query
     * @return
     */
    List<IntegralConfigVO> findIntegralConfigPage(Page<IntegralConfigVO> page, @Param("query") IntegralConfigQuery query);
}
