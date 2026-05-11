package com.jiumai.base.sm.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiumai.base.common.core.enums.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel(value = "证件类型",description = "ID_CARD：身份证")	
public enum IdentityTypeEnum implements BaseEnum {
	/**
	 * 身份证
	 */
	ID_CARD("身份证");
	
	@ApiModelProperty(value = "证件名称")
	private String name;
	
	private IdentityTypeEnum(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	@JsonCreator
	public  static IdentityTypeEnum jsonInit(String value) {
		for(IdentityTypeEnum item : values()){
            if(item.toString().equals(value)){
                return item;
            }
        }
        return null;
	}
	
	@ApiModelProperty(value = "证件编码")
	@Override
	public String getValue() {
		return this.name();
	}
	
	
	
}
