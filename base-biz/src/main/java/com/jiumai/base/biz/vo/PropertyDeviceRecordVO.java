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
 * 物业设备领用记录视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("物业设备领用记录视图类")
public class PropertyDeviceRecordVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("设备id")
    private Long deviceId;

    @ApiModelProperty("考勤记录ID")
    private Long attendanceRecordId;

    @ApiModelProperty("领用时间")
    private LocalDateTime collectionTime;

    @ApiModelProperty("归还时间")
    private LocalDateTime returnTime;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("附件")
    private String uploadFile;

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
