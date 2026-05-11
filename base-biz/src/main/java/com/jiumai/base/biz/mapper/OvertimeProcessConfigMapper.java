package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.OvertimeProcessConfig;
import com.jiumai.base.biz.query.OvertimeProcessConfigQuery;
import com.jiumai.base.biz.vo.OvertimeProcessConfigVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 加班配置表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface OvertimeProcessConfigMapper extends BaseMapper<OvertimeProcessConfig> {

    /**
     * 分页查询加班配置表
     * @param page
     * @param query
     * @return
     */
    List<OvertimeProcessConfigVO> findOvertimeProcessConfigPage(Page<OvertimeProcessConfigVO> page, @Param("query") OvertimeProcessConfigQuery query);
}
