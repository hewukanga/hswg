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
 * 物业工作站
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_property_workstation")
@ApiModel("物业工作站")
public class PropertyWorkstation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("区域id")
    @TableField("area_id")
    private Long areaId;

    @ApiModelProperty("负责人id")
    @TableField("charge_person_id")
    private Long chargePersonId;

    @ApiModelProperty("负责人")
    @TableField("charge_person")
    private String chargePerson;

    @ApiModelProperty("负责人年龄")
    @TableField("charge_person_age")
    private Integer chargePersonAge;

    @ApiModelProperty("负责人性别 男，女")
    @TableField("charge_person_sex")
    private String chargePersonSex;

    @ApiModelProperty("联系方式")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("头像")
    @TableField("head_image")
    private String headImage;

    @ApiModelProperty("地址")
    @TableField("address")
    private String address;

    @ApiModelProperty("经度")
    @TableField("longitude")
    private Double longitude;

    @ApiModelProperty("纬度")
    @TableField("latitude")
    private Double latitude;

    @ApiModelProperty("创建人id")
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private Long createId;

    @ApiModelProperty("创建人姓名")
    @TableField(value = "create_name", fill = FieldFill.INSERT)
    private String createName;

    @ApiModelProperty("创建日期")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;

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
}
