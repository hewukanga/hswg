package com.jiumai.base.sm.service;

import com.jiumai.base.sm.dto.RelRoleInterfaceDTO;
import com.jiumai.base.sm.dto.RoleDTO;
import com.jiumai.base.sm.entity.RelRoleInterface;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 接口权限关联管理 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2023-08-21
 */
public interface RelRoleInterfaceService extends IService<RelRoleInterface> {

    /**
     * 处理接口权限关联
     * @param roleDTO
     * @param relRoleInterfaceDTOS
     */
    void handleRelRoleInterface(RoleDTO roleDTO, List<RelRoleInterfaceDTO> relRoleInterfaceDTOS);

    /**
     * 处理登录时接口权限关联
     * @param opId
     * @param roleIds
     * @param token
     */
    void handleLoginRelRoleInterface(Long opId, List<Long> roleIds, String token);

    /**
     * 更新登录时存储的接口权限关联关系
     * @param opId
     * @param roleIds
     */
    void updateLoginRelRoleInterface(Long opId, List<Long> roleIds);
}
