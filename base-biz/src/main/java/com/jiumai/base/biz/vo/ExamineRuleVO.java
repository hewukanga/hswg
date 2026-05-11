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
 * 考核规则视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("考核规则视图类")
public class ExamineRuleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("计算周期 1.近一月 2.近一年 3.近一周")
    private Integer calculationPeriod;

    @ApiModelProperty("考核类型 1.考勤完成率 2.巡查完成率 3.保洁完成率 4.服务工单完成率 5.有效投诉件数 6.物业缴费率 7.帮扶次数")
    private Integer examineType;

    @ApiModelProperty("考核第一名分数")
    private Integer examineOneScore;

    @ApiModelProperty("考核第二名分数")
    private Integer examineTwoScore;

    @ApiModelProperty("考核第三名分数")
    private Integer examineThreeScore;

    @ApiModelProperty("考核第四名分数")
    private Integer examineFourScore;

    @ApiModelProperty("考核第五名分数")
    private Integer examineFiveScore;

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
