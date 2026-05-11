package com.jiumai.base.common.core.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel(value = "业务状态" ,description="SUCCESS:成功，FAIL:失败")
public enum BusinessStatusEnum implements BaseEnum {

    SUCCESS("成功"),

    FAIL("失败");

    @ApiModelProperty(value = "状态名称")
    private String name;

    @JsonCreator
    public static BusinessStatusEnum jsonInit(String value) {
        for (BusinessStatusEnum item : values()) {
            if (item.toString().equals(value)) {
                return item;
            }
        }
        return null;
    }

    private BusinessStatusEnum(String name) {
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
