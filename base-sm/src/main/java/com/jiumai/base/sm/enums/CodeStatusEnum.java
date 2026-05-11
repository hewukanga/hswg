package com.jiumai.base.sm.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiumai.base.common.core.enums.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel(value = "有效状态", description = "VALID:有效，INVALID:无效，LOCKED：锁定")
public enum CodeStatusEnum implements BaseEnum {
	/**
	 * 有效
	 */
	VALID("有效"),
	/**
	 * 无效
	 */
	INVALID("无效"),
	/**
	 * 锁定
	 */
	LOCKED("锁定");

	@JsonCreator
	public static CodeStatusEnum jsonInit(String value) {
		for (CodeStatusEnum item : values()) {
			if (item.toString().equals(value)) {
				return item;
			}
		}
		return null;
	}

	@ApiModelProperty(value = "状态名称")
	private String name;

	private CodeStatusEnum(String name) {
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
