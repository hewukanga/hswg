package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.NewIntegralConfig;
import com.jiumai.base.biz.query.NewIntegralConfigQuery;
import com.jiumai.base.biz.vo.NewIntegralConfigVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 新积分配置表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface NewIntegralConfigMapper extends BaseMapper<NewIntegralConfig> {

    /**
     * 分页查询新积分配置表
     * @param page
     * @param query
     * @return
     */
    List<NewIntegralConfigVO> findNewIntegralConfigPage(Page<NewIntegralConfigVO> page, @Param("query") NewIntegralConfigQuery query);
}
