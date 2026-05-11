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
 * 考勤规则表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_attendance_rule")
@ApiModel("考勤规则表")
public class AttendanceRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("考勤规则标题")
    @TableField("rule_name")
    private String ruleName;

    @ApiModelProperty("考勤规则介绍")
    @TableField("rule_description")
    private String ruleDescription;

    @ApiModelProperty("打卡中心地址名称")
    @TableField("center_address")
    private String centerAddress;

    @ApiModelProperty("打卡中心地址经度")
    @TableField("center_longitude")
    private Double centerLongitude;

    @ApiModelProperty("打卡中心地址纬度")
    @TableField("center_latitude")
    private Double centerLatitude;

    @ApiModelProperty("打卡中心范围")
    @TableField("center_range")
    private Integer centerRange;

    @ApiModelProperty("考勤人员")
    @TableField("attendance_person")
    private String attendancePerson;

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

    @ApiModelProperty("是否夜班1-是  2-否")
    @TableField("team_flag")
    private Integer teamFlag;
}
