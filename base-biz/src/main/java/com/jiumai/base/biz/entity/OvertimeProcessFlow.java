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
 * 加班流程表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_overtime_process_flow")
@ApiModel("加班流程表")
public class OvertimeProcessFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("加班id")
    @TableField("process_id")
    private Long processId;

    @ApiModelProperty("流程类型 1.下发 2.接收")
    @TableField("flow_type")
    private Integer flowType;

    @ApiModelProperty("节点操作人id")
    @TableField("node_op_id")
    private Long nodeOpId;

    @ApiModelProperty("节点操作人姓名")
    @TableField("node_op_name")
    private String nodeOpName;

    @ApiModelProperty("节点操作人角色ID")
    @TableField("node_op_role_id")
    private Long nodeOpRoleId;

    @ApiModelProperty("节点操作人角色名称")
    @TableField("node_op_role_name")
    private String nodeOpRoleName;

    @ApiModelProperty("节点操作时间")
    @TableField("node_op_date")
    private LocalDateTime nodeOpDate;

    @ApiModelProperty("拒绝原因")
    @TableField("refuse_reason")
    private String refuseReason;

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

    @ApiModelProperty("是否删除 1:未删除 -1:已删除")
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty("乐观锁版本号")
    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    private Integer version;
}
