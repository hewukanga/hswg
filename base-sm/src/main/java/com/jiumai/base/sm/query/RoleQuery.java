package com.jiumai.base.sm.query;

import com.jiumai.base.common.core.query.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleQuery extends PageParam {

	private static final long serialVersionUID = -8290803764706383556L;

	@ApiModelProperty(value = "父角色Id",example = "0")
	private Long parentId;
	
	@ApiModelProperty(value = "状态：有效：VALID，无效：INVALID")
	private String status;


}
