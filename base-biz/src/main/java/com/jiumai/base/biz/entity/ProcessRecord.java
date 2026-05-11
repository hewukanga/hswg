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
 * 流程记录表(流程创建时生成全部记录)
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_process_record")
@ApiModel("流程记录表(流程创建时生成全部记录)")
public class ProcessRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("流程id")
    @TableField("process_id")
    private Long processId;

    @ApiModelProperty("级别(从0增加)")
    @TableField("level")
    private Integer level;

    @ApiModelProperty("节点审核角色id")
    @TableField("role_id")
    private Long roleId;

    @ApiModelProperty("节点角色名称(审批节点名称)")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty("节点类型 1.审批 2.知会")
    @TableField("node_type")
    private Integer nodeType;

    @ApiModelProperty("节点状态 1.未提交 2.已提交 3.待审批 4.已审批 5.已驳回 6.知会")
    @TableField("node_status")
    private Integer nodeStatus;

    @ApiModelProperty("驳回原因")
    @TableField("reject_reason")
    private String rejectReason;

    @ApiModelProperty("可操作人名称,用逗号隔开")
    @TableField("node_can_op_name")
    private String nodeCanOpName;

    @ApiModelProperty("节点操作人id")
    @TableField("node_op_id")
    private Long nodeOpId;

    @ApiModelProperty("节点操作人姓名")
    @TableField("node_op_name")
    private String nodeOpName;

    @ApiModelProperty("节点操作人部门")
    @TableField("node_op_org")
    private String nodeOpOrg;

    @ApiModelProperty("节点操作时间")
    @TableField("node_op_date")
    private LocalDateTime nodeOpDate;

    @ApiModelProperty("是否删除1：未删除 -1：已删除")
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty("乐观锁版本号")
    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    private Integer version;
}
