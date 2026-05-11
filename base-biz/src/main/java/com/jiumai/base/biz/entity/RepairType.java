package com.jiumai.base.biz.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.io.Serializable;

/**
 * <p>
 * 维修项目配置表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_repair_type")
@ApiModel("维修项目配置表")
public class RepairType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("维修项目名称")
    @TableField("repair_name")
    private String repairName;

    @ApiModelProperty("单价")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty("单位")
    @TableField("unit")
    private String unit;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("父维修项目id")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty("工单类型 1.室内维修 2.公共区域报修")
    @TableField("type")
    private Integer type;
}
