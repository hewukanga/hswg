package com.jiumai.base.sm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 修改密码
 */
@Data
@ApiModel(value = "修改密码")
public class UpdatePwdDTO {

    @ApiModelProperty(value = "原密码")
    private String oldPassword;

    @ApiModelProperty(value = "新密码")
    private String newPassword;

    @ApiModelProperty(value = "二次确认密码")
    private String rePassword;

}