package com.jiumai.base.sm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jiumai.base.sm.dto.AreaDTO;
import com.jiumai.base.sm.entity.SmArea;
import com.jiumai.base.sm.query.AreaQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 区域 Mapper 接口
 * </p>
 *
 * @author mysql gen
 * @since 2023-01-09
 */
public interface SmAreaMapper extends BaseMapper<SmArea> {

    /**
     * 根据用户Id查找可访问区域
     * @param opId
     * @return
     */
    List<AreaDTO> findAreaListByOpId(Long opId);

    /**
     * 查询有效的区域集合
     * @return
     */
    List<AreaDTO> findAreaList();

    /**
     * 分页查询区域
     * @param page
     * @param query
     * @return
     */
    IPage<AreaDTO> findAreaPaging(@Param("page") IPage<AreaDTO> page, @Param("query") AreaQuery query);
}
