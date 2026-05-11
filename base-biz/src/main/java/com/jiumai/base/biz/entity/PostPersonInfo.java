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
 * 岗位人员信息表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_post_person_info")
@ApiModel("岗位人员信息表")
public class PostPersonInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("岗位id")
    @TableField("post_id")
    private Long postId;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("报名状态(1.待审核 2.已通过 3.未通过 4.已退出)")
    @TableField("sign_up_status")
    private Integer signUpStatus;

    @ApiModelProperty("报名拒绝原因")
    @TableField("sign_up_reject_reason")
    private String signUpRejectReason;

    @ApiModelProperty("赋分状态(1.已赋分 2.未赋分)")
    @TableField("give_score_status")
    private Integer giveScoreStatus;

    @ApiModelProperty("作业审核情况(1.待审核 2.已通过并赋分 3.已拒绝 4.异常结束)")
    @TableField("work_audit_status")
    private Integer workAuditStatus;

    @ApiModelProperty("作业审核拒绝原因")
    @TableField("work_audit_reject_reason")
    private String workAuditRejectReason;

    @ApiModelProperty("作业效果评价(1.优秀 2.良好 2.一般)")
    @TableField("work_evaluate")
    private Integer workEvaluate;

    @ApiModelProperty("签到时间")
    @TableField("sign_in_time")
    private LocalDateTime signInTime;

    @ApiModelProperty("签到地点")
    @TableField("sign_in_address")
    private String signInAddress;

    @ApiModelProperty("作业前图片")
    @TableField("work_before_images")
    private String workBeforeImages;

    @ApiModelProperty("作业后图片")
    @TableField("work_after_images")
    private String workAfterImages;

    @ApiModelProperty("签退时间")
    @TableField("sign_out_time")
    private LocalDateTime signOutTime;

    @ApiModelProperty("签退地点")
    @TableField("sign_out_address")
    private String signOutAddress;

    @ApiModelProperty("花费时长(**时**分)")
    @TableField("consumption_time")
    private String consumptionTime;

    @ApiModelProperty("工作完结备注")
    @TableField("work_complete_remark")
    private String workCompleteRemark;

    @ApiModelProperty("异常结束原因")
    @TableField("abnormal_end_reason")
    private String abnormalEndReason;

    @ApiModelProperty("岗位地址id")
    @TableField("post_address_id")
    private Long postAddressId;

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

    @ApiModelProperty("是否提醒")
    @TableField("remind")
    private Boolean remind;

    @ApiModelProperty("年龄")
    @TableField("user_age")
    private Integer userAge;
}
