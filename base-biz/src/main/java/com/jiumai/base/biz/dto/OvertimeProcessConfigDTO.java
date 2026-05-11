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
 * 加班配置表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("加班配置表拓展类")
public class OvertimeProcessConfigDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("流程名称")
    private String processName;

    @ApiModelProperty("流程类型 1.加班 2.顶班")
    private Integer processType;

    @ApiModelProperty("流程描述")
    private String processDescription;

    @ApiModelProperty("应用组织id(组织id通过#拼接)")
    private String applicationOrgId;

    @ApiModelProperty("应用组织名称(组织名称通过,拼接)")
    private String applicationOrgName;

    @ApiModelProperty("下发人角色id")
    private Long issuedPersonRoleId;

    @ApiModelProperty("下发人角色名称")
    private String issuedPersonRoleName;

    @ApiModelProperty("被下发人/顶班人角色id")
    private Long receivePersonRoleId;

    @ApiModelProperty("被下发人/顶班人角色名称")
    private String receivePersonRoleName;

    @ApiModelProperty("被顶班人角色id")
    private Long topShiftRoleId;

    @ApiModelProperty("被顶班人角色名称")
    private String topShiftRoleName;

    @ApiModelProperty("是否启用")
    private Boolean enableStatus;

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
