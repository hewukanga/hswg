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
 * 小程序点击统计详情表视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("小程序点击统计详情表视图类")
public class MiniClickStatisticsDetailsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("关联小程序点击统计id")
    private Long refId;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("模块名称")
    private String moduleName;

    @ApiModelProperty("模块操作类型 1.点击")
    private Integer moduleOpType;

    @ApiModelProperty("创建人姓名")
    private String createName;

    @ApiModelProperty("创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
