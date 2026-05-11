package com.jiumai.base.common.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 定时任务执行记录
 * </p>
 *
 * @author mysql gen
 * @since 2023-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmQuartzRunRecord对象", description="定时任务执行记录")
public class SmQuartzRunRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "定时任务id")
    private Long quartzId;

    @ApiModelProperty(value = "job名称")
    private String jobName;

    @ApiModelProperty(value = "运行结果 true成功 false失败")
    private Boolean runResult;

    @ApiModelProperty(value = "异常信息")
    private String exceptionMessage;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifyDate;

    @ApiModelProperty(value = "删除标志(1：未删除  -1：删除)")
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty(value = "乐观锁版本号")
    @TableField(fill = FieldFill.INSERT)
    @Version
    private Integer version;


}
