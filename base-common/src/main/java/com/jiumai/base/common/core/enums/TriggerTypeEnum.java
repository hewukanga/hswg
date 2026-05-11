package com.jiumai.base.common.core.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel(value = "定时任务触发器类型" ,description="CRON:cron表达式，SIMPLE:简单任务")
public enum TriggerTypeEnum implements BaseEnum {

    CRON("cron表达式"),

    SIMPLE("简单任务");

    @ApiModelProperty(value = "类型名称")
    private String name;

    @JsonCreator
    public static TriggerTypeEnum jsonInit(String value) {
        for (TriggerTypeEnum item : values()) {
            if (item.toString().equals(value)) {
                return item;
            }
        }
        return null;
    }

    private TriggerTypeEnum(String name) {
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
