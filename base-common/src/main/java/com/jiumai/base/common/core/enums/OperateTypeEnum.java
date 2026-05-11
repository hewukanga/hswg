package com.jiumai.base.common.core.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 操作人类别
 * 
 * @author ruoyi
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel(value = "操作人类别" ,description="PC:后台，APP:手机端，OTHER:其它")
public enum OperateTypeEnum implements  BaseEnum {

    PC("后台"),

    APP("手机端"),

    OTHER("其它");

    @ApiModelProperty(value = "状态名称")
    private String name;

    @JsonCreator
    public static OperateTypeEnum jsonInit(String value) {
        for (OperateTypeEnum item : values()) {
            if (item.toString().equals(value)) {
                return item;
            }
        }
        return null;
    }

    private OperateTypeEnum(String name) {
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
