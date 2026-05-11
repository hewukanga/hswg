package com.jiumai.base.common.core.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ Author     ：cz
 * @ Date       ：Created in 2022-06-2809:51
 * @ Description：
 * @ Modified By：
 */
@Data
public class BasePage {

    @ApiModelProperty("页码")
    private Integer page = 1;

    @ApiModelProperty("每页条数")
    private Integer size = 10;

}
