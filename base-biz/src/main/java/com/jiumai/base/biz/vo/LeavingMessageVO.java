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
 * 留言表视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("留言表视图类")
public class LeavingMessageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("商品id")
    private Long goodsId;

    private String content;

    @ApiModelProperty("层级1.一级直接回复商品 2.二级回复某个人")
    private Integer level;

    @ApiModelProperty("发送人id")
    private Long senderId;

    @ApiModelProperty("发送人姓名")
    private String senderName;

    @ApiModelProperty("发送人头像")
    private String senderHeadImage;

    @ApiModelProperty("接收人id")
    private Long receiverId;

    @ApiModelProperty("接收人姓名")
    private String receiverName;

    @ApiModelProperty("接收人头像")
    private String receiverHeadImage;

    @ApiModelProperty("是否管理端回复")
    private Boolean adminReply;

    @ApiModelProperty("是否回复")
    private Boolean answerStatus;

    @ApiModelProperty("父留言id")
    private Long parentId;

    @ApiModelProperty("留言时间")
    private LocalDateTime createDate;

    @ApiModelProperty("是否卖家")
    private Boolean buyersFlag;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
