package com.jiumai.base.biz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * activity视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("activity视图类")
public class ActivityVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("活动标题")
    private String activityTitle;

    @ApiModelProperty("活动类型1.社区活动 2.自愿者活动 3.党建活动")
    private Integer activityType;

    @ApiModelProperty("活动封面图")
    private String activityCoverPicture;

    @ApiModelProperty("活动地点")
    private String activityAddress;

    @ApiModelProperty("活动地点简称")
    private String activityAddressName;

    @ApiModelProperty("活动经度")
    private Double activityLongitude;

    @ApiModelProperty("活动纬度")
    private Double activityLatitude;

    @ApiModelProperty("活动开始时间")
    private LocalDateTime activityStartDate;

    @ApiModelProperty("活动结束时间")
    private LocalDateTime activityEndDate;

    @ApiModelProperty("活动介绍")
    private String activityIntroduce;

    @ApiModelProperty("是否开放报名")
    private Boolean openSignUp;

    @ApiModelProperty("报名开始时间")
    private LocalDateTime signUpStartDate;

    @ApiModelProperty("报名结束时间")
    private LocalDateTime signUpEndDate;

    @ApiModelProperty("报名人数上限")
    private Integer signUpLimit;

    @ApiModelProperty("已报名人数")
    private Integer registeredNum;

    @ApiModelProperty("活动状态 1.未开始报名 2.报名中 3,报名结束 4.已结束 5.已下架")
    private Integer activityStatus;

    @ApiModelProperty("审核状态 1.未提交 2.审核中 3.审核通过 4.审核驳回")
    private Integer auditStatus;

    @ApiModelProperty("区域路径(多个逗号隔开)")
    private String areaPath;

    @ApiModelProperty("组织路径（用于员工查询活动）")
    private String orgPath;

    @ApiModelProperty("驳回原因")
    private String rejectReason;

    @ApiModelProperty("活动二维码")
    private String activityQrCode;

    @ApiModelProperty("姓名标识")
    private Boolean userNameFlag;

    @ApiModelProperty("手机号标识")
    private Boolean phoneFlag;

    @ApiModelProperty("身份证标识")
    private Boolean idCardFlag;

    @ApiModelProperty("微信号标识")
    private Boolean weChatFlag;

    @ApiModelProperty("性别标识")
    private Boolean sexFlag;

    @ApiModelProperty("年龄标识")
    private Boolean ageFlag;

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
}
