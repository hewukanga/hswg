package com.jiumai.base.biz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 党员信息视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("党员信息视图类")
public class PartyMemberVO implements Serializable {

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

    @ApiModelProperty("加入党组织日期")
    private LocalDate joinOrganizationDate;

    @ApiModelProperty("转为正式党员日期")
    private LocalDate becomeFormalPartyMemberDate;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("民族")
    private String nation;

    @ApiModelProperty("籍贯")
    private String nativePlace;

    @ApiModelProperty("党龄")
    private Integer partySeniority;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("职位")
    private String position;

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
