package com.jiumai.base.sm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiumai.base.sm.entity.SmRelOpOrg;
import com.jiumai.base.sm.entity.SmRelOrgRole;

import java.util.List;

public interface RelOrgRoleService extends IService<SmRelOrgRole> {
    /**
     * 保存数据
     *
     * @param relOrgRole
     * @return
     * @throws Exception
     */
    long saveSmRelOrgRole(SmRelOrgRole relOrgRole) throws Exception;

    /**
     * 通过角色Id查询组织
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    List<SmRelOrgRole> findRelOrgRoleByRoleId(long roleId) throws Exception;

    /**
     * 通过角色ID与组织ID查询关系
     *
     * @param roleId
     * @param orgId
     * @return
     * @throws Exception
     */
    SmRelOrgRole getRelOrgRoleByRoleIdAndOrgId(long roleId, long orgId) throws Exception;

    /**
     * 删除
     *
     * @throws Exception
     */
    int deleteRelOrgRole(SmRelOrgRole relOrgRole) throws Exception;
}
