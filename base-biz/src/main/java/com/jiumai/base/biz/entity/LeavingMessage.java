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
 * 留言表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_leaving_message")
@ApiModel("留言表")
public class LeavingMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("商品id")
    @TableField("goods_id")
    private Long goodsId;

    @TableField("content")
    private String content;

    @ApiModelProperty("层级1.一级直接回复商品 2.二级回复某个人")
    @TableField("level")
    private Integer level;

    @ApiModelProperty("发送人id")
    @TableField("sender_id")
    private Long senderId;

    @ApiModelProperty("发送人姓名")
    @TableField("sender_name")
    private String senderName;

    @ApiModelProperty("发送人头像")
    @TableField("sender_head_image")
    private String senderHeadImage;

    @ApiModelProperty("接收人id")
    @TableField("receiver_id")
    private Long receiverId;

    @ApiModelProperty("接收人姓名")
    @TableField("receiver_name")
    private String receiverName;

    @ApiModelProperty("接收人头像")
    @TableField("receiver_head_image")
    private String receiverHeadImage;

    @ApiModelProperty("是否管理端回复")
    @TableField("admin_reply")
    private Boolean adminReply;

    @ApiModelProperty("是否回复")
    @TableField("answer_status")
    private Boolean answerStatus;

    @ApiModelProperty("父留言id")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty("留言时间")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @ApiModelProperty("是否卖家")
    @TableField("buyers_flag")
    private Boolean buyersFlag;

    @ApiModelProperty("是否删除 1:未删除 -1:已删除")
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty("乐观锁版本号")
    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    private Integer version;
}
