package com.jiumai.base.sm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.sm.mapper.SmRelResRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.jiumai.base.sm.entity.SmRelResRole;
import com.jiumai.base.sm.service.RelResRoleService;

@Service
@Slf4j
public class RelResRoleServiceImpl extends ServiceImpl<SmRelResRoleMapper, SmRelResRole> implements RelResRoleService{

	@Resource
	private SmRelResRoleMapper smRelResRoleMapper;

	@Override
	public long saveSmRelResRole(SmRelResRole relResRole) throws Exception {
		smRelResRoleMapper.insert(relResRole);
		return relResRole.getRelId();
	}

	@Override
	public List<Long> findResIdsByRoleId(long roleId) throws Exception {
		List<SmRelResRole> smRelResRoles = smRelResRoleMapper.selectList(new QueryWrapper<SmRelResRole>()
				.lambda()
				.isNull(SmRelResRole::getExpireDate)
				.eq(SmRelResRole::getRoleId, roleId)
				.select(SmRelResRole::getResourceId)
		);
		if(CommonFuntions.isEmptyObject(smRelResRoles)){
			return new ArrayList<>();
		}
		return smRelResRoles.stream().map(SmRelResRole::getResourceId).collect(Collectors.toList());
	}

	@Override
	public SmRelResRole getRelResRoleByRoleIdAndResId(long roleId, long resId) throws Exception {
		return smRelResRoleMapper.selectOne(new QueryWrapper<SmRelResRole>()
				.lambda()
				.eq(SmRelResRole::getRoleId, roleId)
				.eq(SmRelResRole::getResourceId, resId)
		);
	}

	@Override
	public int deleteRelResRole(SmRelResRole relResRole) throws Exception {
		relResRole.setExpireDate(new Date());
		return smRelResRoleMapper.update(relResRole, new UpdateWrapper<SmRelResRole>()
				.lambda()
				.eq(SmRelResRole::getRelId, relResRole.getRelId())
		);
	}

	@Override
	public List<SmRelResRole> findRelResRoleByRoleId(long roleId) throws Exception {
		return smRelResRoleMapper.selectList(new QueryWrapper<SmRelResRole>()
				.lambda()
				.eq(SmRelResRole::getRoleId, roleId)
				.isNull(SmRelResRole::getExpireDate)
		);
	}

	@Override
	public List<String> findExpieResIdsByRoleId(Long roleId) {
		List<SmRelResRole> smRelResRoles = smRelResRoleMapper.selectList(new QueryWrapper<SmRelResRole>()
				.lambda()
				.isNotNull(SmRelResRole::getExpireDate)
				.eq(SmRelResRole::getRoleId, roleId)
				.select(SmRelResRole::getResourceId)
		);
		if(CommonFuntions.isEmptyObject(smRelResRoles)){
			return new ArrayList<>();
		}
		return smRelResRoles.stream().map(s -> String.valueOf(s.getResourceId())).collect(Collectors.toList());
	}

	@Override
	public void updateByResIdByRoleId(SmRelResRole rrr) {
		smRelResRoleMapper.update(rrr, new UpdateWrapper<SmRelResRole>()
				.lambda()
				.eq(SmRelResRole::getResourceId, rrr.getResourceId())
				.eq(SmRelResRole::getRoleId, rrr.getRoleId())
		);
	}


}
