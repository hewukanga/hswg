package com.jiumai.base.biz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalTime;
import java.io.Serializable;

/**
 * <p>
 * 考勤规则日期表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("考勤规则日期表拓展类")
public class AttendanceRuleDateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("考勤规则id")
    private Long ruleId;

    @ApiModelProperty("星期")
    private String week;

    @ApiModelProperty("上班时间")
    private LocalTime startTime;

    @ApiModelProperty("下班时间")
    private LocalTime endTime;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
