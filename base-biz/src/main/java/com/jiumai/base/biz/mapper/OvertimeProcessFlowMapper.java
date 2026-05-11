package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.OvertimeProcessFlow;
import com.jiumai.base.biz.query.OvertimeProcessFlowQuery;
import com.jiumai.base.biz.vo.OvertimeProcessFlowVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 加班流程表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface OvertimeProcessFlowMapper extends BaseMapper<OvertimeProcessFlow> {

    /**
     * 分页查询加班流程表
     * @param page
     * @param query
     * @return
     */
    List<OvertimeProcessFlowVO> findOvertimeProcessFlowPage(Page<OvertimeProcessFlowVO> page, @Param("query") OvertimeProcessFlowQuery query);
}
