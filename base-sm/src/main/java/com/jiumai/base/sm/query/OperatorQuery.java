package com.jiumai.base.sm.query;

import com.jiumai.base.common.core.query.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "操作员查询参数")
public class OperatorQuery extends PageParam {

	private static final long serialVersionUID = 8352454255925332078L;

	@ApiModelProperty(value = "操作员名称")
	private String opName;
	
	@ApiModelProperty(value = "操作员登陆名")
	private String opLoginName;
	
	@ApiModelProperty(value = "账号状态： VALID:有效，INVALID:无效，LOCKED：锁定")
	private String[] status;
	
	@ApiModelProperty(value = "是否为管理员：YES | NO")
	private String isAdmin;
	
	@ApiModelProperty(value = "创建人Id",example="0")
	private Long createId;
	@ApiModelProperty(value = "所属组织Id",example="0")
	private Long ownOrgId;
	
	private List<Long> ownOrgIds;

}
