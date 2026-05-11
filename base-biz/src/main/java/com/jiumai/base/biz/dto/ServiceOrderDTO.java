package com.jiumai.base.biz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 服务工单表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("服务工单表拓展类")
public class ServiceOrderDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("平台交易单号")
    private String tradeNo;

    @ApiModelProperty("工单类型1.垃圾回收 2.家政服务 3.报修")
    private Integer workOrderType;

    @ApiModelProperty("子工单类型 1.家庭回收 2.汽车清运 3.室内维修 4.公共区域保修 5.弱电维修")
    private Integer subWorkOrderType;

    @ApiModelProperty("工单状态1.待派发 2.待签收 3.待处理 4.已完成 5.已关闭  6.待提交 7.待付款")
    private Integer workOrderStatus;

    @ApiModelProperty("服务地址")
    private String serviceAddress;

    @ApiModelProperty("服务项目类型id")
    private Long serviceTypeId;

    @ApiModelProperty("服务项目类型名称")
    private String serviceTypeName;

    @ApiModelProperty("维修科目code")
    private String maintenanceSubjectCode;

    @ApiModelProperty("维修科目code")
    private String maintenanceSubject;

    @ApiModelProperty("服务描述")
    private String serviceDescription;

    @ApiModelProperty("预约开始时间")
    private LocalDateTime appointmentStartDate;

    @ApiModelProperty("预约结束时间")
    private LocalDateTime appointmentEndDate;

    @ApiModelProperty("图片")
    private String images;

    @ApiModelProperty("指定接收人员id")
    private Long assignPersonId;

    @ApiModelProperty("是否评价")
    private Boolean evaluateStatus;

    @ApiModelProperty("第三方报修单id")
    private String thirdRepairId;

    @ApiModelProperty("来源 1.系统 2.云鹭")
    private Integer source;

    @ApiModelProperty("收款类型1.支付宝 2.微信 3.现金 4.免费 5.线上支付")
    private Integer collectionType;

    @ApiModelProperty("收款金额")
    private BigDecimal collectionPrice;

    @ApiModelProperty("总耗时")
    private String totalTimeConsuming;

    @ApiModelProperty("关闭工单原因")
    private String closeReason;

    @ApiModelProperty("房产所属小区id")
    private Long residentialQuartersId;

    @ApiModelProperty("经度")
    private Double longitude;

    @ApiModelProperty("纬度")
    private Double latitude;

    @ApiModelProperty("服务地址简称")
    private String serverAddressAbbreviation;

    @ApiModelProperty("重量")
    private Double weight;

    @ApiModelProperty("预估体积")
    private Double estimatedVolume;

    @ApiModelProperty("是否一键报修 true:是 false:否")
    private Boolean oneRepairStatus;

    @ApiModelProperty("创建人姓名")
    private String createName;

    @ApiModelProperty("创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty("创建人手机号")
    private String createPhone;

    @ApiModelProperty("创建人所属社区path/绑定房屋所属社区path")
    private String createAreaPath;

    @ApiModelProperty("修改人姓名")
    private String modifyName;

    @ApiModelProperty("修改日期")
    private LocalDateTime modifyDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
