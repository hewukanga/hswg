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
 * 整改单
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_rectification_form")
@ApiModel("整改单")
public class RectificationForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("整改单名称")
    @TableField("rectification_form_name")
    private String rectificationFormName;

    @ApiModelProperty("检查日期")
    @TableField("check_date")
    private LocalDateTime checkDate;

    @ApiModelProperty("截止日期")
    @TableField("deadline")
    private LocalDateTime deadline;

    @ApiModelProperty("检查地址id")
    @TableField("area_id")
    private Long areaId;

    @ApiModelProperty("检查地址名称")
    @TableField("area_name")
    private String areaName;

    @ApiModelProperty("总扣分")
    @TableField("total_deductions")
    private Double totalDeductions;

    @ApiModelProperty("状态 0 -> 已处理,1 -> 待处理,2 -> 处理中")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("整改问题")
    @TableField("rectification_issues")
    private String rectificationIssues;

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
