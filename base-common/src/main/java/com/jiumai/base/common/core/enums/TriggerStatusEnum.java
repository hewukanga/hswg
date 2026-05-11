package com.jiumai.base.common.core.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel(value = "定时任务状态" ,description="START_UP:启动 STOP:暂停")
public enum TriggerStatusEnum implements BaseEnum {

    START_UP("启动"),

    STOP("暂停");

    @ApiModelProperty(value = "状态名称")
    private String name;

    @JsonCreator
    public static TriggerStatusEnum jsonInit(String value) {
        for (TriggerStatusEnum item : values()) {
            if (item.toString().equals(value)) {
                return item;
            }
        }
        return null;
    }

    private TriggerStatusEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @ApiModelProperty(value = "状态编码")
    @Override
    public String getValue() {
        return this.name();
    }


}
