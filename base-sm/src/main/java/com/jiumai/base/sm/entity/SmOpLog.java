package com.jiumai.base.sm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.jiumai.base.common.core.enums.BusinessStatusEnum;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.OperateTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 操作日志记录
 * </p>
 *
 * @author mysql gen
 * @since 2023-01-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmOpLog对象", description="操作日志记录")
public class SmOpLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日志主键")
    @TableId(value = "op_log_id", type = IdType.AUTO)
    private Long opLogId;

    @ApiModelProperty(value = "操作人id")
    private Long opId;

    @ApiModelProperty(value = "操作人员")
    private String opName;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "模块标题")
    private String title;

    @ApiModelProperty(value = "业务类型")
    private BusinessTypeEnum businessType;

    @ApiModelProperty(value = "操作类别")
    private OperateTypeEnum operateType;

    @ApiModelProperty(value = "方法名称")
    private String method;

    @ApiModelProperty(value = "操作状态")
    private BusinessStatusEnum status;

    @ApiModelProperty(value = "请求方式")
    private String requestMethod;

    @ApiModelProperty(value = "请求参数")
    private String params;

    @ApiModelProperty(value = "请求URL")
    private String url;

    @ApiModelProperty(value = "主机地址")
    private String ip;

    @ApiModelProperty(value = "操作地点")
    private String location;

    @ApiModelProperty(value = "返回参数")
    private String result;

    @ApiModelProperty(value = "错误消息")
    private String errorMsg;

    @ApiModelProperty(value = "操作时间")
    private Date opTime;


}
