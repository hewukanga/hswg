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
 * 岗位配置信息表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("岗位配置信息表拓展类")
public class PostConfigDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("岗位配置类型(1.签到提醒 2.报名截止)")
    private Integer configType;

    @ApiModelProperty("配置内容")
    private String configContent;

    @ApiModelProperty("配置小时值")
    private Integer configHour;

    @ApiModelProperty("配置分钟值")
    private Integer configMinutes;

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
