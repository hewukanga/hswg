package com.jiumai.base.sm.query;

import com.jiumai.base.common.core.enums.TriggerStatusEnum;
import com.jiumai.base.common.core.enums.TriggerTypeEnum;
import com.jiumai.base.common.core.query.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "定时器分页查询请求入参", description = "定时器分页查询请求入参")
public class QuartzQuery extends PageParam {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "触发器名称")
	private String triggerName;

	@ApiModelProperty(value = "状态 START_UP:启动 STOP:暂停")
	private TriggerStatusEnum status;

	@ApiModelProperty(value = "触发器类型 CRON：cron表达式 SIMPLE：简单")
	private TriggerTypeEnum triggerType;
}
