package com.jiumai.base.sm.query;

import com.jiumai.base.common.core.query.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "功能菜单查询参数")
public class ResourceQuery extends PageParam {

	private static final long serialVersionUID = 8617481031797165601L;

	@ApiModelProperty(value = "父类id",example = "0")
	private Long parentId;

	@ApiModelProperty(value = "当前登陆人",example = "0")
	private Long opId;


}
