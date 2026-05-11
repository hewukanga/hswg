package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.CleaningArea;
import com.jiumai.base.biz.query.CleaningAreaQuery;
import com.jiumai.base.biz.vo.CleaningAreaVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 保洁区域管理 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface CleaningAreaMapper extends BaseMapper<CleaningArea> {

    /**
     * 分页查询保洁区域管理
     * @param page
     * @param query
     * @return
     */
    List<CleaningAreaVO> findCleaningAreaPage(Page<CleaningAreaVO> page, @Param("query") CleaningAreaQuery query);
}
