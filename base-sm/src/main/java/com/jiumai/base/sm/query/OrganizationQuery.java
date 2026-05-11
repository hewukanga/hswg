package com.jiumai.base.sm.query;

import com.jiumai.base.common.core.query.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrganizationQuery extends PageParam {

	private static final long serialVersionUID = -9137532897980194629L;

	@ApiModelProperty(value = "组织名称")
	private String orgName;
	
	@ApiModelProperty(value = "父组织id",example = "0")
	private Long parentId;
	

	private Long opId;

	

}
