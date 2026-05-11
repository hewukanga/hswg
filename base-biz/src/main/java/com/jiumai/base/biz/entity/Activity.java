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
 * activity
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_activity")
@ApiModel("activity")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("活动标题")
    @TableField("activity_title")
    private String activityTitle;

    @ApiModelProperty("活动类型1.社区活动 2.自愿者活动 3.党建活动")
    @TableField("activity_type")
    private Integer activityType;

    @ApiModelProperty("活动封面图")
    @TableField("activity_cover_picture")
    private String activityCoverPicture;

    @ApiModelProperty("活动地点")
    @TableField("activity_address")
    private String activityAddress;

    @ApiModelProperty("活动地点简称")
    @TableField("activity_address_name")
    private String activityAddressName;

    @ApiModelProperty("活动经度")
    @TableField("activity_longitude")
    private Double activityLongitude;

    @ApiModelProperty("活动纬度")
    @TableField("activity_latitude")
    private Double activityLatitude;

    @ApiModelProperty("活动开始时间")
    @TableField("activity_start_date")
    private LocalDateTime activityStartDate;

    @ApiModelProperty("活动结束时间")
    @TableField("activity_end_date")
    private LocalDateTime activityEndDate;

    @ApiModelProperty("活动介绍")
    @TableField("activity_introduce")
    private String activityIntroduce;

    @ApiModelProperty("是否开放报名")
    @TableField("open_sign_up")
    private Boolean openSignUp;

    @ApiModelProperty("报名开始时间")
    @TableField("sign_up_start_date")
    private LocalDateTime signUpStartDate;

    @ApiModelProperty("报名结束时间")
    @TableField("sign_up_end_date")
    private LocalDateTime signUpEndDate;

    @ApiModelProperty("报名人数上限")
    @TableField("sign_up_limit")
    private Integer signUpLimit;

    @ApiModelProperty("已报名人数")
    @TableField("registered_num")
    private Integer registeredNum;

    @ApiModelProperty("活动状态 1.未开始报名 2.报名中 3,报名结束 4.已结束 5.已下架")
    @TableField("activity_status")
    private Integer activityStatus;

    @ApiModelProperty("审核状态 1.未提交 2.审核中 3.审核通过 4.审核驳回")
    @TableField("audit_status")
    private Integer auditStatus;

    @ApiModelProperty("区域路径(多个逗号隔开)")
    @TableField("area_path")
    private String areaPath;

    @ApiModelProperty("组织路径（用于员工查询活动）")
    @TableField("org_path")
    private String orgPath;

    @ApiModelProperty("驳回原因")
    @TableField("reject_reason")
    private String rejectReason;

    @ApiModelProperty("活动二维码")
    @TableField("activity_qr_code")
    private String activityQrCode;

    @ApiModelProperty("姓名标识")
    @TableField("user_name_flag")
    private Boolean userNameFlag;

    @ApiModelProperty("手机号标识")
    @TableField("phone_flag")
    private Boolean phoneFlag;

    @ApiModelProperty("身份证标识")
    @TableField("id_card_flag")
    private Boolean idCardFlag;

    @ApiModelProperty("微信号标识")
    @TableField("we_chat_flag")
    private Boolean weChatFlag;

    @ApiModelProperty("性别标识")
    @TableField("sex_flag")
    private Boolean sexFlag;

    @ApiModelProperty("年龄标识")
    @TableField("age_flag")
    private Boolean ageFlag;

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
}
