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
 * 业主表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_user")
@ApiModel("业主表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("姓名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("微信openId")
    @TableField("open_id")
    private String openId;

    @ApiModelProperty("联系电话")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("性别")
    @TableField("sex")
    private String sex;

    @ApiModelProperty("身份证号")
    @TableField("id_card")
    private String idCard;

    @ApiModelProperty("头像")
    @TableField("head_image")
    private String headImage;

    @ApiModelProperty("年龄")
    @TableField("age")
    private Integer age;

    @ApiModelProperty("街道")
    @TableField("street_name")
    private String streetName;

    @ApiModelProperty("街道id")
    @TableField("street_id")
    private Long streetId;

    @ApiModelProperty("社区名称")
    @TableField("community_name")
    private String communityName;

    @ApiModelProperty("社区id")
    @TableField("community_id")
    private Long communityId;

    @ApiModelProperty("网格名称")
    @TableField("grid_name")
    private String gridName;

    @ApiModelProperty("网格id")
    @TableField("grid_id")
    private Long gridId;

    @ApiModelProperty("小区名称")
    @TableField("residential_quarters_name")
    private String residentialQuartersName;

    @ApiModelProperty("小区id")
    @TableField("residential_quarters_id")
    private Long residentialQuartersId;

    @ApiModelProperty("幢")
    @TableField("building")
    private String building;

    @ApiModelProperty("单元")
    @TableField("unit")
    private String unit;

    @ApiModelProperty("门牌号")
    @TableField("house_number")
    private String houseNumber;

    @ApiModelProperty("是否党员")
    @TableField("party_member_status")
    private Boolean partyMemberStatus;

    @ApiModelProperty("关联房产名称(英文逗号隔开)")
    @TableField("rel_house_property_name")
    private String relHousePropertyName;

    @ApiModelProperty("是否需要一键报修")
    @TableField("one_touch_repair")
    private Boolean oneTouchRepair;

    @ApiModelProperty("所属小区path")
    @TableField("area_path")
    private String areaPath;

    @ApiModelProperty("用户剩余积分")
    @TableField("residual_integral_sum")
    private Integer residualIntegralSum;

    @ApiModelProperty("用户已获取积分")
    @TableField("earned_integral_sum")
    private Integer earnedIntegralSum;

    @ApiModelProperty("报名未签到次数")
    @TableField("sign_up_no_sign_in_sum")
    private Integer signUpNoSignInSum;

    @ApiModelProperty("未完结退出次数")
    @TableField("incomplete_exit_sum")
    private Integer incompleteExitSum;

    @ApiModelProperty("是否商户")
    @TableField("merchant")
    private Boolean merchant;

    @ApiModelProperty("商户名称")
    @TableField("merchant_name")
    private String merchantName;

    @ApiModelProperty("首次绑定状态")
    @TableField("first_binding_status")
    private Boolean firstBindingStatus;

    @ApiModelProperty("创建人id")
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private Long createId;

    @ApiModelProperty("创建人姓名")
    @TableField(value = "create_name", fill = FieldFill.INSERT)
    private String createName;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @ApiModelProperty("修改人id")
    @TableField(value = "modify_id", fill = FieldFill.INSERT_UPDATE)
    private Long modifyId;

    @ApiModelProperty("修改人姓名")
    @TableField(value = "modify_name", fill = FieldFill.INSERT_UPDATE)
    private String modifyName;

    @ApiModelProperty("修改时间")
    @TableField(value = "modify_date", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifyDate;

    @ApiModelProperty("是否删除 1:未删除 -1:已删除")
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty("乐观锁版本号")
    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    private Integer version;

    @ApiModelProperty("用户备注")
    @TableField("remark")
    private String remark;
}
