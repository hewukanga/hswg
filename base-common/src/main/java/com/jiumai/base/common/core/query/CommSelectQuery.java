package com.jiumai.base.common.core.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 通用下拉查询需要的字段
 */
@Data
public class CommSelectQuery{
    @ApiModelProperty(value = "需要查询的表")
    private String q_tab;

    @ApiModelProperty(value = "需要查询出来ID的表字段名")
    private String q_id;

    @ApiModelProperty(value = "需要查询出来名称的表字段名")
    private String q_name;

    @ApiModelProperty(value = "需要查询的条件字段名")
    private String q_where;

    @ApiModelProperty(value = "需要查询的判断逻辑(等于还是like)")
    private String q_logic = "like";

    @ApiModelProperty(value = "需要查询的条件字段值")
    private String q_value;

}
