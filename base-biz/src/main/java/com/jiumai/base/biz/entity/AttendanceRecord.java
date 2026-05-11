package com.jiumai.base.biz.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.io.Serializable;

/**
 * <p>
 * 考勤记录表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_attendance_record")
@ApiModel("考勤记录表")
public class AttendanceRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户id")
    @TableField("op_id")
    private Long opId;

    @ApiModelProperty("姓名")
    @TableField("op_name")
    private String opName;

    @ApiModelProperty("工号")
    @TableField("job_number")
    private String jobNumber;

    @ApiModelProperty("角色名称(岗位)")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty("考勤组(考勤规则标题)")
    @TableField("attendance_group")
    private String attendanceGroup;

    @ApiModelProperty("打卡日期")
    @TableField("clock_in_date")
    private String clockInDate;

    @ApiModelProperty("签到时间")
    @TableField("sign_in_time")
    private String signInTime;

    @ApiModelProperty("签退时间")
    @TableField("sign_out_time")
    private String signOutTime;

    @ApiModelProperty("签到状态 1.正常 2,迟到 3,未打卡")
    @TableField("sign_in_status")
    private Integer signInStatus;

    @ApiModelProperty("签退状态 1.正常 2,早退 3,未打卡")
    @TableField("sign_out_status")
    private Integer signOutStatus;

    @ApiModelProperty("规则上班时间")
    @TableField("rule_start_time")
    private LocalTime ruleStartTime;

    @ApiModelProperty("规则下班时间")
    @TableField("rule_end_time")
    private LocalTime ruleEndTime;

    @ApiModelProperty("打卡地点")
    @TableField("address")
    private String address;

    @ApiModelProperty("打卡经度")
    @TableField("longitude")
    private Double longitude;

    @ApiModelProperty("打卡纬度")
    @TableField("latitude")
    private Double latitude;

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

    @ApiModelProperty("签退打卡地点")
    @TableField("out_address")
    private String outAddress;

    @ApiModelProperty("是否老年机")
    @TableField("elderly_phone")
    private Boolean elderlyPhone;
}
