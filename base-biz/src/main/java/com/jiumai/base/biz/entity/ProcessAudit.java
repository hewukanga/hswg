package com.jiumai.base.biz.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 流程审批表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_process_audit")
@ApiModel("流程审批表")
public class ProcessAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("流程名称")
    @TableField("process_name")
    private String processName;

    @ApiModelProperty("流程类型 1.加班 2.请假 3.补卡")
    @TableField("process_type")
    private Integer processType;

    @ApiModelProperty("流程描述")
    @TableField("process_description")
    private String processDescription;

    @ApiModelProperty("应用组织id(组织id通过#拼接)")
    @TableField("application_org_id")
    private String applicationOrgId;

    @ApiModelProperty("提交人角色id")
    @TableField("commit_role_id")
    private Long commitRoleId;

    @ApiModelProperty("应用组织名称(组织名称通过,拼接)")
    @TableField("application_org_name")
    private String applicationOrgName;

    @ApiModelProperty("提交人角色名称")
    @TableField("commit_role_name")
    private String commitRoleName;

    @ApiModelProperty("是否启用")
    @TableField("enable_status")
    private Boolean enableStatus;

    @ApiModelProperty("创建人id")
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private Long createId;

    @ApiModelProperty("创建人姓名")
    @TableField(value = "create_name", fill = FieldFill.INSERT)
    private String createName;

    @ApiModelProperty("创建日期")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @ApiModelProperty("修改人id")
    @TableField(value = "modify_id", fill = FieldFill.INSERT_UPDATE)
    private Long modifyId;

    @ApiModelProperty("修改人姓名")
    @TableField(value = "modify_name", fill = FieldFill.INSERT_UPDATE)
    private String modifyName;

    @ApiModelProperty("修改日期")
    @TableField(value = "modify_date", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifyDate;

    @ApiModelProperty("是否删除1：未删除 -1：已删除")
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty("乐观锁版本号")
    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    private Integer version;
}
