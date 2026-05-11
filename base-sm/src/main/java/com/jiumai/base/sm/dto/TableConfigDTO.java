package com.jiumai.base.sm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ Author     ：cz
 * @ Date       ：Created in 2023-02-2314:45
 * @ Description：
 * @ Modified By：
 */
@Data
public class TableConfigDTO {

    @ApiModelProperty(value = "表编号")
    private String tableCode;

    @ApiModelProperty(value = "表名")
    private String tableName;

    @ApiModelProperty(value = "表格配置")
    private String tableConfig;

}
