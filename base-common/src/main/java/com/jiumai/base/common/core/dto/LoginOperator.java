package com.jiumai.base.common.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class LoginOperator {

	@ApiModelProperty(value = "操作员ID",example="0")
	protected  Long opId ;

	@ApiModelProperty(value = "账号登陆名")
	protected  String opLoginName ;

	@ApiModelProperty(value = "用户姓名")
	protected  String opName ;

	@ApiModelProperty(value = "外部账号")
	protected  String outId ;

	@ApiModelProperty(value = "账号归属组织",example="0")
	protected  Long orgId ;

	@ApiModelProperty(value = "组织名称")
	protected  String orgName ;

	@ApiModelProperty(value = "地址")
	protected  String address ;

	@ApiModelProperty(value = "手机号")
	protected  String mobilePhone ;

	@ApiModelProperty(value = "邮箱")
	protected  String email ;

	@ApiModelProperty(value = "证件号")
	protected  String identityNo ;

	@ApiModelProperty(value = "备注")
	private String memo;

	@ApiModelProperty(value = "额外信息")
	private Map<String, Object> exetInfo;
	
	@ApiModelProperty(value = "操作员拥有角色集合")
	private List<Long> roleIds;

	@ApiModelProperty(value = "当前角色：SALES：客户经理 OP：普通操作员")
	private String mainRole = "OP";

	@ApiModelProperty(value = "是否需要修改密码")
	private Boolean needUpdatePwd;

}
