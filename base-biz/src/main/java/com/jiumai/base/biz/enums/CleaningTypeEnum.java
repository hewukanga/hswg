package com.jiumai.base.biz.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiumai.base.common.core.enums.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel(value = "保洁类型", description = "STREET:街巷保洁，BUILDING:楼栋保洁")
public enum CleaningTypeEnum implements BaseEnum {
    /**
     * 街巷保洁
     */
    STREET(1, "街巷保洁"),
    /**
     * 楼栋保洁
     */
    BUILDING(2, "楼栋保洁");

    @ApiModelProperty(value = "类型编码")
    private Integer code;

    @ApiModelProperty(value = "类型名称")
    private String name;

    @JsonCreator
    public static CleaningTypeEnum jsonInit(String value) {
        for (CleaningTypeEnum item : values()) {
            if (item.toString().equals(value)) {
                return item;
            }
        }
        return null;
    }

    CleaningTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @ApiModelProperty(value = "类型值")
    @Override
    public String getValue() {
        return this.name();
    }
}
