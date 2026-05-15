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
 * 工作成果拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("工作成果拓展类")
public class WorkResultDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("工作名称")
    private String workName;

    @ApiModelProperty("工作类型1.日常 2.专项 3.临时")
    private Integer workType;

    @ApiModelProperty("执行人id")
    private Long executorId;

    @ApiModelProperty("执行人姓名")
    private String executorName;

    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty("工作状态1.待开始 2.进行中 3.已完成 4.已延期")
    private Integer workStatus;

    @ApiModelProperty("成果描述")
    private String resultDesc;

    @ApiModelProperty("附件图片")
    private String images;

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