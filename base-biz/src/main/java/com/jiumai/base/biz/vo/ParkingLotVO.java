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
 * 停车场(数泊推送)视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("停车场(数泊推送)视图类")
public class ParkingLotVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("停车场code(数泊)")
    private String parkingLotCode;

    @ApiModelProperty("停车场名称")
    private String parkingLotName;

    @ApiModelProperty("停车场地址")
    private String parkingLotAddress;

    @ApiModelProperty("停车场经度")
    private Double parkingLotLongitude;

    @ApiModelProperty("停车场纬度")
    private Double parkingLotLatitude;

    @ApiModelProperty("停车场类型名称")
    private String parkingLotTypeName;

    @ApiModelProperty("停车场所属小区")
    private String parkingLotArea;

    @ApiModelProperty("停车位总数 ")
    private Integer parkingSpaceTotal;

    @ApiModelProperty("停车场剩余车位")
    private Integer parkingSpaceFree;

    @ApiModelProperty("服务电话")
    private String serviceTelephone;

    @ApiModelProperty("停车场状态")
    private Boolean parkingLotStatus;

    @ApiModelProperty("收费性质(1：收费 2：免费)")
    private Integer chargeNature;

    @ApiModelProperty("创建人姓名")
    private String createName;

    @ApiModelProperty("创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty("创建人手机号")
    private String createPhone;

    @ApiModelProperty("修改人姓名")
    private String modifyName;

    @ApiModelProperty("修改日期")
    private LocalDateTime modifyDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;

    @ApiModelProperty("所属片区id")
    private Long streetId;

    @ApiModelProperty("所属片区名称")
    private String streetName;

    @ApiModelProperty("所属社区id")
    private Long communityId;

    @ApiModelProperty("所属社区名称")
    private String communityName;

    @ApiModelProperty("所属社区path")
    private String communityPath;
}
