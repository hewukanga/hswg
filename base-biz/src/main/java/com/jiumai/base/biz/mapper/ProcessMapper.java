package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.Process;
import com.jiumai.base.biz.query.ProcessQuery;
import com.jiumai.base.biz.vo.ProcessVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 流程表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ProcessMapper extends BaseMapper<Process> {

    /**
     * 分页查询流程表
     * @param page
     * @param query
     * @return
     */
    List<ProcessVO> findProcessPage(Page<ProcessVO> page, @Param("query") ProcessQuery query);
}
