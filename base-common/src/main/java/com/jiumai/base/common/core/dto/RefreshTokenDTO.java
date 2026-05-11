package com.jiumai.base.common.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author skm
 * @date 2023/1/30 17:36
 */
@Data
public class RefreshTokenDTO {

    @ApiModelProperty("刷新token")
    private String refreshToken;
}
