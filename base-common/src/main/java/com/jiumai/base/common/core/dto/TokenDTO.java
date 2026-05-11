package com.jiumai.base.common.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author skm
 * @date 2023/1/30 11:36
 */
@Data
public class TokenDTO {

    @ApiModelProperty("刷新token")
    private String refreshToken;

    @ApiModelProperty("刷新token过期时间")
    private Long refreshTokenExpirationTime;

    @ApiModelProperty("请求token")
    private String requestToken;

    @ApiModelProperty("刷新token过期时间")
    private Long requestTokenExpirationTime;


}
