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
 * 岗位信息表视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("岗位信息表视图类")
public class PostInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("岗位id")
    private Long id;

    @ApiModelProperty("岗位名称")
    private String postName;

    @ApiModelProperty("岗位类型(1.秩序维护 2.环境保洁 3.绿化养护 4.邻里服务)")
    private Integer postType;

    @ApiModelProperty("开始时间")
    private LocalDateTime startDate;

    @ApiModelProperty("结束时间")
    private LocalDateTime endDate;

    @ApiModelProperty("单次作业积分")
    private Integer singleAssignmentPoints;

    @ApiModelProperty("所需人数")
    private Integer needPersonNum;

    @ApiModelProperty("报名人数")
    private Integer signUpNum;

    private Integer registrationDeadline;

    @ApiModelProperty("报名年龄限制")
    private Integer registrationAgeLimit;

    @ApiModelProperty("签到动态提醒类型")
    private Integer signInRemindType;

    @ApiModelProperty("岗位说明")
    private String jobDescription;

    @ApiModelProperty("岗位状态(1.进行中 2.已结束)")
    private Integer postStatus;

    @ApiModelProperty("岗位图片")
    private String postImages;

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

    @ApiModelProperty("报名截止时间")
    private LocalDateTime registrationDeadlineDate;

    @ApiModelProperty("是否限制年龄")
    private Boolean registrationAgeLimitValue;

    @ApiModelProperty("是否已提醒")
    private Boolean remindValue;

    @ApiModelProperty("打卡范围(米)")
    private Integer centerRange;

    @ApiModelProperty("提醒时间")
    private LocalDateTime remindDate;

    @ApiModelProperty("区域id")
    private String areaIds;
}
