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
 * 迎检表视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("迎检表视图类")
public class WelcomeCheckVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("负责人id")
    private Long chargePersonId;

    @ApiModelProperty("负责人姓名")
    private String chargePersonName;

    @ApiModelProperty("负责人所属街道id")
    private String streetId;

    @ApiModelProperty("迎检标题")
    private String wcTitle;

    @ApiModelProperty("类型 1.国家级 2.省级 3.市级 4.区级")
    private Integer wcType;

    @ApiModelProperty("迎检时间")
    private LocalDateTime wcDate;

    @ApiModelProperty("迎检内容")
    private String wcContent;

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
