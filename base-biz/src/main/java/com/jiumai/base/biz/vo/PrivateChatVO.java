package com.jiumai.base.biz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 私聊消息记录表视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("私聊消息记录表视图类")
public class PrivateChatVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("私聊消息记录关系表id")
    private Long privateChatRelationId;

    @ApiModelProperty("消息内容")
    private String messageContent;

    @ApiModelProperty("消息时间")
    private LocalDateTime messageDate;

    @ApiModelProperty("消息接收人id")
    private Long receiverId;

    @ApiModelProperty("消息发送人id")
    private Long senderId;

    @ApiModelProperty("消息类型1.文本 2.时间 3.图片")
    private Integer privateMessageType;
}
