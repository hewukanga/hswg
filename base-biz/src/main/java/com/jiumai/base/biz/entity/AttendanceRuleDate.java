package com.jiumai.base.biz.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalTime;
import java.io.Serializable;

/**
 * <p>
 * 考勤规则日期表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_attendance_rule_date")
@ApiModel("考勤规则日期表")
public class AttendanceRuleDate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("考勤规则id")
    @TableField("rule_id")
    private Long ruleId;

    @ApiModelProperty("星期")
    @TableField("week")
    private String week;

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
