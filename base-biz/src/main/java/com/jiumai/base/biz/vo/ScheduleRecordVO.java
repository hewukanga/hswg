package com.jiumai.base.biz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.Serializable;

/**
 * <p>
 * 排班记录表视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("排班记录表视图类")
public class ScheduleRecordVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("人员id")
    private Long opId;

    @ApiModelProperty("排班日期")
    private LocalDate scheduleDate;

    @ApiModelProperty("上班时间")
    private LocalTime startTime;

    @ApiModelProperty("下班时间")
    private LocalTime endTime;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
