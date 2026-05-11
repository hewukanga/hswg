package com.jiumai.base.sm.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiumai.base.common.core.enums.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel(value = "功能类型",description = "RESOURCE_SYS:API接口，RESOURCE_DIR:功能目录，RESOURCE_MENU:功能菜单，RESOURCE_BTN:功能按钮,RESOURCE_ROUTER:路由页面")	
public enum ResourceTypeEnum implements BaseEnum {
	/**
	 * 系统权限
	 */
	RESOURCE_SYS("API接口"),
	/**
	 * 功能目录
	 */
	RESOURCE_DIR("功能目录"),
	/**
	 * 功能菜单
	 */
	RESOURCE_MENU("功能菜单"),
	/**
	 * 功能按钮
	 */
	RESOURCE_BTN("功能按钮"),
	/**
	 * 功能按钮
	 */
	RESOURCE_ROUTER("路由页面");

	
	@ApiModelProperty(value = "菜单名称")
	private String name;
	
	@JsonCreator
	public static  ResourceTypeEnum jsonInit(String value) {
		for(ResourceTypeEnum item : values()){
            if(item.toString().equals(value)){
                return item;
            }
        }
        return null;
	}
	
	private ResourceTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@ApiModelProperty(value = "菜单编码")
	@Override
	public String getValue() {
		return this.name();
	}
	
}
