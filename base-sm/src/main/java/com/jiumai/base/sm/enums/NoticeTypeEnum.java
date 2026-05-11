package com.jiumai.base.sm.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiumai.base.common.core.enums.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel(value = "通知公告类型",description = "")	
public enum NoticeTypeEnum implements BaseEnum {
	/**
	 * 合同告警通知
	 */
	CON_WARN("合同告警通知"),
	/**
	 * 合同审批结果通知
	 */
	CON_AUDIT("合同审批结果通知"),
	/**
	 * 系统公告
	 */
	SYS_NOTICE("系统公告"),
	/**
	 * 发布公告
	 */
	PUB_NOTICE("发布公告"),
	/**
	 * 我的公告
	 */
	MY_NOTICE("我的公告");
	
	@ApiModelProperty(value = "枚举类型")
	private String name;

	@JsonCreator
	public static NoticeTypeEnum jsonInit(String value) {
		for(NoticeTypeEnum item : values()){
            if(item.toString().equals(value)){
                return item;
            }
        }
        return null;
	}
	
	private NoticeTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	@ApiModelProperty(value = "编码")
	@Override
	public String getValue() {
		return this.name();
	}
	
}
