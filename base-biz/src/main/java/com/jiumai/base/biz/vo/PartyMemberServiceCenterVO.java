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
 * 党员服务中心视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("党员服务中心视图类")
public class PartyMemberServiceCenterVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("街道id")
    private Long streetId;

    @ApiModelProperty("区域id")
    private Long areaId;

    @ApiModelProperty("服务中心地址")
    private String serviceCenterAddress;

    @ApiModelProperty("服务中心经度")
    private Double serviceCenterLongitude;

    @ApiModelProperty("服务中心纬度")
    private Double serviceCenterLatitude;

    @ApiModelProperty("党员数量")
    private Integer partyMemberNum;

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
