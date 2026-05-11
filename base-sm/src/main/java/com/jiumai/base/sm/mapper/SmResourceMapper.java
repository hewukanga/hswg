package com.jiumai.base.sm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jiumai.base.common.core.query.PageParam;
import com.jiumai.base.sm.dto.OperatorDTO;
import com.jiumai.base.sm.dto.ResourceDTO;
import com.jiumai.base.sm.entity.SmResource;
import com.jiumai.base.sm.query.OperatorQuery;
import com.jiumai.base.sm.query.ResourceQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 功能权限 Mapper 接口
 * </p>
 *
 * @author mysql gen
 * @since 2023-01-09
 */
public interface SmResourceMapper extends BaseMapper<SmResource> {

    /**
     * 根据操作员ID查询操作员权限
     * @param opId
     * @return
     */
    List<ResourceDTO> getByOpId(@Param("opId") Long opId);

    /**
     * 根据操作员ID查询操作员按钮权限
     * @param opId
     * @return
     */
    List<ResourceDTO> getBtnByOpId(@Param("resId") long resId, @Param("opId") long opId) throws Exception;

    /**
     * 获取操作员按钮权限
     * @param opId
     * @return
     */
    List<ResourceDTO> findOperatorBtn(@Param("opId") Long opId);

    /**
     * 根据操作员ID查询操作员菜单权限
     * @param opId
     * @return
     */
    List<ResourceDTO> getMenuByOpId(@Param("opId") Long opId);

    /**
     * 分页查询菜单
     * @param page
     * @param query
     * @return
     */
    IPage<ResourceDTO> findResourcePaging(@Param("page") IPage<ResourceDTO> page, @Param("query") ResourceQuery query);

}
