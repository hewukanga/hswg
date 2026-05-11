package com.jiumai.base.biz.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 停车场(数泊推送)
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_parking_lot")
@ApiModel("停车场(数泊推送)")
public class ParkingLot implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("停车场code(数泊)")
    @TableField("parking_lot_code")
    private String parkingLotCode;

    @ApiModelProperty("停车场名称")
    @TableField("parking_lot_name")
    private String parkingLotName;

    @ApiModelProperty("停车场地址")
    @TableField("parking_lot_address")
    private String parkingLotAddress;

    @ApiModelProperty("停车场经度")
    @TableField("parking_lot_longitude")
    private Double parkingLotLongitude;

    @ApiModelProperty("停车场纬度")
    @TableField("parking_lot_latitude")
    private Double parkingLotLatitude;

    @ApiModelProperty("停车场类型名称")
    @TableField("parking_lot_type_name")
    private String parkingLotTypeName;

    @ApiModelProperty("停车场所属小区")
    @TableField("parking_lot_area")
    private String parkingLotArea;

    @ApiModelProperty("停车位总数 ")
    @TableField("parking_space_total")
    private Integer parkingSpaceTotal;

    @ApiModelProperty("停车场剩余车位")
    @TableField("parking_space_free")
    private Integer parkingSpaceFree;

    @ApiModelProperty("服务电话")
    @TableField("service_telephone")
    private String serviceTelephone;

    @ApiModelProperty("停车场状态")
    @TableField("parking_lot_status")
    private Boolean parkingLotStatus;

    @ApiModelProperty("收费性质(1：收费 2：免费)")
    @TableField("charge_nature")
    private Integer chargeNature;

    @ApiModelProperty("创建人id")
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private Long createId;

    @ApiModelProperty("创建人姓名")
    @TableField(value = "create_name", fill = FieldFill.INSERT)
    private String createName;

    @ApiModelProperty("创建日期")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @ApiModelProperty("创建人手机号")
    @TableField("create_phone")
    private String createPhone;

    @ApiModelProperty("修改人id")
    @TableField(value = "modify_id", fill = FieldFill.INSERT_UPDATE)
    private Long modifyId;

    @ApiModelProperty("修改人姓名")
    @TableField(value = "modify_name", fill = FieldFill.INSERT_UPDATE)
    private String modifyName;

    @ApiModelProperty("修改日期")
    @TableField(value = "modify_date", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifyDate;

    @ApiModelProperty("是否删除1：未删除 -1：已删除")
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty("乐观锁版本号")
    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    private Integer version;

    @ApiModelProperty("所属片区id")
    @TableField("street_id")
    private Long streetId;

    @ApiModelProperty("所属片区名称")
    @TableField("street_name")
    private String streetName;

    @ApiModelProperty("所属社区id")
    @TableField("community_id")
    private Long communityId;

    @ApiModelProperty("所属社区名称")
    @TableField("community_name")
    private String communityName;

    @ApiModelProperty("所属社区path")
    @TableField("community_path")
    private String communityPath;
}
