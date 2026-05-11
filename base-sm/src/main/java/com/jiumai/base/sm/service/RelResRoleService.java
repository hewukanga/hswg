package com.jiumai.base.sm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiumai.base.sm.entity.SmRelOrgRole;
import com.jiumai.base.sm.entity.SmRelResRole;

import java.util.List;

public interface RelResRoleService extends IService<SmRelResRole> {
    /**
     * 保存数据
     *
     * @param relResRole
     * @return
     * @throws Exception
     */
    long saveSmRelResRole(SmRelResRole relResRole) throws Exception;

    /**
     * 根据角色ID查询关联权限Id
     *
     * @return
     * @throws Exception
     */
    List<Long> findResIdsByRoleId(long roleId) throws Exception;

    /**
     * 通过角色ID与权限ID查询关系
     *
     * @param resId
     * @param roleId
     * @return
     * @throws Exception
     */
    SmRelResRole getRelResRoleByRoleIdAndResId(long roleId, long resId) throws Exception;

    /**
     * 删除
     *
     * @param relResRole
     * @throws Exception
     */
    int deleteRelResRole(SmRelResRole relResRole) throws Exception;

    /**
     * 查看权限是否与角色之间关系
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    List<SmRelResRole> findRelResRoleByRoleId(long roleId) throws Exception;

    /**
     * 根据角色ID取得原有的已过期菜单
     *
     * @param roleId
     * @return
     */
    List<String> findExpieResIdsByRoleId(Long roleId);

    /**
     * 更新角色菜单关系
     *
     * @param rrr
     */
    void updateByResIdByRoleId(SmRelResRole rrr);


}
