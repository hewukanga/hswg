package com.jiumai.base.biz.dto;

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
 * 巡查单规则表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("巡查单规则表拓展类")
public class PatrolRuleListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("巡查单名称")
    private String ruleName;

    @ApiModelProperty("巡查单描述")
    private String ruleDescription;

    @ApiModelProperty("巡查单类型")
    private Integer ruleType;

    @ApiModelProperty("巡查单开始日期")
    private LocalDate ruleStartDate;

    @ApiModelProperty("巡查单结束日期")
    private LocalDate ruleEndDate;

    @ApiModelProperty("巡查频率1.每日 2.每二日 3.每三日 4.每周 5.每月")
    private Integer ruleFrequency;

    @ApiModelProperty("巡查点位名称(逗号隔开)")
    private String rulePointName;

    @ApiModelProperty("巡查单规则启用状态")
    private Boolean ruleEnableStatus;

    @ApiModelProperty("巡查时间段开始时间")
    private LocalTime timeSlotStart;

    @ApiModelProperty("时间段结束时间")
    private LocalTime timeSlotEnd;

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

    @ApiModelProperty("定时任务名称")
    private String triggerJobName;

    @ApiModelProperty("定时任务组名称")
    private String triggerJobGroup;

    @ApiModelProperty("定时任务cron表达式")
    private String triggerCronExpression;
}
