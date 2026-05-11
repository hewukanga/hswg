package com.jiumai.base.sm.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

import com.jiumai.base.common.core.enums.TriggerStatusEnum;
import com.jiumai.base.common.core.enums.TriggerTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.quartz.JobDataMap;

/**
 * <p>
 * 
 * </p>
 *
 * @author mysql gen
 * @since 2023-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmQuartz对象", description="")
public class SmQuartz implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "job分组")
    private String jobGroup;

    @ApiModelProperty(value = "job名称")
    private String jobName;

    @ApiModelProperty(value = "触发器分组")
    private String triggerGroup;

    @ApiModelProperty(value = "触发器名称")
    private String triggerName;

    @ApiModelProperty(value = "状态 START_UP:启动 STOP:暂停")
    private TriggerStatusEnum status;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "触发器类型 CRON：cron表达式 SIMPLE：简单")
    private TriggerTypeEnum triggerType;

    @ApiModelProperty(value = "任务类全路径 例如com.jiumai.demo.Demo")
    private String jobClassNamePath;

    @ApiModelProperty(value = "cron表达式，触发器类型为cron时填入")
    private String cronExpression;

    @ApiModelProperty(value = "重复执行次数，0表示无限次数，触发器类型为simple时填入")
    private Integer repeatCount;

    @ApiModelProperty(value = "重复执行时间间隔，单位秒，触发器类型为simple时填入")
    private Integer repeatInterval;

    @ApiModelProperty(value = "定时任务开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "定时任务结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "定时任务入参")
    private String jobData;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private Long createId;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @ApiModelProperty(value = "修改人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long modifyId;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifyDate;

    @ApiModelProperty(value = "删除标志(1：未删除  -1：删除)",example = "1")
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty(value = "乐观锁版本号",example = "0")
    @TableField(fill = FieldFill.INSERT)
    @Version
    private Integer version;
}
