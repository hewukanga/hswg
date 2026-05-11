package com.jiumai.base.sm.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiumai.base.sm.mapper.SmRelOrgRoleMapper;
import org.springframework.stereotype.Service;
import com.jiumai.base.sm.entity.SmRelOrgRole;
import com.jiumai.base.sm.service.RelOrgRoleService;

@Service
public class RelOrgRoleServiceImpl extends ServiceImpl<SmRelOrgRoleMapper, SmRelOrgRole> implements RelOrgRoleService {

	@Resource
	private SmRelOrgRoleMapper smRelOrgRoleMapper;

	@Override
	public long saveSmRelOrgRole(SmRelOrgRole relOrgRole) throws Exception {
		smRelOrgRoleMapper.insert(relOrgRole);
		return relOrgRole.getRelId();
	}

	@Override
	public List<SmRelOrgRole> findRelOrgRoleByRoleId(long roleId) throws Exception {
		return smRelOrgRoleMapper.selectList(new QueryWrapper<SmRelOrgRole>()
				.lambda()
				.eq(SmRelOrgRole::getRoleId, roleId)
				.isNull(SmRelOrgRole::getExpireDate)
		);
	}

	@Override
	public SmRelOrgRole getRelOrgRoleByRoleIdAndOrgId(long roleId, long orgId) throws Exception {
		return smRelOrgRoleMapper.selectOne(new QueryWrapper<SmRelOrgRole>().lambda().eq(SmRelOrgRole::getRoleId, roleId).eq(SmRelOrgRole::getOrgId, orgId));
	}

	@Override
	public int deleteRelOrgRole(SmRelOrgRole relOrgRole) throws Exception {
		relOrgRole.setExpireDate(new Date());
		return smRelOrgRoleMapper.update(relOrgRole, new UpdateWrapper<SmRelOrgRole>()
				.lambda()
				.eq(SmRelOrgRole::getRelId, relOrgRole.getRelId())
		);
	}

}
