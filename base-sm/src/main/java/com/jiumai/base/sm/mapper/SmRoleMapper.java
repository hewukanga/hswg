package com.jiumai.base.sm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiumai.base.sm.dto.RoleDTO;
import com.jiumai.base.sm.entity.SmRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author mysql gen
 * @since 2023-01-09
 */
public interface SmRoleMapper extends BaseMapper<SmRole> {

    /**
     * 查询父类角色
     * @param parentId
     * @return
     */
    RoleDTO getRoleByPrentId(@Param("parentId") Long parentId);

    /**
     * 修改时查询角色名是否重复
     * @param role
     * @return
     */
    Integer ifRepeatNameUpdate(@Param("role") RoleDTO role);
}
