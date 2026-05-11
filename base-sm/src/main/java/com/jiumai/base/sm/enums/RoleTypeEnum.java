package com.jiumai.base.sm.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiumai.base.common.core.enums.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel(value = "角色类型",description = "SYS:系统内置，USER:用户定义")	
public enum RoleTypeEnum implements BaseEnum {
	/**
	 * 系统内置
	 */
	SYS("系统内置"),
	/**
	 * 用户定义
	 */
	USER("用户定义");
	
	@ApiModelProperty(value = "角色类型")
	private String name;

	@JsonCreator
	public static RoleTypeEnum jsonInit(String value) {
		for(RoleTypeEnum item : values()){
            if(item.toString().equals(value)){
                return item;
            }
        }
        return null;
	}
	
	private RoleTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	@ApiModelProperty(value = "角色编码")
	@Override
	public String getValue() {
		return this.name();
	}
	
}
