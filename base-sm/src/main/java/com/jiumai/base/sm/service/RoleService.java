/*
 * RoleService.java Created on 2016年10月25日 下午8:36:14
 * Copyright (c) 2018 Hangzhou JiuMai Network Technology Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jiumai.base.sm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiumai.base.common.core.enums.IsValidEnum;
import com.jiumai.base.sm.dto.RoleDTO;
import com.jiumai.base.sm.entity.SmRole;
import com.jiumai.base.sm.query.RoleQuery;

import java.util.List;

/**
 * 角色服务接口
 *
 * @author louis<8 3 0 3 0 2 4 6 @ qq.com>
 * @version 1.0
 * @date 2018-02-12
 */
public interface RoleService extends IService<SmRole> {

    /**
     * 查询所有有效角色
     *
     * @return
     * @throws Exception
     */
    List<RoleDTO> findRoleList(Long opId) throws Exception;

    /**
     * 角色分页查询
     *
     * @param roleQuery
     * @return
     * @throws Exception
     */
    IPage<RoleDTO> findRolePaging(RoleQuery roleQuery) throws Exception;

    /**
     * 查询角色
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    RoleDTO findRoleById(Long roleId) throws Exception;

    /**
     * 查询父类角色
     *
     * @param parentId
     * @return
     * @throws Exception
     */
    RoleDTO findRoleByPrentId(Long parentId) throws Exception;

    /**
     * 查看角色名是否重复
     *
     * @param role
     * @return
     */
    boolean ifRepeatName(RoleDTO role) throws Exception;

    /**
     * 添加角色
     *
     * @param role
     * @param resIdListStr
     * @param orgIdListStr
     */
    void addRole(RoleDTO role, String resIdListStr, String orgIdListStr) throws Exception;

    /**
     * 修改 角色
     *
     * @param role
     * @param resIdListStr
     * @param orgIdListStr
     */
    void updateRole(RoleDTO role, String resIdListStr, String orgIdListStr) throws Exception;

    /**
     * 通过角色Id查询角色拥有的菜单权限
     *
     * @return
     * @throws Exception
     */
    List<Long> findRelResRoleByRoleId(Long roleId) throws Exception;

    /**
     * 通过角色Id查询角色与组织之间的关系
     *
     * @return
     * @throws Exception
     */
    List<Long> findRelOrgRoleByRoleId(Long roleId) throws Exception;

    /**
     * 删除角色
     *
     * @param roleId
     * @param invalid
     * @param opId
     * @throws Exception
     */
    void delRoleById(Long roleId, IsValidEnum invalid, Long opId) throws Exception;

    /**
     * 获取角色树
     *
     * @return
     * @throws Exception
     */
    List<RoleDTO> findRoleTree(Long opId) throws Exception;
}
