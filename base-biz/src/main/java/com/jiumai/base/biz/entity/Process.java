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
 * 流程表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_process")
@ApiModel("流程表")
public class Process implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("开始时间")
    @TableField("start_date")
    private LocalDateTime startDate;

    @ApiModelProperty("结束时间")
    @TableField("end_date")
    private LocalDateTime endDate;

    @ApiModelProperty("类型 1.加班 2.请假 3.补打卡")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("请假类型1.年休假 2.事假 3.病假 4.婚假 5.丧假 6.产假 7.其他 8.调休")
    @TableField("leave_type")
    private Integer leaveType;

    @ApiModelProperty("加班类型 1.调休 2.加班工资")
    @TableField("overtime_type")
    private Integer overtimeType;

    @ApiModelProperty("流程状态 1.未提交 2,审核中 3.审核通过 4.审核驳回")
    @TableField("process_status")
    private Integer processStatus;

    @ApiModelProperty("加班时长(小时)")
    @TableField("overtime_duration")
    private Double overtimeDuration;

    @ApiModelProperty("请假时长(天)")
    @TableField("leave_duration")
    private Double leaveDuration;

    @ApiModelProperty("原因")
    @TableField("reason")
    private String reason;

    @ApiModelProperty("图片")
    @TableField("images")
    private String images;

    @ApiModelProperty("当前审批节点角色id(用于分页查询)")
    @TableField("curren_audit_role_id")
    private Long currenAuditRoleId;

    @ApiModelProperty("已知会角色id(#拼接用于分页查询)")
    @TableField("told_role_id")
    private String toldRoleId;

    @ApiModelProperty("已审批人物id(#拼接用于分页查询)")
    @TableField("audited_op_id")
    private String auditedOpId;

    @ApiModelProperty("驳回角色id(用于分页查询)")
    @TableField("reject_op_id")
    private Long rejectOpId;

    @ApiModelProperty("提交人所在片区id")
    @TableField("commit_org_id")
    private Long commitOrgId;

    @ApiModelProperty("创建人id")
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private Long createId;

    @ApiModelProperty("创建人姓名")
    @TableField(value = "create_name", fill = FieldFill.INSERT)
    private String createName;

    @ApiModelProperty("手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("创建人部门")
    @TableField("create_org")
    private String createOrg;

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

    @ApiModelProperty("补卡时长(小时)")
    @TableField("replacement_duration")
    private Double replacementDuration;
}
