package com.jiumai.base.sm.query;

import com.jiumai.base.common.core.enums.TriggerStatusEnum;
import com.jiumai.base.common.core.enums.TriggerTypeEnum;
import com.jiumai.base.common.core.query.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "定时器执行结果分页查询请求入参", description = "定时器执行结果分页查询请求入参")
public class QuartzRecordQuery extends PageParam {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "定时任务id")
	private Long quartzId;

	@ApiModelProperty(value = "开始时间")
	private LocalDateTime startTime;

	@ApiModelProperty(value = "结束时间")
	private LocalDateTime endTime;

	@ApiModelProperty(value = "运行结果 true成功 false失败")
	private Boolean runResult;
}
