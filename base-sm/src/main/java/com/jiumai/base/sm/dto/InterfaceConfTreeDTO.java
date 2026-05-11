package com.jiumai.base.sm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
@ApiModel(value = "接口配置树形实体类", description = "接口配置树形实体类")
public class InterfaceConfTreeDTO {

    @ApiModelProperty("接口组名")
    private String interfaceGroupName;

    @ApiModelProperty(value = "接口信息")
    private List<InterfaceConfDTO> interfaceConfDTOS;

}
