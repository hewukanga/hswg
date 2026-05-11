/**
 * @{#} OperatorDTO.java Created on 2016-09-28 13:42:39
 *
 * Copyright (c) 2018 Hangzhou JiuMai Network Technology Co., Ltd. All rights reserved.
 */
package com.jiumai.base.sm.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jiumai.base.sm.entity.SmOperator;
import com.jiumai.base.sm.entity.SmRelOpOrg;
import com.jiumai.base.sm.entity.SmRelOpRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

/**
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 * 
 * 对应实体类(Operator)
 */
@Data
@ApiModel(value = "操作员对象拓展",description="包含操作员的属性及拓展属性")
public class OperatorDTO extends SmOperator {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "二次确认密码")
    private String rePassword;
    
    @ApiModelProperty(value = "归属组织名称")
    private String orgName;
    
    @ApiModelProperty(value = "修改人姓名")
    private String modifyOpName;
    
    @ApiModelProperty(value = "用户组织集")
    private List<Long> AccOrgList;

    @ApiModelProperty(value = "用户角色集")
    private List<Long> roles;
    
    @ApiModelProperty(value = "区域集")
    private List<Long> areas;

	@ApiModelProperty(value = "用户流程工作组集")
	private List<Long> groupList;
    
    @ApiModelProperty(value = "用户组织集")
    private List<SmRelOpOrg> relOpOrgList;
    
    @ApiModelProperty(value = "用户角色集")
    private List<SmRelOpRole> relOpRoleList;
    
    @ApiModelProperty(value = "用户可访问组织集")
    private List<SmRelOpOrg> relOpAccOrgList;
    
   
    @ApiModelProperty(value = "用户归属组织ID")
	private String orgIds;

    @ApiModelProperty(value = "用户拥有角色ID")
    private String roleIds;

    @ApiModelProperty(value = "可访问组织ID")
    private String accOrgIds;
    
    @ApiModelProperty(value = "可访问区域ID")
    private String areaIds;

    @ApiModelProperty(value = "用户归属工作组ID")
    private String groupIds;

    @ApiModelProperty(value = "用户角色信息")
    private String roleNames;

	@JsonIgnore//get|set时忽略此方法
    public long getOpOrgId() {
    	if(this.relOpOrgList!=null && this.relOpOrgList.size()>0) {
    		return relOpOrgList.get(0).getOrgId();
    	}
    	
    	return 0;
    }
}