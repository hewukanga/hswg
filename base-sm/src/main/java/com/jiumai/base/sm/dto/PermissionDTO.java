/**
 * 
 */
package com.jiumai.base.sm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户权限
 * @author louis
 *
 */
@Data
@ApiModel(value = "用户权限")
public class PermissionDTO {
	
	@ApiModelProperty(value = "可访问功能集")
	private List<ResourceDTO> res ;
/*
	@ApiModelProperty(value = "可访问菜单集")
    List<ResourceDTO> resLeveOne;
	*/
	@ApiModelProperty(value = "可访问按钮集")
    private List<ResourceDTO> resBtn;

	
}
