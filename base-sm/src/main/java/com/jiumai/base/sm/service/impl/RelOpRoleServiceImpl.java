/*
 * RelOpRoleServiceImpl.java Created on 2016年10月26日 下午4:59:00
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
package com.jiumai.base.sm.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.sm.mapper.SmRelOpRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.jiumai.base.sm.entity.SmRelOpRole;
import com.jiumai.base.sm.service.RelOpRoleService;

/**
 * 
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 */
@Service
@Slf4j
public class RelOpRoleServiceImpl extends ServiceImpl<SmRelOpRoleMapper, SmRelOpRole> implements RelOpRoleService {

    @Resource
	private SmRelOpRoleMapper smRelOpRoleMapper;

    @Override
	public long saveSmRelOpRole(SmRelOpRole relOpRole) {
    	smRelOpRoleMapper.insert(relOpRole);
		return relOpRole.getRelId();
	}
    
    @Override
    public List<SmRelOpRole> findRelOpRoleListByOpId(long opId) throws Exception {
    	return smRelOpRoleMapper.selectList(new QueryWrapper<SmRelOpRole>().lambda().eq(SmRelOpRole::getOpId, opId));
    }

	@Override
	public List<Long> findRelOpRoleIdsByOpId(long opId) throws Exception {
		List<SmRelOpRole> smRelOpRoles = smRelOpRoleMapper.selectList(new QueryWrapper<SmRelOpRole>()
				.lambda()
				.isNull(SmRelOpRole::getExpireDate)
				.eq(opId > 0, SmRelOpRole::getOpId, opId)
				.select(SmRelOpRole::getRoleId)
		);
		if(CommonFuntions.isEmptyObject(smRelOpRoles)){
			return new ArrayList<>();
		}
		return smRelOpRoles.stream().map(SmRelOpRole::getRoleId).collect(Collectors.toList());
	}


	@Override
	public int deleteRelOpRole(long opId, long roleId) throws Exception {
    	SmRelOpRole smRelOpRole = new SmRelOpRole();
    	smRelOpRole.setExpireDate(new Date());
    	return smRelOpRoleMapper.update(smRelOpRole, new UpdateWrapper<SmRelOpRole>()
				.lambda()
				.eq(SmRelOpRole::getOpId, opId)
				.eq(SmRelOpRole::getRoleId, roleId)
		);
	}

}
