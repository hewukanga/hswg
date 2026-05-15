package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.WorkResult;
import com.jiumai.base.biz.query.WorkResultQuery;
import com.jiumai.base.biz.vo.WorkResultVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 工作成果 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-15
 */
public interface WorkResultMapper extends BaseMapper<WorkResult> {

    /**
     * 分页查询工作成果
     * @param page
     * @param query
     * @return
     */
    List<WorkResultVO> findWorkResultPage(Page<WorkResultVO> page, @Param("query") WorkResultQuery query);
}