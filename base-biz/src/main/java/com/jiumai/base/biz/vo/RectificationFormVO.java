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
 * 整改单视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("整改单视图类")
public class RectificationFormVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("整改单名称")
    private String rectificationFormName;

    @ApiModelProperty("检查日期")
    private LocalDateTime checkDate;

    @ApiModelProperty("截止日期")
    private LocalDateTime deadline;

    @ApiModelProperty("检查地址id")
    private Long areaId;

    @ApiModelProperty("检查地址名称")
    private String areaName;

    @ApiModelProperty("总扣分")
    private Double totalDeductions;

    @ApiModelProperty("状态 0 -> 已处理,1 -> 待处理,2 -> 处理中")
    private Integer status;

    @ApiModelProperty("整改问题")
    private String rectificationIssues;

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
