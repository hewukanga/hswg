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
 * 巡查单表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("巡查单表拓展类")
public class PatrolListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("规则id")
    private Long ruleId;

    @ApiModelProperty("巡查单名称")
    private String ruleName;

    @ApiModelProperty("巡查人员名称(逗号隔开)")
    private String ruleSuitUser;

    @ApiModelProperty("巡查开始日期")
    private LocalDateTime patrolStartDate;

    @ApiModelProperty("巡查结束日期")
    private LocalDateTime patrolEndDate;

    @ApiModelProperty("巡查率")
    private Double patrolRate;

    @ApiModelProperty("是否巡查完")
    private Boolean patrolCompleteStatus;

    @ApiModelProperty("创建日期")
    private LocalDateTime createDate;
}
