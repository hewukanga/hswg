package com.jiumai.base.sm.dto;

import com.jiumai.base.sm.entity.SmArea;
import com.jiumai.base.sm.enums.RelTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(value = "区域扩展类",description="区域信息")
public class AreaDTO extends SmArea {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "下级区域集合")
	private List<AreaDTO> childrens = new ArrayList<AreaDTO>();// 子区域集
	
	private List<AreaDTO> children = new ArrayList<AreaDTO>();// 子区域集
	private String label;
	private Long value;
	
	private String parentName;
	
	private String createName;

	private RelTypeEnum relType; // 用于操作员与区域的关系类型设置

	
}
