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
 * 流程表视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("流程表视图类")
public class ProcessVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("开始时间")
    private LocalDateTime startDate;

    @ApiModelProperty("结束时间")
    private LocalDateTime endDate;

    @ApiModelProperty("类型 1.加班 2.请假 3.补打卡")
    private Integer type;

    @ApiModelProperty("请假类型1.年休假 2.事假 3.病假 4.婚假 5.丧假 6.产假 7.其他 8.调休")
    private Integer leaveType;

    @ApiModelProperty("加班类型 1.调休 2.加班工资")
    private Integer overtimeType;

    @ApiModelProperty("流程状态 1.未提交 2,审核中 3.审核通过 4.审核驳回")
    private Integer processStatus;

    @ApiModelProperty("加班时长(小时)")
    private Double overtimeDuration;

    @ApiModelProperty("请假时长(天)")
    private Double leaveDuration;

    @ApiModelProperty("原因")
    private String reason;

    @ApiModelProperty("图片")
    private String images;

    @ApiModelProperty("当前审批节点角色id(用于分页查询)")
    private Long currenAuditRoleId;

    @ApiModelProperty("已知会角色id(#拼接用于分页查询)")
    private String toldRoleId;

    @ApiModelProperty("已审批人物id(#拼接用于分页查询)")
    private String auditedOpId;

    @ApiModelProperty("驳回角色id(用于分页查询)")
    private Long rejectOpId;

    @ApiModelProperty("提交人所在片区id")
    private Long commitOrgId;

    @ApiModelProperty("创建人姓名")
    private String createName;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("创建人部门")
    private String createOrg;

    @ApiModelProperty("创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty("修改人姓名")
    private String modifyName;

    @ApiModelProperty("修改日期")
    private LocalDateTime modifyDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;

    @ApiModelProperty("补卡时长(小时)")
    private Double replacementDuration;
}
