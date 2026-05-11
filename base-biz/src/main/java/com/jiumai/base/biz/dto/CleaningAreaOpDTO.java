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
 * 保洁区域人员拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("保洁区域人员拓展类")
public class CleaningAreaOpDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("保洁区域id")
    private Long cleaningAreaId;

    @ApiModelProperty("人员类型 1.保洁人员 2.负责人")
    private Integer opType;

    @ApiModelProperty("人员id")
    private Long opId;

    @ApiModelProperty("人员姓名")
    private String opName;

    @ApiModelProperty("人员手机号")
    private String opPhone;

    @ApiModelProperty("创建人姓名")
    private String createName;

    @ApiModelProperty("创建日期")
    private LocalDateTime createDate;
}
