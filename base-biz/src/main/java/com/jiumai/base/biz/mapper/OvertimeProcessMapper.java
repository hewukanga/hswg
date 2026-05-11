package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.OvertimeProcess;
import com.jiumai.base.biz.query.OvertimeProcessQuery;
import com.jiumai.base.biz.vo.OvertimeProcessVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 加班记录表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface OvertimeProcessMapper extends BaseMapper<OvertimeProcess> {

    /**
     * 分页查询加班记录表
     * @param page
     * @param query
     * @return
     */
    List<OvertimeProcessVO> findOvertimeProcessPage(Page<OvertimeProcessVO> page, @Param("query") OvertimeProcessQuery query);
}
