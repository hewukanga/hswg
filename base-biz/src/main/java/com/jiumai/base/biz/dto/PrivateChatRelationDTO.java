package com.jiumai.base.biz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>
 * 私聊消息记录关系表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("私聊消息记录关系表拓展类")
public class PrivateChatRelationDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("聊天人1")
    private Long chatOne;

    @ApiModelProperty("聊天人2")
    private Long chatTwo;

    @ApiModelProperty("商品id")
    private Long goodsId;
}
