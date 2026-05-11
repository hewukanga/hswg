package com.jiumai.base.sm.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jiumai.base.common.core.enums.TriggerStatusEnum;
import com.jiumai.base.common.core.enums.TriggerTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.quartz.JobDataMap;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="保存或修改定时任务请求入参", description="保存或修改定时任务请求入参")
public class SmQuartzDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
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

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifyDate;
}
