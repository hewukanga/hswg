package com.jiumai.base.sm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.session.OperatorUtil;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.sm.dto.AreaDTO;
import com.jiumai.base.sm.entity.SmArea;
import com.jiumai.base.sm.query.AreaQuery;
import com.jiumai.base.sm.service.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "区域类" })
@RestController
@RequestMapping("d-admin/area")
public class AreaController {

	@Autowired
	private AreaService areaService;

//	@GetMapping("findAreaTree")
//	@ApiOperation(value = "获取区域树")
//	public List<AreaDTO> findAreaTree(HttpServletRequest request) throws Exception {
//		return areaService.findAreaTree();
//	}

	@GetMapping("findOpAccessAreaTree")
	@ApiOperation(value = "获取区域树")
	public ResultDTO<List<AreaDTO>> findOpAccessAreaTree(HttpServletRequest request) throws Exception {
		ResultDTO<List<AreaDTO>> resultDTO = new ResultDTO<>();
		List<AreaDTO> opAccessAreaTree = areaService.findOpAccessAreaTree(OperatorUtil.getOperatorId(request));

		return resultDTO.set(ResultCodeEnum.SUCCESS,null,opAccessAreaTree);

	}


	@GetMapping("saveArea")
	@ApiOperation(value = "新增或修改区域")
	@OpLog(title = "新增或修改区域", businessType = BusinessTypeEnum.INSERT_OR_UPDATE)
	public ResultDTO<Long> saveArea(HttpServletRequest request,@RequestBody SmArea smArea) throws Exception {
		ResultDTO<Long> result = new ResultDTO<>();
		if (CommonFuntions.isEmptyObject(smArea)) {
			return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "区域对象不能为空");
		}
		if (CommonFuntions.isEmptyObject(smArea.getAreaName())) {
			return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "区域名称不能为空");
		}
		if (CommonFuntions.isEmptyObject(smArea.getParentId())) {
			return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "区域父ID不能为空");
		}
		try {
			this.areaService.saveOrUpdateArea(smArea);
		} catch (Exception e) {
			result.set(ResultCodeEnum.ERROR_HANDLE, e.getMessage());
		}
		
		return result.set(ResultCodeEnum.SUCCESS,"保存或更新区域成功", smArea.getAreaId());	
	}
	
	@PostMapping("findAreaPaging")
	@ApiOperation(value = "查询区域分页列表")
	public ResultDTO<IPage<AreaDTO>> findAreaPaging(@RequestBody AreaQuery areaQuery, HttpServletRequest request)throws Exception{
		areaQuery.setOpId(OperatorUtil.getOperatorId(request));
		IPage<AreaDTO> areaPaging = this.areaService.findAreaPaging(areaQuery);
		return new ResultDTO<IPage<AreaDTO>>().set(ResultCodeEnum.SUCCESS, "查询成功", areaPaging);
	}
	
}
