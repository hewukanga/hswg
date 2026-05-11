/**
 * @{#} ResourceDTO.java Created on 2016-09-22 16:04:07
 *
 * Copyright (c) 2018 Hangzhou JiuMai Network Technology Co., Ltd. All rights reserved.
 */
package com.jiumai.base.sm.dto;

import com.jiumai.base.sm.entity.SmResource;
import com.jiumai.base.sm.enums.RelTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 * 
 *          对应实体类(Resource)
 */
@Data
@ApiModel(value = "功能对象拓展", description = "包含功能的属性及拓展属性")
public class ResourceDTO extends SmResource {

	private static final long serialVersionUID = 1L;
    // 是否拥有按钮权限
	private boolean btnPermission;

	private RelTypeEnum relType;
	
	// 是否禁用树
	// private boolean disabled = true;

	@ApiModelProperty(value = "子菜单集")
	private List<ResourceDTO> childrens = new ArrayList<ResourceDTO>();// 子菜单集

}