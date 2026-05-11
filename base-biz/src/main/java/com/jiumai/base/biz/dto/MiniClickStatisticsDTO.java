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
 * 小程序点击统计表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("小程序点击统计表拓展类")
public class MiniClickStatisticsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("片区id")
    private Long streetId;

    @ApiModelProperty("片区名称")
    private String streetName;

    @ApiModelProperty("统计日期")
    private LocalDate statisticsDate;

    @ApiModelProperty("今日访问量")
    private Integer todayVisits;

    @ApiModelProperty("今日点击量")
    private Integer todayHits;

    @ApiModelProperty("修改日期")
    private LocalDateTime modifyDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
