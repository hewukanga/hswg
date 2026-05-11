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
 * 保洁区域人员
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_cleaning_area_op")
@ApiModel("保洁区域人员")
public class CleaningAreaOp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("保洁区域id")
    @TableField("cleaning_area_id")
    private Long cleaningAreaId;

    @ApiModelProperty("人员类型 1.保洁人员 2.负责人")
    @TableField("op_type")
    private Integer opType;

    @ApiModelProperty("人员id")
    @TableField("op_id")
    private Long opId;

    @ApiModelProperty("人员姓名")
    @TableField("op_name")
    private String opName;

    @ApiModelProperty("人员手机号")
    @TableField("op_phone")
    private String opPhone;

    @ApiModelProperty("创建人id")
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private Long createId;

    @ApiModelProperty("创建人姓名")
    @TableField(value = "create_name", fill = FieldFill.INSERT)
    private String createName;

    @ApiModelProperty("创建日期")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;
}
