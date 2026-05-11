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
 * 能工巧匠信息拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("能工巧匠信息拓展类")
public class SkilledCraftsmenDTO implements Serializable {

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

    @ApiModelProperty("姓名")
    private String userName;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("住址")
    private String address;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("是否为党员")
    private Boolean partyMemberStatus;

    @ApiModelProperty("人才类型(字典TALENT_TYPE)1.技能人才 2.储备人才 3.知识人才 4.科技人才")
    private Integer talentType;

    @ApiModelProperty("事迹说明")
    private String deedDescription;

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
