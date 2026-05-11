package com.jiumai.base.biz.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 私聊消息记录表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_private_chat")
@ApiModel("私聊消息记录表")
public class PrivateChat implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("私聊消息记录关系表id")
    @TableField("private_chat_relation_id")
    private Long privateChatRelationId;

    @ApiModelProperty("消息内容")
    @TableField("message_content")
    private String messageContent;

    @ApiModelProperty("消息时间")
    @TableField("message_date")
    private LocalDateTime messageDate;

    @ApiModelProperty("消息接收人id")
    @TableField("receiver_id")
    private Long receiverId;

    @ApiModelProperty("消息发送人id")
    @TableField("sender_id")
    private Long senderId;

    @ApiModelProperty("消息类型1.文本 2.时间 3.图片")
    @TableField("private_message_type")
    private Integer privateMessageType;
}
