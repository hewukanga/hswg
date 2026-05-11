package com.jiumai.base.biz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.io.Serializable;

/**
 * <p>
 * 维修项目配置表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("维修项目配置表拓展类")
public class RepairTypeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty("维修项目名称")
    private String repairName;

    @ApiModelProperty("单价")
    private BigDecimal price;

    @ApiModelProperty("单位")
    private String unit;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("父维修项目id")
    private Long parentId;

    @ApiModelProperty("工单类型 1.室内维修 2.公共区域报修")
    private Integer type;
}
