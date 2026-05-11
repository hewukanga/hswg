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
 * 活动报名表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_activity_sign_up")
@ApiModel("活动报名表")
public class ActivitySignUp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("活动id")
    @TableField("activity_id")
    private Long activityId;

    @ApiModelProperty("活动类型1.社区活动 2.自愿者活动 3.党建活动")
    @TableField("activity_type")
    private Integer activityType;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("姓名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("身份证号")
    @TableField("id_card")
    private String idCard;

    @ApiModelProperty("微信号")
    @TableField("we_chat")
    private String weChat;

    @ApiModelProperty("头像图片")
    @TableField("avatar_image_url")
    private String avatarImageUrl;

    @ApiModelProperty("性别")
    @TableField("sex")
    private String sex;

    @ApiModelProperty("年龄")
    @TableField("age")
    private Integer age;

    @ApiModelProperty("报名时间")
    @TableField("sign_up_date")
    private LocalDateTime signUpDate;

    @ApiModelProperty("签到状态")
    @TableField("sign_in_status")
    private Boolean signInStatus;

    @ApiModelProperty("签到时间")
    @TableField("sign_in_date")
    private LocalDateTime signInDate;

    @ApiModelProperty("来源 1.线上 2.线下")
    @TableField("source")
    private Integer source;

    @ApiModelProperty("是否删除1：未删除 -1：已删除")
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty("乐观锁版本号")
    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    private Integer version;
}
