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
 * 邻里商品表视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("邻里商品表视图类")
public class NeighborhoodGoodsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("商品描述")
    private String goodsDescription;

    @ApiModelProperty("商品图片")
    private String images;

    @ApiModelProperty("商品费用")
    private BigDecimal goodsPrice;

    @ApiModelProperty("话题控制")
    private Boolean topicControl;

    @ApiModelProperty("类型1.闲置宝贝 2.宠物话题")
    private Integer type;

    @ApiModelProperty("状态1.待发布 2.待审核 3.已审核 4.已驳回 5.已下架")
    private Integer goodsStatus;

    @ApiModelProperty("设置推荐")
    private Boolean recommend;

    @ApiModelProperty("审核时间")
    private LocalDateTime approvedDate;

    @ApiModelProperty("是否审核通过")
    private Boolean audit;

    @ApiModelProperty("评论数")
    private Long evaluationQuantity;

    @ApiModelProperty("浏览量")
    private Long views;

    @ApiModelProperty("驳回原因")
    private String rejectReason;

    @ApiModelProperty("下架原因")
    private String offShelf;

    @ApiModelProperty("头像")
    private String headImage;

    @ApiModelProperty("区域名称")
    private String areaName;

    @ApiModelProperty("数据归属区域路径")
    private String areaPath;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("社区区域path")
    private String communityPath;

    @ApiModelProperty("创建人姓名")
    private String createName;

    @ApiModelProperty("创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty("修改人姓名")
    private String modifyName;

    @ApiModelProperty("修改日期")
    private LocalDateTime modifyDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
