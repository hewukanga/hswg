package com.jiumai.base.biz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 工作调度入参（仅更新执行人信息）
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("工作调度入参")
public class WorkDispatchDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("工作成果主键id")
    private Long id;

    @ApiModelProperty("执行人id")
    private Long executorId;

    @ApiModelProperty("执行人姓名")
    private String executorName;
}