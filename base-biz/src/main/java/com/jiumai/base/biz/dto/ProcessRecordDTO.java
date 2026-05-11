package com.jiumai.base.biz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 流程记录表(流程创建时生成全部记录)拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("流程记录表(流程创建时生成全部记录)拓展类")
public class ProcessRecordDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("流程id")
    private Long processId;

    @ApiModelProperty("级别(从0增加)")
    private Integer level;

    @ApiModelProperty("节点审核角色id")
    private Long roleId;

    @ApiModelProperty("节点角色名称(审批节点名称)")
    private String roleName;

    @ApiModelProperty("节点类型 1.审批 2.知会")
    private Integer nodeType;

    @ApiModelProperty("节点状态 1.未提交 2.已提交 3.待审批 4.已审批 5.已驳回 6.知会")
    private Integer nodeStatus;

    @ApiModelProperty("驳回原因")
    private String rejectReason;

    @ApiModelProperty("可操作人名称,用逗号隔开")
    private String nodeCanOpName;

    @ApiModelProperty("节点操作人id")
    private Long nodeOpId;

    @ApiModelProperty("节点操作人姓名")
    private String nodeOpName;

    @ApiModelProperty("节点操作人部门")
    private String nodeOpOrg;

    @ApiModelProperty("节点操作时间")
    private LocalDateTime nodeOpDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
