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
 * 考勤规则表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("考勤规则表拓展类")
public class AttendanceRuleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("考勤规则标题")
    private String ruleName;

    @ApiModelProperty("考勤规则介绍")
    private String ruleDescription;

    @ApiModelProperty("打卡中心地址名称")
    private String centerAddress;

    @ApiModelProperty("打卡中心地址经度")
    private Double centerLongitude;

    @ApiModelProperty("打卡中心地址纬度")
    private Double centerLatitude;

    @ApiModelProperty("打卡中心范围")
    private Integer centerRange;

    @ApiModelProperty("考勤人员")
    private String attendancePerson;

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

    @ApiModelProperty("是否夜班1-是  2-否")
    private Integer teamFlag;
}
