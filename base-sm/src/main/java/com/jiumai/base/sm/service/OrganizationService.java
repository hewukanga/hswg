/*
 * organizationService.java Created on 2016年10月25日 下午8:15:35
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
import com.jiumai.base.common.core.dto.LoginOperator;
import com.jiumai.base.sm.dto.OrganizationDTO;
import com.jiumai.base.sm.entity.SmOrganization;
import com.jiumai.base.sm.entity.SmRelOpOrg;
import com.jiumai.base.sm.query.OrganizationQuery;
import java.util.List;

/**
 * 组织服务接口
 *
 * @author louis<8 3 0 3 0 2 4 6 @ qq.com>
 * @version 1.0
 * @date 2018-02-12
 */
public interface OrganizationService extends IService<SmOrganization> {

    List<SmOrganization> searchPayeeOrgs(String searchKe);

    /**
     * 保存数据
     *
     * @param organizationDTO
     * @return
     * @throws Exception
     */
    long save(OrganizationDTO organizationDTO) throws Exception;

    /**
     * 查询所有有效组织
     *
     * @return
     * @throws Exception
     */
    List<OrganizationDTO> findOrganizationList() throws Exception;

    /**
     * 根据父ID查询所有有效组织
     *
     * @return
     * @throws Exception
     */
    List<SmOrganization> findOrganizationListByParentId(long parentId) throws Exception;

    /**
     * 根据主键查询组织信息
     *
     * @return
     * @throws Exception
     */
    OrganizationDTO getOrganizationByOrgId(long orgId) throws Exception;

    /**
     * 根据组织名称查询组织列表
     *
     * @return
     * @throws Exception
     */
    List<SmOrganization> getOrganizationByOrgName(String orgName) throws Exception;

    /**
     * 分页查询信息
     *
     * @return
     * @throws Exception
     */
    IPage<OrganizationDTO> findOrganizationPaging(OrganizationQuery queryCondition) throws Exception;

    /**
     * 更新信息
     *
     * @param organizationDTO
     * @return
     */
    int updateOrganization(OrganizationDTO organizationDTO) throws Exception;

    /**
     * 根据组织ID删除组织
     *
     * @param orgId
     * @return
     */
    int deleteOrg(Long orgId, Long opId) throws Exception;

    /**
     * 判断组织名称是否存在
     *
     * @param organizationDTO
     * @return
     */
    Long checkOrgName(OrganizationDTO organizationDTO) throws Exception;

    /**
     * 根据操作员ID获取组织
     *
     * @param opId
     * @return
     */
    List<OrganizationDTO> findOrganizationTreeByOpId(Long opId);


    /**
     * @param opId
     * @return
     */
    List<OrganizationDTO> findDisabedOrgTreeByOpId(Long opId);

    /**
     * 查询操作员与组织关系
     *
     * @param opId
     * @return
     */
    SmRelOpOrg getRelOpOrgByOpOrgId(Long opId, Long orgId);


    List<OrganizationDTO> searchOrganizations(String searchKey, LoginOperator nowOperDTO);
}
