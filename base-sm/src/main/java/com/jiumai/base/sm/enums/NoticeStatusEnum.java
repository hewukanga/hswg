package com.jiumai.base.sm.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiumai.base.common.core.enums.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel(value = "通知状态", description = "")
public enum NoticeStatusEnum implements BaseEnum {
	/**
	 * 已读
	 */
	READ("已读"),
	/**
	 * 未读
	 */
	UNREAD("未读");


	@JsonCreator
	public static NoticeStatusEnum jsonInit(String value) {
		for (NoticeStatusEnum item : values()) {
			if (item.toString().equals(value)) {
				return item;
			}
		}
		return null;
	}

	@ApiModelProperty(value = "状态名称")
	private String name;

	private NoticeStatusEnum(String name) {
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
