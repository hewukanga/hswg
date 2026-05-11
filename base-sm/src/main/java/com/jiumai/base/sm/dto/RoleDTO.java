/**
 * @{#} RoleDTO.java Created on 2016-09-22 16:04:07
 *
 * Copyright (c) 2018 Hangzhou JiuMai Network Technology Co., Ltd. All rights reserved.
 */
package com.jiumai.base.sm.dto;

import com.jiumai.base.sm.entity.SmRole;
import com.jiumai.base.sm.enums.RelTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色实体
 * 
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 */
@Data
@ApiModel(value = "角色对象拓展", description = "包含角色的属性及拓展属性")
public class RoleDTO extends SmRole {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "操作员名称")
	private String opName;

	@ApiModelProperty(value = "操作员别名")
	private String supplierShortName;

	@ApiModelProperty(value = "功能菜单集")
	private String resIdList;

	@ApiModelProperty(value = "组织菜单集")
	private String orgIdList;

	@ApiModelProperty(value = "功能菜单集合")
	private List<Long> resIds;

	@ApiModelProperty(value = "功能菜单集合")
	private List<Long> orgIds;

	@ApiModelProperty(value = "下级角色集合")
	private List<RoleDTO> childrens = new ArrayList<RoleDTO>();// 子菜单集

	private RelTypeEnum relType;

	@ApiModelProperty(value = "关联接口信息")
	private List<RelRoleInterfaceDTO> interfaceConfDTOS;

	private boolean disabled = false;

	public RoleDTO(SmRole role) {
		super();
		BeanUtils.copyProperties(role, this);
	}
	public RoleDTO() {

	}

}