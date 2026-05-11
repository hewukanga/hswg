/*
 * RelOpRoleService.java Created on 2016年10月26日 下午4:54:46
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

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiumai.base.sm.entity.SmRelOpOrg;
import com.jiumai.base.sm.entity.SmRelOpRole;

import java.util.List;

/**
 * 用户与角色关系服务
 *
 * @author louis<8 3 0 3 0 2 4 6 @ qq.com>
 * @version 1.0
 * @date 2018-02-12
 */
public interface RelOpRoleService extends IService<SmRelOpRole>{
    /**
     * 保存数据
     *
     * @return
     * @throws Exception
     */
    long saveSmRelOpRole(SmRelOpRole relOpRole);

    /**
     * 根据用户ID获取用户关联角色
     *
     * @param opId
     * @return
     * @throws Exception
     */
    List<SmRelOpRole> findRelOpRoleListByOpId(long opId) throws Exception;

    /**
     * 根据用户ID获取用户关联角色ID
     *
     * @param opId
     * @return
     * @throws Exception
     */
    List<Long> findRelOpRoleIdsByOpId(long opId) throws Exception;

    /**
     * 删除操作员与角色的关系
     *
     * @param opId
     * @param roleId
     * @return
     * @throws Exception
     */
    int deleteRelOpRole(long opId, long roleId) throws Exception;

}
