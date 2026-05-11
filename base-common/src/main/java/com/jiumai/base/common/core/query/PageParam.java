package com.jiumai.base.common.core.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

/**
 * 子类继承此类获得翻页功能
 * @author lijiazhi
 */
@Data
@ApiModel(value = "分页查询参数")
public class PageParam implements Serializable {

	private static final long serialVersionUID = -6570508179662029398L;

	@ApiModelProperty(value = "当前页,默认是第1页",example = "1")
    private Integer page = 1;
	
	@ApiModelProperty(value = "每页几条数据,默认10条",example = "10")
    private Integer limit = 10;
}
