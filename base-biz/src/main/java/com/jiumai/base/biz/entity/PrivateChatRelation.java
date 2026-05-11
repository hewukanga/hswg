package com.jiumai.base.biz.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>
 * 私聊消息记录关系表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_private_chat_relation")
@ApiModel("私聊消息记录关系表")
public class PrivateChatRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("聊天人1")
    @TableField("chat_one")
    private Long chatOne;

    @ApiModelProperty("聊天人2")
    @TableField("chat_two")
    private Long chatTwo;

    @ApiModelProperty("商品id")
    @TableField("goods_id")
    private Long goodsId;
}
