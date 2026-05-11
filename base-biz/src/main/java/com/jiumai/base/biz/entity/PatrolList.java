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
 * 巡查单表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_patrol_list")
@ApiModel("巡查单表")
public class PatrolList implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("规则id")
    @TableField("rule_id")
    private Long ruleId;

    @ApiModelProperty("巡查单名称")
    @TableField("rule_name")
    private String ruleName;

    @ApiModelProperty("巡查人员名称(逗号隔开)")
    @TableField("rule_suit_user")
    private String ruleSuitUser;

    @ApiModelProperty("巡查开始日期")
    @TableField("patrol_start_date")
    private LocalDateTime patrolStartDate;

    @ApiModelProperty("巡查结束日期")
    @TableField("patrol_end_date")
    private LocalDateTime patrolEndDate;

    @ApiModelProperty("巡查率")
    @TableField("patrol_rate")
    private Double patrolRate;

    @ApiModelProperty("是否巡查完")
    @TableField("patrol_complete_status")
    private Boolean patrolCompleteStatus;

    @ApiModelProperty("创建日期")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;
}
