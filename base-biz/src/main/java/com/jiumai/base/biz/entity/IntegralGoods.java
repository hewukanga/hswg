package com.jiumai.base.biz.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 积分商品
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_integral_goods")
@ApiModel("积分商品")
public class IntegralGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("商品标题")
    @TableField("goods_title")
    private String goodsTitle;

    @ApiModelProperty("商品类型 1.食材 2.电子设备 3.其他")
    @TableField("goods_type")
    private Integer goodsType;

    @ApiModelProperty("商品封面图")
    @TableField("goods_cover_image")
    private String goodsCoverImage;

    @ApiModelProperty("商品介绍")
    @TableField("goods_description")
    private String goodsDescription;

    @ApiModelProperty("商品所需积分")
    @TableField("goods_need_integral")
    private Integer goodsNeedIntegral;

    @ApiModelProperty("商品库存")
    @TableField("goods_stock")
    private Integer goodsStock;

    @ApiModelProperty("兑换截止日期")
    @TableField("goods_end_date")
    private LocalDate goodsEndDate;

    @ApiModelProperty("可见社区区域路径(多个逗号隔开)")
    @TableField("goods_area_path")
    private String goodsAreaPath;

    @ApiModelProperty("商品状态 1.待上架 2.已上架 3.已下架")
    @TableField("goods_top_status")
    private Integer goodsTopStatus;

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

    @ApiModelProperty("商品可兑换总数")
    @TableField("goods_total")
    private Integer goodsTotal;
}
