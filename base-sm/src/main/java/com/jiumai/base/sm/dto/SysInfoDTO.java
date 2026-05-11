package com.jiumai.base.sm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：cz
 * @date ：Created in 2023-08-04
 * @description：
 */
@Data
@ApiModel(value = "系统信息")
public class SysInfoDTO {

    @ApiModelProperty(value = "SM4-key")
    private String key;

}
