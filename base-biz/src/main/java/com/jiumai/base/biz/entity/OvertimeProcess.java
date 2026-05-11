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
 * 加班记录表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_overtime_process")
@ApiModel("加班记录表")
public class OvertimeProcess implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("类型 1.加班 2.顶班")
    @TableField("overtime_type")
    private Integer overtimeType;

    @ApiModelProperty("加班/顶班人id")
    @TableField("overtime_person_id")
    private Long overtimePersonId;

    @ApiModelProperty("加班/顶班人姓名")
    @TableField("overtime_person_name")
    private String overtimePersonName;

    @ApiModelProperty("加班/顶班人手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("加班/顶班人部门")
    @TableField("org_name")
    private String orgName;

    @ApiModelProperty("被顶班人id")
    @TableField("top_shift_person_id")
    private Long topShiftPersonId;

    @ApiModelProperty("被顶班人名称")
    @TableField("top_shift_person_name")
    private String topShiftPersonName;

    @ApiModelProperty("开始时间")
    @TableField("overtime_start_time")
    private LocalDateTime overtimeStartTime;

    @ApiModelProperty("结束时间")
    @TableField("overtime_end_time")
    private LocalDateTime overtimeEndTime;

    @ApiModelProperty("时长(小时)")
    @TableField("overtime_duration")
    private Double overtimeDuration;

    @ApiModelProperty("原因")
    @TableField("overtime_reason")
    private String overtimeReason;

    @ApiModelProperty("状态 1.未提交 2.待接收 3.已拒绝 4.已接收")
    @TableField("overtime_status")
    private Integer overtimeStatus;

    @ApiModelProperty("是否存草稿")
    @TableField("draft_status")
    private Boolean draftStatus;

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

    @ApiModelProperty("是否删除 1:未删除 -1:已删除")
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty("乐观锁版本号")
    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    private Integer version;
}
