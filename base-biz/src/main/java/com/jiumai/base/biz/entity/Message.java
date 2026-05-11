package com.jiumai.base.biz.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 通知互动消息表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_message")
@ApiModel("通知互动消息表")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("商品id")
    @TableField("goods_id")
    private Long goodsId;

    @ApiModelProperty("商品标题")
    @TableField("goods_title")
    private String goodsTitle;

    @ApiModelProperty("商品费用")
    @TableField("goods_price")
    private BigDecimal goodsPrice;

    @ApiModelProperty("消息内容")
    @TableField("message_content")
    private String messageContent;

    @ApiModelProperty("消息来源1.闲置广场 2.宠物广场 3.活动 4.服务工单 5.岗位签到提醒")
    @TableField("source")
    private Integer source;

    @ApiModelProperty("消息阅读状态")
    @TableField("read_status")
    private Boolean readStatus;

    @ApiModelProperty("消息类型1.私聊消息2.留言消息 3.管理端通知")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("消息接收人id")
    @TableField("receiver_id")
    private Long receiverId;

    @ApiModelProperty("消息接收人姓名")
    @TableField("receiver_name")
    private String receiverName;

    @ApiModelProperty("消息发送人id")
    @TableField("sender_id")
    private Long senderId;

    @ApiModelProperty("消息发送人姓名")
    @TableField("sender_name")
    private String senderName;

    @ApiModelProperty("消息发送人头像")
    @TableField("sender_head_image")
    private String senderHeadImage;

    @ApiModelProperty("商品图片")
    @TableField("goods_images")
    private String goodsImages;

    @ApiModelProperty("私聊未读消息数量")
    @TableField("private_unread_message_sum")
    private Integer privateUnreadMessageSum;

    @ApiModelProperty("消息发送时间")
    @TableField("message_date")
    private LocalDateTime messageDate;

    @ApiModelProperty("乐观锁版本号")
    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    private Integer version;

    @ApiModelProperty("商品所属人姓名")
    @TableField("goods_user_name")
    private String goodsUserName;

    @ApiModelProperty("商品所属人头像")
    @TableField("goods_user_head_image")
    private String goodsUserHeadImage;
}
