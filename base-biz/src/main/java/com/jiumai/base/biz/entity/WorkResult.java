package com.jiumai.base.biz.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 工作成果
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-15
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_work_result")
@ApiModel("工作成果")
public class WorkResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("工作名称")
    @TableField("work_name")
    private String workName;

    @ApiModelProperty("工作类型1.日常 2.专项 3.临时")
    @TableField("work_type")
    private Integer workType;

    @ApiModelProperty("执行人id")
    @TableField("executor_id")
    private Long executorId;

    @ApiModelProperty("执行人姓名")
    @TableField("executor_name")
    private String executorName;

    @ApiModelProperty("开始时间")
    @TableField("start_time")
    private LocalDateTime startTime;

    @ApiModelProperty("结束时间")
    @TableField("end_time")
    private LocalDateTime endTime;

    @ApiModelProperty("工作状态1.待开始 2.进行中 3.已完成 4.已延期")
    @TableField("work_status")
    private Integer workStatus;

    @ApiModelProperty("成果描述")
    @TableField("result_desc")
    private String resultDesc;

    @ApiModelProperty("附件图片")
    @TableField("images")
    private String images;

    @ApiModelProperty("创建人id")
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private Long createId;

    @ApiModelProperty("创建人姓名")
    @TableField(value = "create_name", fill = FieldFill.INSERT)
    private String createName;

    @ApiModelProperty("创建日期")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @ApiModelProperty("修改人id")
    @TableField(value = "modify_id", fill = FieldFill.INSERT_UPDATE)
    private Long modifyId;

    @ApiModelProperty("修改人姓名")
    @TableField(value = "modify_name", fill = FieldFill.INSERT_UPDATE)
    private String modifyName;

    @ApiModelProperty("修改日期")
    @TableField(value = "modify_date", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifyDate;

    @ApiModelProperty("是否删除1：未删除 -1：已删除")
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty("乐观锁版本号")
    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    private Integer version;
}