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
 * 业主表视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("业主表视图类")
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("姓名")
    private String userName;

    @ApiModelProperty("微信openId")
    private String openId;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("身份证号")
    private String idCard;

    @ApiModelProperty("头像")
    private String headImage;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("街道")
    private String streetName;

    @ApiModelProperty("街道id")
    private Long streetId;

    @ApiModelProperty("社区名称")
    private String communityName;

    @ApiModelProperty("社区id")
    private Long communityId;

    @ApiModelProperty("网格名称")
    private String gridName;

    @ApiModelProperty("网格id")
    private Long gridId;

    @ApiModelProperty("小区名称")
    private String residentialQuartersName;

    @ApiModelProperty("小区id")
    private Long residentialQuartersId;

    @ApiModelProperty("幢")
    private String building;

    @ApiModelProperty("单元")
    private String unit;

    @ApiModelProperty("门牌号")
    private String houseNumber;

    @ApiModelProperty("是否党员")
    private Boolean partyMemberStatus;

    @ApiModelProperty("关联房产名称(英文逗号隔开)")
    private String relHousePropertyName;

    @ApiModelProperty("是否需要一键报修")
    private Boolean oneTouchRepair;

    @ApiModelProperty("所属小区path")
    private String areaPath;

    @ApiModelProperty("用户剩余积分")
    private Integer residualIntegralSum;

    @ApiModelProperty("用户已获取积分")
    private Integer earnedIntegralSum;

    @ApiModelProperty("报名未签到次数")
    private Integer signUpNoSignInSum;

    @ApiModelProperty("未完结退出次数")
    private Integer incompleteExitSum;

    @ApiModelProperty("是否商户")
    private Boolean merchant;

    @ApiModelProperty("商户名称")
    private String merchantName;

    @ApiModelProperty("首次绑定状态")
    private Boolean firstBindingStatus;

    @ApiModelProperty("创建人姓名")
    private String createName;

    @ApiModelProperty("创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty("修改人姓名")
    private String modifyName;

    @ApiModelProperty("修改时间")
    private LocalDateTime modifyDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;

    @ApiModelProperty("用户备注")
    private String remark;
}
