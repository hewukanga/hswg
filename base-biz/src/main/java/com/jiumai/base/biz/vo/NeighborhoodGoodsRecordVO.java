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
 * 邻里商品操作记录表视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("邻里商品操作记录表视图类")
public class NeighborhoodGoodsRecordVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("商品id")
    private Long goodsId;

    @ApiModelProperty("记录类型1.用户提交 2.用户撤销 3.用户编辑 4.管理员审核 5.管理员驳回 6.管理员下架 7.管理员设置推荐 8.管理员取消推荐")
    private Integer recordType;

    @ApiModelProperty("驳回原因/下架原因")
    private String reason;

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
