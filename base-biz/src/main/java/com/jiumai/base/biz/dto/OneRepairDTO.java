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
 * 一键报修表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("一键报修表拓展类")
public class OneRepairDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("关联类型1.报修单 2.帮扶")
    private Integer relationType;

    @ApiModelProperty("语音文件")
    private String voiceFile;

    @ApiModelProperty("语音文件名称")
    private String voiceFileName;

    @ApiModelProperty("语音长度(00:01)")
    private String voiceSeconds;

    @ApiModelProperty("关联房产名称(英文逗号隔开)")
    private String relHousePropertyName;

    @ApiModelProperty("服务工单id")
    private Long serviceOrderId;

    @ApiModelProperty("创建人姓名")
    private String createName;

    @ApiModelProperty("创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty("创建人手机号")
    private String createPhone;

    @ApiModelProperty("创建人所属社区path")
    private String createPath;

    @ApiModelProperty("修改人姓名")
    private String modifyName;

    @ApiModelProperty("修改日期")
    private LocalDateTime modifyDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
