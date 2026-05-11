package com.jiumai.base.common.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 接口和资源的关联关系
 */
@Data
public class InterfaceResourceBO {
    @ApiModelProperty("接口id")
    private Long interfaceId;

    @ApiModelProperty("接口路径")
    private String interfacePath;

    @ApiModelProperty("接口名称")
    private String interfaceName;

    @ApiModelProperty("资源id")
    private Long resourceId;
}
