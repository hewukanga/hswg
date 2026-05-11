package com.jiumai.base.sm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ Author     ：cz
 * @ Date       ：Created in 2023-01-0317:35
 * @ Description：
 * @ Modified By：
 */
@Data
public class LoginDTO {

    @ApiModelProperty(value = "用户名")
    private String opLoginName;

    @ApiModelProperty(value = "密码")
    private String password;

}
