package com.jiumai.base.sm.query;

import com.jiumai.base.common.core.query.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "枚举查询参数")
public class EnumQuery extends PageParam {

	private static final long serialVersionUID = 6255873107152837230L;

	@ApiModelProperty(value = "名称")
	private String enumName;

	@ApiModelProperty(value = "名称")
	private String enumCode;

	@ApiModelProperty(value = "类型： SYS:系统，USER:用户定义")
	private String enumType;

}
