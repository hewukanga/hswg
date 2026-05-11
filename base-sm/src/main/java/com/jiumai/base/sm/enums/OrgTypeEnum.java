package com.jiumai.base.sm.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jiumai.base.common.core.enums.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel(value = "组织类型",description = "GROUP_COMPANY:集团，COMPANY:公司，CHILDREN_COMPANY:子公司，PART_COMPANY:分公司，DEPT:部门")	
public enum OrgTypeEnum implements BaseEnum {
	
	GROUP_COMPANY("集团公司"),
	
	COMPANY("公司"),
	
	CHILDREN_COMPANY("子公司"),
	
	PART_COMPANY("分公司"),
	
	DEPT("部门");
	
	@ApiModelProperty(value = "组织类型名称")
	private String name;

	@JsonCreator
	public static OrgTypeEnum jsonInit(@JsonProperty("value") String value) {
		System.err.println(value);
		for(OrgTypeEnum item : values()){
            if(item.toString().equals(value)){
                return item;
            }
        }
        return null;
	}
	
	private OrgTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	@ApiModelProperty(value = "组织类型编码")
	@Override
	public String getValue() {
		return this.name();
	}
}
