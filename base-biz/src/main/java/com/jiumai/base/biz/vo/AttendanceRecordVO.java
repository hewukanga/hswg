package com.jiumai.base.biz.vo;

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
 * 考勤记录表视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("考勤记录表视图类")
public class AttendanceRecordVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("用户id")
    private Long opId;

    @ApiModelProperty("姓名")
    private String opName;

    @ApiModelProperty("工号")
    private String jobNumber;

    @ApiModelProperty("角色名称(岗位)")
    private String roleName;

    @ApiModelProperty("考勤组(考勤规则标题)")
    private String attendanceGroup;

    @ApiModelProperty("打卡日期")
    private String clockInDate;

    @ApiModelProperty("签到时间")
    private String signInTime;

    @ApiModelProperty("签退时间")
    private String signOutTime;

    @ApiModelProperty("签到状态 1.正常 2,迟到 3,未打卡")
    private Integer signInStatus;

    @ApiModelProperty("签退状态 1.正常 2,早退 3,未打卡")
    private Integer signOutStatus;

    @ApiModelProperty("规则上班时间")
    private LocalTime ruleStartTime;

    @ApiModelProperty("规则下班时间")
    private LocalTime ruleEndTime;

    @ApiModelProperty("打卡地点")
    private String address;

    @ApiModelProperty("打卡经度")
    private Double longitude;

    @ApiModelProperty("打卡纬度")
    private Double latitude;

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

    @ApiModelProperty("签退打卡地点")
    private String outAddress;

    @ApiModelProperty("是否老年机")
    private Boolean elderlyPhone;
}
