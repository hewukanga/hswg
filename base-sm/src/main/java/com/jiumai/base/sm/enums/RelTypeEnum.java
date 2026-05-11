package com.jiumai.base.sm.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jiumai.base.common.core.enums.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel(value = "关系类型",description = "UNREAL:虚拟，REAL:真实")	
public enum RelTypeEnum implements BaseEnum {
	
	UNREAL("虚拟"),
	REAL("真实");
	
	@ApiModelProperty(value = "关系类型描述")
	private String name;

	@JsonCreator
	public static RelTypeEnum jsonInit(@JsonProperty("value") String value) {
		for(RelTypeEnum item : values()){
            if(item.toString().equals(value)){
                return item;
            }
        }
        return null;
	}
	
	private RelTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String getValue() {
		return this.name();
	}
}
