package com.jiumai.base.common.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 接口和角色的关联关系
 */
@Data
public class InterfaceRoleBO {

    @ApiModelProperty("接口id")
    private Long interfaceId;

    @ApiModelProperty("接口路径")
    private String interfaceUri;

    @ApiModelProperty("接口名称")
    private String interfaceName;

    @ApiModelProperty("角色id")
    private Long roleId;
}
