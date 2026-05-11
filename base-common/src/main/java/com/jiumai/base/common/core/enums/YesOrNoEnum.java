package com.jiumai.base.common.core.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel(value = "是否状态" ,description="YES:是，NO:否")
public enum YesOrNoEnum implements BaseEnum {
	/**
	 * 是
	 */
	YES("是"),
	/**
	 * 不是
	 */
	NO("否");
	
	@ApiModelProperty(value = "是否")
	private String name;

	@JsonCreator
	public  static YesOrNoEnum jsonInit(String value) {
		for(YesOrNoEnum item : values()){
            if(item.toString().equals(value)){
                return item;
            }
        }
        return NO;
	}
	
	private YesOrNoEnum(String name) {
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
