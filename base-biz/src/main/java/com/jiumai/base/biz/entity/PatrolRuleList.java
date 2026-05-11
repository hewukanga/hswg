package com.jiumai.base.biz.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.io.Serializable;

/**
 * <p>
 * 巡查单规则表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_patrol_rule_list")
@ApiModel("巡查单规则表")
public class PatrolRuleList implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("巡查单名称")
    @TableField("rule_name")
    private String ruleName;

    @ApiModelProperty("巡查单描述")
    @TableField("rule_description")
    private String ruleDescription;

    @ApiModelProperty("巡查单类型")
    @TableField("rule_type")
    private Integer ruleType;

    @ApiModelProperty("巡查单开始日期")
    @TableField("rule_start_date")
    private LocalDate ruleStartDate;

    @ApiModelProperty("巡查单结束日期")
    @TableField("rule_end_date")
    private LocalDate ruleEndDate;

    @ApiModelProperty("巡查频率1.每日 2.每二日 3.每三日 4.每周 5.每月")
    @TableField("rule_frequency")
    private Integer ruleFrequency;

    @ApiModelProperty("巡查点位名称(逗号隔开)")
    @TableField("rule_point_name")
    private String rulePointName;

    @ApiModelProperty("巡查单规则启用状态")
    @TableField("rule_enable_status")
    private Boolean ruleEnableStatus;

    @ApiModelProperty("巡查时间段开始时间")
    @TableField("time_slot_start")
    private LocalTime timeSlotStart;

    @ApiModelProperty("时间段结束时间")
    @TableField("time_slot_end")
    private LocalTime timeSlotEnd;

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

    @ApiModelProperty("定时任务名称")
    @TableField("trigger_job_name")
    private String triggerJobName;

    @ApiModelProperty("定时任务组名称")
    @TableField("trigger_job_group")
    private String triggerJobGroup;

    @ApiModelProperty("定时任务cron表达式")
    @TableField("trigger_cron_expression")
    private String triggerCronExpression;
}
