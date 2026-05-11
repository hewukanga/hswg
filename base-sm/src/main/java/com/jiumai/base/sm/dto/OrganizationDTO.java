/**
 * @{#} OrganizationDTO.java Created on 2016-09-22 16:04:02
 *
 * Copyright (c) 2018 Hangzhou JiuMai Network Technology Co., Ltd. All rights reserved.
 */
package com.jiumai.base.sm.dto;

import com.jiumai.base.sm.entity.SmOrganization;
import com.jiumai.base.sm.enums.RelTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 * 
 *          对应实体类(Organization)
 */
@Data
@ApiModel(value = "组织对象拓展", description = "包含组织的属性及拓展属性")
public class OrganizationDTO extends SmOrganization {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "下级组织")
	private List<OrganizationDTO> childrens = new ArrayList<OrganizationDTO>();// 子菜单集
   
	private String parentOrgName;

	private RelTypeEnum relType; // 用于操作员与组织的关系类型设置

	private List<OrganizationDTO> children = new ArrayList<>();// 子区域集
	private String label;
	private Long value;

	public OrganizationDTO(SmOrganization org) {
		super();
		BeanUtils.copyProperties(org, this);
	}
	
	public OrganizationDTO() {
		super();
	}


}