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
 * 保洁区域管理视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("保洁区域管理视图类")
public class CleaningAreaVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("保洁区域名称")
    private String cleaningAreaName;

    @ApiModelProperty("点位描述")
    private String pointDescription;

    @ApiModelProperty("保洁类型 1.街巷保洁 2.楼栋保洁(字典CLEANING_TYPE)")
    private Integer cleaningType;

    @ApiModelProperty("保洁区域点位")
    private String cleaningAreaPoint;

    @ApiModelProperty("保洁区域中心经度")
    private Double cleaningCenterLng;

    @ApiModelProperty("保洁区域中心纬度")
    private Double cleaningCenterLat;

    @ApiModelProperty("保洁区域中心名称")
    private String cleaningCenterName;

    @ApiModelProperty("保洁人员数量")
    private Integer cleaningPersonSize;

    @ApiModelProperty("所属片区id")
    private Long streetId;

    @ApiModelProperty("所属片区名称")
    private String streetName;

    @ApiModelProperty("所属片区path")
    private String streetPath;

    @ApiModelProperty("所属社区id")
    private Long communityId;

    @ApiModelProperty("所属社区名称")
    private String communityName;

    @ApiModelProperty("所属社区path")
    private String communityPath;

    @ApiModelProperty("保洁班组负责人")
    private String chargeName;

    @ApiModelProperty("保洁班组负责人手机号")
    private String chargePhone;

    @ApiModelProperty("保洁人员名称")
    private String cleanerName;

    @ApiModelProperty("有效期(用于判断展示绿色还是灰色)")
    private LocalDateTime validityDate;

    @ApiModelProperty("上次完成保洁时间")
    private LocalDateTime lastCleaningTime;

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
