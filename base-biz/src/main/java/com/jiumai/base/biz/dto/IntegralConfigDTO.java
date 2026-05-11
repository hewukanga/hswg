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
 * 积分配置表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("积分配置表拓展类")
public class IntegralConfigDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("类型1.消费获取积分 2.任务获取积分 3.积分抵扣")
    private Integer type;

    @ApiModelProperty("子类型1.总体规则 2.来源规则 3.日常任务 4.基础任务 5.抵扣规则")
    private Integer subType;

    @ApiModelProperty("事项类型1.物业缴费 2.停车缴费 3.每日签到 4.室内报修评价 5.公共报修评价 6.参与社区活动 7.参与志愿者活动 8.提出建议并被采纳  9.物业费抵扣")
    private Integer itemType;

    @ApiModelProperty("计算类型 1.非比例 2.比例")
    private Integer calculation;

    @ApiModelProperty("积分数")
    private Integer integralSum;

    @ApiModelProperty("金额")
    private Integer amount;

    @ApiModelProperty("每日获取次数上限")
    private Integer obtainLimit;

    @ApiModelProperty("开启状态")
    private Boolean enableStatus;

    @ApiModelProperty("修改人姓名")
    private String modifyName;

    @ApiModelProperty("修改日期")
    private LocalDateTime modifyDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
