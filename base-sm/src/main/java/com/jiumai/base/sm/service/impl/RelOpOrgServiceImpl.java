/*
 * RelOpOrgServiceImpl.java Created on 2016年10月26日 下午4:58:46
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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.sm.entity.SmRelOpOrg;
import com.jiumai.base.sm.enums.RelTypeEnum;
import com.jiumai.base.sm.mapper.SmRelOpOrgMapper;
import com.jiumai.base.sm.service.RelOpOrgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 */
@Service
@Slf4j
public class RelOpOrgServiceImpl extends ServiceImpl<SmRelOpOrgMapper, SmRelOpOrg> implements RelOpOrgService {

    @Resource
	private SmRelOpOrgMapper smRelOpOrgMapper;
    
	@Override
	public long saveSmRelOpOrg(SmRelOpOrg relOpOrg) throws Exception {
		smRelOpOrgMapper.insert(relOpOrg);
		return relOpOrg.getRelId();
	}
	
    @Override
    public List<SmRelOpOrg> findRelOpOrgListByOpId(long opId) throws Exception {
    	return smRelOpOrgMapper.selectList(new QueryWrapper<SmRelOpOrg>().lambda().eq(SmRelOpOrg::getOpId, opId).isNull(SmRelOpOrg::getExpireDate));
    }
    


	@Override
	public List<Long> findRelOpOrgIdsByOpId(long opId) throws Exception {
		List<SmRelOpOrg> smRelOpOrgs = smRelOpOrgMapper.selectList(new QueryWrapper<SmRelOpOrg>().lambda()
				.eq(opId > 1, SmRelOpOrg::getOpId, opId)
				.eq(SmRelOpOrg::getRelType, RelTypeEnum.REAL.getValue())
				.select(SmRelOpOrg::getOrgId)
		);
		if(CommonFuntions.isEmptyObject(smRelOpOrgs)){
			return new ArrayList<>();
		}
		return smRelOpOrgs.stream().map(SmRelOpOrg::getOrgId).collect(Collectors.toList());
	}



}
