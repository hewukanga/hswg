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
 * 加班记录表视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("加班记录表视图类")
public class OvertimeProcessVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("类型 1.加班 2.顶班")
    private Integer overtimeType;

    @ApiModelProperty("加班/顶班人id")
    private Long overtimePersonId;

    @ApiModelProperty("加班/顶班人姓名")
    private String overtimePersonName;

    @ApiModelProperty("加班/顶班人手机号")
    private String phone;

    @ApiModelProperty("加班/顶班人部门")
    private String orgName;

    @ApiModelProperty("被顶班人id")
    private Long topShiftPersonId;

    @ApiModelProperty("被顶班人名称")
    private String topShiftPersonName;

    @ApiModelProperty("开始时间")
    private LocalDateTime overtimeStartTime;

    @ApiModelProperty("结束时间")
    private LocalDateTime overtimeEndTime;

    @ApiModelProperty("时长(小时)")
    private Double overtimeDuration;

    @ApiModelProperty("原因")
    private String overtimeReason;

    @ApiModelProperty("状态 1.未提交 2.待接收 3.已拒绝 4.已接收")
    private Integer overtimeStatus;

    @ApiModelProperty("是否存草稿")
    private Boolean draftStatus;

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
