package com.jiumai.base.common.core.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel(value = "有效状态", description = "VALID:有效，INVALID:无效")
public enum IsValidEnum implements BaseEnum {
	/**
	 * 有效
	 */
	VALID("有效"),
	/**
	 * 无效
	 */
	INVALID("无效");

	@ApiModelProperty(value = "状态名称")
	private String name;

	@JsonCreator
	public static IsValidEnum jsonInit(String value) {
		for (IsValidEnum item : values()) {
			if (item.toString().equals(value)) {
				return item;
			}
		}
		return null;
	}

	private IsValidEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@ApiModelProperty(value = "状态编码")
	@Override
	public String getValue() {
		return this.name();
	}
}
