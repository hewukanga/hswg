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
 * 岗位人员信息表视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("岗位人员信息表视图类")
public class PostPersonInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("岗位id")
    private Long postId;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("报名状态(1.待审核 2.已通过 3.未通过 4.已退出)")
    private Integer signUpStatus;

    @ApiModelProperty("报名拒绝原因")
    private String signUpRejectReason;

    @ApiModelProperty("赋分状态(1.已赋分 2.未赋分)")
    private Integer giveScoreStatus;

    @ApiModelProperty("作业审核情况(1.待审核 2.已通过并赋分 3.已拒绝 4.异常结束)")
    private Integer workAuditStatus;

    @ApiModelProperty("作业审核拒绝原因")
    private String workAuditRejectReason;

    @ApiModelProperty("作业效果评价(1.优秀 2.良好 2.一般)")
    private Integer workEvaluate;

    @ApiModelProperty("签到时间")
    private LocalDateTime signInTime;

    @ApiModelProperty("签到地点")
    private String signInAddress;

    @ApiModelProperty("作业前图片")
    private String workBeforeImages;

    @ApiModelProperty("作业后图片")
    private String workAfterImages;

    @ApiModelProperty("签退时间")
    private LocalDateTime signOutTime;

    @ApiModelProperty("签退地点")
    private String signOutAddress;

    @ApiModelProperty("花费时长(**时**分)")
    private String consumptionTime;

    @ApiModelProperty("工作完结备注")
    private String workCompleteRemark;

    @ApiModelProperty("异常结束原因")
    private String abnormalEndReason;

    @ApiModelProperty("岗位地址id")
    private Long postAddressId;

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

    @ApiModelProperty("是否提醒")
    private Boolean remind;

    @ApiModelProperty("年龄")
    private Integer userAge;
}
