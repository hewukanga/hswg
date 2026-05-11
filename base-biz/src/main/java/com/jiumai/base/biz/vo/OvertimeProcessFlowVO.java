package com.jiumai.base.biz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 加班流程表视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("加班流程表视图类")
public class OvertimeProcessFlowVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("加班id")
    private Long processId;

    @ApiModelProperty("流程类型 1.下发 2.接收")
    private Integer flowType;

    @ApiModelProperty("节点操作人id")
    private Long nodeOpId;

    @ApiModelProperty("节点操作人姓名")
    private String nodeOpName;

    @ApiModelProperty("节点操作人角色ID")
    private Long nodeOpRoleId;

    @ApiModelProperty("节点操作人角色名称")
    private String nodeOpRoleName;

    @ApiModelProperty("节点操作时间")
    private LocalDateTime nodeOpDate;

    @ApiModelProperty("拒绝原因")
    private String refuseReason;

    @ApiModelProperty("创建人姓名")
    private String createName;

    @ApiModelProperty("创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty("修改人姓名")
    private String modifyName;

    @ApiModelProperty("修改日期")
    private LocalDateTime modifyDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
