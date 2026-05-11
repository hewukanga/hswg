package com.jiumai.base.biz.entity;

import com.baomidou.mybatisplus.annotation.*;
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
 * 服务工单表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_service_order")
@ApiModel("服务工单表")
public class ServiceOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("平台交易单号")
    @TableField("trade_no")
    private String tradeNo;

    @ApiModelProperty("工单类型1.垃圾回收 2.家政服务 3.报修")
    @TableField("work_order_type")
    private Integer workOrderType;

    @ApiModelProperty("子工单类型 1.家庭回收 2.汽车清运 3.室内维修 4.公共区域保修 5.弱电维修")
    @TableField("sub_work_order_type")
    private Integer subWorkOrderType;

    @ApiModelProperty("工单状态1.待派发 2.待签收 3.待处理 4.已完成 5.已关闭  6.待提交 7.待付款")
    @TableField("work_order_status")
    private Integer workOrderStatus;

    @ApiModelProperty("服务地址")
    @TableField("service_address")
    private String serviceAddress;

    @ApiModelProperty("服务项目类型id")
    @TableField("service_type_id")
    private Long serviceTypeId;

    @ApiModelProperty("服务项目类型名称")
    @TableField("service_type_name")
    private String serviceTypeName;

    @ApiModelProperty("维修科目code")
    @TableField("maintenance_subject_code")
    private String maintenanceSubjectCode;

    @ApiModelProperty("维修科目code")
    @TableField("maintenance_subject")
    private String maintenanceSubject;

    @ApiModelProperty("服务描述")
    @TableField("service_description")
    private String serviceDescription;

    @ApiModelProperty("预约开始时间")
    @TableField("appointment_start_date")
    private LocalDateTime appointmentStartDate;

    @ApiModelProperty("预约结束时间")
    @TableField("appointment_end_date")
    private LocalDateTime appointmentEndDate;

    @ApiModelProperty("图片")
    @TableField("images")
    private String images;

    @ApiModelProperty("指定接收人员id")
    @TableField("assign_person_id")
    private Long assignPersonId;

    @ApiModelProperty("是否评价")
    @TableField("evaluate_status")
    private Boolean evaluateStatus;

    @ApiModelProperty("第三方报修单id")
    @TableField("third_repair_id")
    private String thirdRepairId;

    @ApiModelProperty("来源 1.系统 2.云鹭")
    @TableField("source")
    private Integer source;

    @ApiModelProperty("收款类型1.支付宝 2.微信 3.现金 4.免费 5.线上支付")
    @TableField("collection_type")
    private Integer collectionType;

    @ApiModelProperty("收款金额")
    @TableField("collection_price")
    private BigDecimal collectionPrice;

    @ApiModelProperty("总耗时")
    @TableField("total_time_consuming")
    private String totalTimeConsuming;

    @ApiModelProperty("关闭工单原因")
    @TableField("close_reason")
    private String closeReason;

    @ApiModelProperty("房产所属小区id")
    @TableField("residential_quarters_id")
    private Long residentialQuartersId;

    @ApiModelProperty("经度")
    @TableField("longitude")
    private Double longitude;

    @ApiModelProperty("纬度")
    @TableField("latitude")
    private Double latitude;

    @ApiModelProperty("服务地址简称")
    @TableField("server_address_abbreviation")
    private String serverAddressAbbreviation;

    @ApiModelProperty("重量")
    @TableField("weight")
    private Double weight;

    @ApiModelProperty("预估体积")
    @TableField("estimated_volume")
    private Double estimatedVolume;

    @ApiModelProperty("是否一键报修 true:是 false:否")
    @TableField("one_repair_status")
    private Boolean oneRepairStatus;

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

    @ApiModelProperty("创建人所属社区path/绑定房屋所属社区path")
    @TableField("create_area_path")
    private String createAreaPath;

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
