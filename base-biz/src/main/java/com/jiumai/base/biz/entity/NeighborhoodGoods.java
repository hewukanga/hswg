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
 * 邻里商品表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_neighborhood_goods")
@ApiModel("邻里商品表")
public class NeighborhoodGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("商品描述")
    @TableField("goods_description")
    private String goodsDescription;

    @ApiModelProperty("商品图片")
    @TableField("images")
    private String images;

    @ApiModelProperty("商品费用")
    @TableField("goods_price")
    private BigDecimal goodsPrice;

    @ApiModelProperty("话题控制")
    @TableField("topic_control")
    private Boolean topicControl;

    @ApiModelProperty("类型1.闲置宝贝 2.宠物话题")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("状态1.待发布 2.待审核 3.已审核 4.已驳回 5.已下架")
    @TableField("goods_status")
    private Integer goodsStatus;

    @ApiModelProperty("设置推荐")
    @TableField("recommend")
    private Boolean recommend;

    @ApiModelProperty("审核时间")
    @TableField("approved_date")
    private LocalDateTime approvedDate;

    @ApiModelProperty("是否审核通过")
    @TableField("audit")
    private Boolean audit;

    @ApiModelProperty("评论数")
    @TableField("evaluation_quantity")
    private Long evaluationQuantity;

    @ApiModelProperty("浏览量")
    @TableField("views")
    private Long views;

    @ApiModelProperty("驳回原因")
    @TableField("reject_reason")
    private String rejectReason;

    @ApiModelProperty("下架原因")
    @TableField("off_shelf")
    private String offShelf;

    @ApiModelProperty("头像")
    @TableField("head_image")
    private String headImage;

    @ApiModelProperty("区域名称")
    @TableField("area_name")
    private String areaName;

    @ApiModelProperty("数据归属区域路径")
    @TableField("area_path")
    private String areaPath;

    @ApiModelProperty("手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("社区区域path")
    @TableField("community_path")
    private String communityPath;

    @ApiModelProperty("创建人id")
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private Long createId;

    @ApiModelProperty("创建人姓名")
    @TableField(value = "create_name", fill = FieldFill.INSERT)
    private String createName;

    @ApiModelProperty("创建日期")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @ApiModelProperty("修改人id")
    @TableField(value = "modify_id", fill = FieldFill.INSERT_UPDATE)
    private Long modifyId;

    @ApiModelProperty("修改人姓名")
    @TableField(value = "modify_name", fill = FieldFill.INSERT_UPDATE)
    private String modifyName;

    @ApiModelProperty("修改日期")
    @TableField(value = "modify_date", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifyDate;

    @ApiModelProperty("是否删除1：未删除 -1：已删除")
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty("乐观锁版本号")
    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    private Integer version;
}
