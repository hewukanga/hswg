package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PropertyArea;
import com.jiumai.base.biz.query.PropertyAreaQuery;
import com.jiumai.base.biz.vo.PropertyAreaVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 物业区域表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PropertyAreaMapper extends BaseMapper<PropertyArea> {

    /**
     * 分页查询物业区域表
     * @param page
     * @param query
     * @return
     */
    List<PropertyAreaVO> findPropertyAreaPage(Page<PropertyAreaVO> page, @Param("query") PropertyAreaQuery query);
}
