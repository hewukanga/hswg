package com.jiumai.base.sm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jiumai.base.sm.dto.AreaDTO;
import com.jiumai.base.sm.dto.OperatorDTO;
import com.jiumai.base.sm.entity.SmOperator;
import com.jiumai.base.sm.query.AreaQuery;
import com.jiumai.base.sm.query.OperatorQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 操作员 Mapper 接口
 * </p>
 *
 * @author mysql gen
 * @since 2023-01-09
 */
public interface SmOperatorMapper extends BaseMapper<SmOperator> {


    /**
     * 根据角色id获取操作员信息
     * @param roleId
     * @return
     */
    List<SmOperator> findOperatorByRoleId(@Param("roleId") Long roleId);

    /**
     * 查询操作员分页列表
     * @param page
     * @param query
     * @return
     */
    IPage<OperatorDTO> findOperatorPaging(@Param("page") IPage<OperatorDTO> page, @Param("query") OperatorQuery query);

}
