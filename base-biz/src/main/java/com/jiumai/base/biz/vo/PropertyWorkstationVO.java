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
 * 物业工作站视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("物业工作站视图类")
public class PropertyWorkstationVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("区域id")
    private Long areaId;

    @ApiModelProperty("负责人id")
    private Long chargePersonId;

    @ApiModelProperty("负责人")
    private String chargePerson;

    @ApiModelProperty("负责人年龄")
    private Integer chargePersonAge;

    @ApiModelProperty("负责人性别 男，女")
    private String chargePersonSex;

    @ApiModelProperty("联系方式")
    private String phone;

    @ApiModelProperty("头像")
    private String headImage;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("经度")
    private Double longitude;

    @ApiModelProperty("纬度")
    private Double latitude;

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
