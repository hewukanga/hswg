package com.jiumai.base.sm.service.impl;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiumai.base.common.core.utils.PageUtils;
import com.jiumai.base.sm.mapper.SmAreaMapper;
import org.springframework.stereotype.Service;

import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.sm.dto.AreaDTO;
import com.jiumai.base.sm.entity.SmArea;
import com.jiumai.base.sm.query.AreaQuery;
import com.jiumai.base.sm.service.AreaService;

@Service
public class AreaServiceImpl extends ServiceImpl<SmAreaMapper, SmArea> implements AreaService {

	@Resource
	private SmAreaMapper smAreaMapper;

	@Override
	public List<AreaDTO> findAreaList(Long opId) throws Exception {
		List<AreaDTO> list = smAreaMapper.findAreaListByOpId(opId);
		return list;
	}

	@Override
	public List<AreaDTO> findOpAccessAreaTree(Long opId) throws Exception {
		
		List<AreaDTO> areaList = this.findAreaList(opId);
		List<AreaDTO> rootTrees = new ArrayList<AreaDTO>();
		for (AreaDTO tree : areaList) {
			if("4".equals(tree.getAreaType())) {
				continue;
			}
			if (tree.getParentId().longValue() == tree.getAreaId().longValue()) {
				rootTrees.add(tree);
			}
			for (AreaDTO t : areaList) {
				if (t.getParentId().longValue() == t.getAreaId().longValue()) {
					continue;
				}
				if (t.getParentId().longValue() == tree.getAreaId().longValue()) {
					if (tree.getChildrens() == null) {
						List<AreaDTO> myChildrens = new ArrayList<AreaDTO>();
						myChildrens.add(t);
						tree.setChildrens(myChildrens);
					} else {
						tree.getChildrens().add(t);
					}
				}
			}
		}
		return rootTrees;
	}
	
	@Override
	public List<AreaDTO> findAreaTree() throws Exception {
		List<AreaDTO> areaList = this.smAreaMapper.findAreaList();
		List<AreaDTO> rootTrees = new ArrayList<AreaDTO>();

		for (AreaDTO tree : areaList) {
			if("4".equals(tree.getAreaType())) {
				continue;
			}
			if (tree.getParentId().longValue() == tree.getAreaId().longValue()) {
				rootTrees.add(tree);
			}
			for (AreaDTO t : areaList) {
				if (t.getParentId().longValue() == t.getAreaId().longValue()) {
					continue;
				}
				if (t.getParentId().longValue() == tree.getAreaId().longValue()) {
					if (tree.getChildrens() == null) {
						List<AreaDTO> myChildrens = new ArrayList<AreaDTO>();
						myChildrens.add(t);
						tree.setChildrens(myChildrens);
					} else {
						tree.getChildrens().add(t);
					}
				}
			}
		}
		return rootTrees;
	}

	@Override
	public SmArea getAreaById(long areaId) {
		
		return getById(areaId);
		
	}

	@Override
	public void saveOrUpdateArea(SmArea smArea) throws Exception {
		
		if (CommonFuntions.isEmptyObject(smArea.getAreaId())) {
			smAreaMapper.insert(smArea);
		}else {
			smAreaMapper.updateById(smArea);
		}
	}



	@Override
	public IPage<AreaDTO> findAreaPaging(AreaQuery areaQuery) throws Exception {
		PageUtils<AreaDTO> pageUtils = new PageUtils<>();
		Page<AreaDTO> page = pageUtils.getPageByPageParam(areaQuery);
		smAreaMapper.findAreaPaging(page, areaQuery);
		return page;
	}


}
