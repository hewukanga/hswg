package com.jiumai.base.biz.vo;

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
 * 通知互动消息表视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("通知互动消息表视图类")
public class MessageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("商品id")
    private Long goodsId;

    @ApiModelProperty("商品标题")
    private String goodsTitle;

    @ApiModelProperty("商品费用")
    private BigDecimal goodsPrice;

    @ApiModelProperty("消息内容")
    private String messageContent;

    @ApiModelProperty("消息来源1.闲置广场 2.宠物广场 3.活动 4.服务工单 5.岗位签到提醒")
    private Integer source;

    @ApiModelProperty("消息阅读状态")
    private Boolean readStatus;

    @ApiModelProperty("消息类型1.私聊消息2.留言消息 3.管理端通知")
    private Integer type;

    @ApiModelProperty("消息接收人id")
    private Long receiverId;

    @ApiModelProperty("消息接收人姓名")
    private String receiverName;

    @ApiModelProperty("消息发送人id")
    private Long senderId;

    @ApiModelProperty("消息发送人姓名")
    private String senderName;

    @ApiModelProperty("消息发送人头像")
    private String senderHeadImage;

    @ApiModelProperty("商品图片")
    private String goodsImages;

    @ApiModelProperty("私聊未读消息数量")
    private Integer privateUnreadMessageSum;

    @ApiModelProperty("消息发送时间")
    private LocalDateTime messageDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;

    @ApiModelProperty("商品所属人姓名")
    private String goodsUserName;

    @ApiModelProperty("商品所属人头像")
    private String goodsUserHeadImage;
}
