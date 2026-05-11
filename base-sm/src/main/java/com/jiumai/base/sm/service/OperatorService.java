/*
 * OperatorService.java Created on 2016年10月24日 下午3:07:53
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
import com.jiumai.base.sm.dto.OperatorDTO;
import com.jiumai.base.sm.entity.SmOperator;
import com.jiumai.base.sm.query.OperatorQuery;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 操作员服务接口
 *
 * @author louis<8 3 0 3 0 2 4 6 @ qq.com>
 * @version 1.0
 * @date 2018-02-12
 */
public interface OperatorService extends IService<SmOperator> {


    /**
     * 分页查询操作员信息
     * <p>
     * 分页参数
     * 操作员相关查询参数
     *
     * @return
     * @throws Exception
     */
    IPage<OperatorDTO> findOperatorPaging(OperatorQuery operQuery) throws Exception;


    /**
     * 根据登录名获取操作员信息
     *
     * @param opLoginName 登录名
     * @return
     * @throws Exception
     */
    SmOperator getByOpLoginName(String opLoginName) throws Exception;


    OperatorDTO getFullOperatorInfo(long opId) throws Exception;

    /**
     * 根据操作员ID获取操作员信息
     *
     * @param opId
     * @return
     * @throws Exception
     */
    OperatorDTO getById(Long opId) throws Exception;

    /**
     * 修改操作员信息
     *
     * @return
     */
    long saveOrUpdateOperator(OperatorDTO operator) throws Exception;

    /**
     * 修改操作员状态
     *
     * @param opId
     * @param status
     * @return 受影响的行数
     * @throws Exception
     */
    int updateOpStatus(Long opId, String status) throws Exception;

    /**
     * 修改操作员信息
     *
     * @throws Exception
     */
    void updateOperatorInfo(SmOperator operator, HttpServletRequest request) throws Exception;

    /**
     * 通过角色Id获取操作员列表
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    List<SmOperator> findOperatorByRoleId(long roleId) throws Exception;

    /**
     * 根据用户名称查询用户
     *
     * @param opName
     * @return
     */
    List<SmOperator> getOperatorByOpName(String opName, List<Long> accOrgList);


    /**
     * 根据组织ID查询操作员ID集合
     *
     * @return
     * @throws Exception
     */
    List<Long> findOperatorIdsByOrgIds(List<Long> recOrgs) throws Exception;

    List<SmOperator> findList(String searchKey, LoginOperator nowOperDTO);
}
