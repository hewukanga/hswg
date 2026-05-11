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
 * 岗位地址信息表视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("岗位地址信息表视图类")
public class PostAddressVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("岗位id")
    private Long postId;

    @ApiModelProperty("地址完善")
    private String addressName;

    @ApiModelProperty("地址经度")
    private Double addressLongitude;

    @ApiModelProperty("地址纬度")
    private Double addressLatitude;

    @ApiModelProperty("地址是否被选择(true/false)")
    private Boolean chooseStatus;

    @ApiModelProperty("地址是否被使用(针对居民true/false)")
    private Boolean useStatus;

    @ApiModelProperty("排序")
    private Integer sort;

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

    @ApiModelProperty("地址前缀")
    private String addressNamePrefix;
}
