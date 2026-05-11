package com.jiumai.base.biz.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.Serializable;

/**
 * <p>
 * 排班记录表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_schedule_record")
@ApiModel("排班记录表")
public class ScheduleRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("人员id")
    @TableField("op_id")
    private Long opId;

    @ApiModelProperty("排班日期")
    @TableField("schedule_date")
    private LocalDate scheduleDate;

    @ApiModelProperty("上班时间")
    @TableField("start_time")
    private LocalTime startTime;

    @ApiModelProperty("下班时间")
    @TableField("end_time")
    private LocalTime endTime;

    @ApiModelProperty("是否删除1：未删除 -1：已删除")
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty("乐观锁版本号")
    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    private Integer version;
}
