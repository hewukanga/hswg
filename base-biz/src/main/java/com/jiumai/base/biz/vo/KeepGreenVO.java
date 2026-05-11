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
 * 保绿视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("保绿视图类")
public class KeepGreenVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("年份")
    private String yearStr;

    @ApiModelProperty("月份")
    private String monthStr;

    @ApiModelProperty("所属片区id")
    private Long streetId;

    @ApiModelProperty("所属片区名称")
    private String streetName;

    @ApiModelProperty("所属片区path")
    private String streetPath;

    @ApiModelProperty("类型code 1.修剪 2,浇水 3.打药 4.除草 5.其他")
    private Integer typeCode;

    @ApiModelProperty("类型名称")
    private String typeName;

    @ApiModelProperty("数量")
    private Integer typeSum;

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
