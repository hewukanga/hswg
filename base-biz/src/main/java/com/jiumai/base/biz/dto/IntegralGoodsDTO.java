package com.jiumai.base.biz.dto;

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
 * 积分商品拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("积分商品拓展类")
public class IntegralGoodsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("商品标题")
    private String goodsTitle;

    @ApiModelProperty("商品类型 1.食材 2.电子设备 3.其他")
    private Integer goodsType;

    @ApiModelProperty("商品封面图")
    private String goodsCoverImage;

    @ApiModelProperty("商品介绍")
    private String goodsDescription;

    @ApiModelProperty("商品所需积分")
    private Integer goodsNeedIntegral;

    @ApiModelProperty("商品库存")
    private Integer goodsStock;

    @ApiModelProperty("兑换截止日期")
    private LocalDate goodsEndDate;

    @ApiModelProperty("可见社区区域路径(多个逗号隔开)")
    private String goodsAreaPath;

    @ApiModelProperty("商品状态 1.待上架 2.已上架 3.已下架")
    private Integer goodsTopStatus;

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

    @ApiModelProperty("商品可兑换总数")
    private Integer goodsTotal;
}
