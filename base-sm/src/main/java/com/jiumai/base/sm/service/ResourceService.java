/*
 * ResourceService.java Created on 2016年10月26日 下午9:51:33
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
import com.jiumai.base.sm.dto.ResourceDTO;
import com.jiumai.base.sm.entity.SmRelOpRole;
import com.jiumai.base.sm.entity.SmRelResRole;
import com.jiumai.base.sm.entity.SmResource;
import com.jiumai.base.sm.query.ResourceQuery;
import java.util.List;

/**
 * 功能菜单服务
 *
 * @author louis<8 3 0 3 0 2 4 6 @ qq.com>
 * @version 1.0
 * @date 2018-02-12
 */
public interface ResourceService extends IService<SmResource>{

    /**
     * 保存数据
     *
     * @return
     * @throws Exception
     */
    Long saveResource(SmResource resource) throws Exception;

    /**
     * 查询所有菜单
     *
     * @return
     * @throws Exception
     */
    List<ResourceDTO> findResourceList() throws Exception;

    /**
     * 根据主键查询信息
     *
     * @param resourceId
     * @return
     * @throws Exception
     */
    ResourceDTO getByResourceId(Long resourceId) throws Exception;

    /**
     * 分页查询信息
     *
     * @param pageParam 分页参数
     *                  相关查询参数
     * @return
     * @throws Exception
     */
    IPage<ResourceDTO> findResourcePaging(ResourceQuery pageParam) throws Exception;

    /**
     * 校验功能名称是否存在
     *
     * @param resourceDTO
     * @return
     * @throws Exception
     */
    Long checkResourceTitle(SmResource resourceDTO) throws Exception;

    /**
     * 校验功能路径是否存在
     *
     * @param resourceDTO
     * @return
     * @throws Exception
     */
    Long checkResourceUrl(SmResource resourceDTO) throws Exception;

    /**
     * 修改信息
     *
     * @return
     * @throws Exception
     */
    Integer updateResource(SmResource resource) throws Exception;

    /**
     * 根据主键删除功能菜单
     *
     * @param resourceId
     * @return
     * @throws Exception
     */
    Integer deleteResource(Long resourceId, Long opId) throws Exception;

    /**
     * 修改排序顺序
     *
     * @throws Exception
     */
    void updateSort(List<ResourceDTO> resourceList) throws Exception;

    List<ResourceDTO> findResourceDTOByOpId(long opId) throws Exception;

    /**
     * 获取操作员的菜单树
     *
     * @param opId
     * @return
     * @throws Exception
     */
    List<ResourceDTO> getOperatorMenusTree(Long opId) throws Exception;

    /**
     * 获取操作员按钮信息列表
     *
     * @param opId
     * @return
     * @throws Exception
     */
    List<ResourceDTO> findOperatorBtnList(Long opId) throws Exception;

    /**
     * 查询操作员拥有角色权限
     *
     * @param opId
     * @return
     */
    List<SmRelOpRole> getRelOpRoleByOpId(Long opId);

    /**
     * 查询角色与菜单关系
     *
     * @return
     */
    SmRelResRole getRelResRoleBy(Long roleId, Long resId);

    /**
     * 更新菜单顺序
     *
     * @return
     * @throws Exception
     */

    List<ResourceDTO> getBtnByOpId(Long resId, Long opId) throws Exception;
}
