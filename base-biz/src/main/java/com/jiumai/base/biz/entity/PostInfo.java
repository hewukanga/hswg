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
 * 岗位信息表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_post_info")
@ApiModel("岗位信息表")
public class PostInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("岗位id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("岗位名称")
    @TableField("post_name")
    private String postName;

    @ApiModelProperty("岗位类型(1.秩序维护 2.环境保洁 3.绿化养护 4.邻里服务)")
    @TableField("post_type")
    private Integer postType;

    @ApiModelProperty("开始时间")
    @TableField("start_date")
    private LocalDateTime startDate;

    @ApiModelProperty("结束时间")
    @TableField("end_date")
    private LocalDateTime endDate;

    @ApiModelProperty("单次作业积分")
    @TableField("single_assignment_points")
    private Integer singleAssignmentPoints;

    @ApiModelProperty("所需人数")
    @TableField("need_person_num")
    private Integer needPersonNum;

    @ApiModelProperty("报名人数")
    @TableField("sign_up_num")
    private Integer signUpNum;

    @TableField("registration_deadline")
    private Integer registrationDeadline;

    @ApiModelProperty("报名年龄限制")
    @TableField("registration_age_limit")
    private Integer registrationAgeLimit;

    @ApiModelProperty("签到动态提醒类型")
    @TableField("sign_in_remind_type")
    private Integer signInRemindType;

    @ApiModelProperty("岗位说明")
    @TableField("job_description")
    private String jobDescription;

    @ApiModelProperty("岗位状态(1.进行中 2.已结束)")
    @TableField("post_status")
    private Integer postStatus;

    @ApiModelProperty("岗位图片")
    @TableField("post_images")
    private String postImages;

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

    @ApiModelProperty("报名截止时间")
    @TableField("registration_deadline_date")
    private LocalDateTime registrationDeadlineDate;

    @ApiModelProperty("是否限制年龄")
    @TableField("registration_age_limit_value")
    private Boolean registrationAgeLimitValue;

    @ApiModelProperty("是否已提醒")
    @TableField("remind_value")
    private Boolean remindValue;

    @ApiModelProperty("打卡范围(米)")
    @TableField("center_range")
    private Integer centerRange;

    @ApiModelProperty("提醒时间")
    @TableField("remind_date")
    private LocalDateTime remindDate;

    @ApiModelProperty("区域id")
    @TableField("area_ids")
    private String areaIds;
}
