package com.jiumai.base.biz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 网格人员信息拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("网格人员信息拓展类")
public class GridPersonnelDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

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

    @ApiModelProperty("所属网格id")
    private Long gridId;

    @ApiModelProperty("所属网格名称")
    private String gridName;

    @ApiModelProperty("所属网格path")
    private String gridPath;

    @ApiModelProperty("姓名")
    private String userName;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("职位(字典POSITION_TYPE) 1.网格长 2.网格员")
    private Integer positionType;

    @ApiModelProperty("管辖范围")
    private String jurisdiction;

    @ApiModelProperty("是否为党员")
    private Boolean partyMemberStatus;

    @ApiModelProperty("个人照片")
    private String images;

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
