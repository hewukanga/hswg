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
 * 积分记录视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("积分记录视图类")
public class IntegralRecordVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("1.物业缴费 2.停车缴费 3.每日签到 4.室内报修评价 5.公共报修评价 6.参与社区活动 7.参与志愿者活动 8.提出建议并被采纳  9.物业费抵扣 10.商品兑换")
    private Integer integralSource;

    @ApiModelProperty("类型 1.发放 2.扣除")
    private Integer type;

    @ApiModelProperty("姓名")
    private String userName;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("房屋信息")
    private String houseAddress;

    @ApiModelProperty("积分数")
    private Integer integralSum;

    @ApiModelProperty("积分余额")
    private Integer integralBalance;

    @ApiModelProperty("状态 true:成功 false:失败")
    private Boolean status;

    @ApiModelProperty("创建人姓名")
    private String createName;

    @ApiModelProperty("创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
