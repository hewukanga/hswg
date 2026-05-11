/*
 * RelOpOrgService.java Created on 2016年10月26日 下午4:54:29
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
import com.jiumai.base.sm.entity.SmRelOpArea;
import com.jiumai.base.sm.entity.SmRelOpOrg;

import java.util.List;

/**
 * 用户与组织关系服务
 *
 * @author louis<8 3 0 3 0 2 4 6 @ qq.com>
 * @version 1.0
 * @date 2018-02-12
 */
public interface RelOpOrgService extends IService<SmRelOpOrg> {

    /**
     * 根据用户ID获取用户关联组织
     *
     * @param opId
     * @return
     * @throws Exception
     */
    List<SmRelOpOrg> findRelOpOrgListByOpId(long opId) throws Exception;


    /**
     * 保存数据
     *
     * @param relOpOrg
     * @return
     * @throws Exception
     */
    long saveSmRelOpOrg(SmRelOpOrg relOpOrg) throws Exception;


    /**
     * 根据用户ID获取用户关联组织ID
     *
     * @param opId
     * @return
     * @throws Exception
     */
    List<Long> findRelOpOrgIdsByOpId(long opId) throws Exception;



}
