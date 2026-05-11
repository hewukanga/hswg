package com.jiumai.base.biz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 活动报名表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("活动报名表拓展类")
public class ActivitySignUpDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("活动id")
    private Long activityId;

    @ApiModelProperty("活动类型1.社区活动 2.自愿者活动 3.党建活动")
    private Integer activityType;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("姓名")
    private String userName;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("身份证号")
    private String idCard;

    @ApiModelProperty("微信号")
    private String weChat;

    @ApiModelProperty("头像图片")
    private String avatarImageUrl;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("报名时间")
    private LocalDateTime signUpDate;

    @ApiModelProperty("签到状态")
    private Boolean signInStatus;

    @ApiModelProperty("签到时间")
    private LocalDateTime signInDate;

    @ApiModelProperty("来源 1.线上 2.线下")
    private Integer source;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
