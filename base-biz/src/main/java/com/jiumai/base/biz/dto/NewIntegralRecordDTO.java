package com.jiumai.base.biz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 新积分记录表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("新积分记录表拓展类")
public class NewIntegralRecordDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("姓名")
    private String userName;

    @ApiModelProperty("记录类型(1.居民 2.商户 3.拱墅市政)")
    private Integer integralRecordType;

    @ApiModelProperty("积分类型(1.消耗 2.获取)")
    private Integer integralType;

    @ApiModelProperty("细分类型(岗位获取、绑定户号获取、物业缴费获取、停车缴费获取、户内转增、物业缴费抵扣、停车缴费抵扣、二维码交易、验证码交易、现金兑换、物业缴费兑换、停车缴费兑换、岗位奖励发放、停车缴费发放、物业缴费发放、绑定户号发放、后台积分抵扣、后台积分兑换、积分退回)")
    private Integer subdivisionType;

    @ApiModelProperty("积分来源")
    private String integralSource;

    @ApiModelProperty("消耗对象")
    private String consumptionObject;

    @ApiModelProperty("积分数量")
    private Integer integralSum;

    @ApiModelProperty("积分余额")
    private Integer integralBalance;

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

    @ApiModelProperty("商户id")
    private Long operatorId;

    @ApiModelProperty("来源id")
    private Long sourceId;
}
