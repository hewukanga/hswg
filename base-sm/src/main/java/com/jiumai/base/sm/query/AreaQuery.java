package com.jiumai.base.sm.query;

import com.jiumai.base.common.core.query.PageParam;
import lombok.Data;

@Data
public class AreaQuery extends PageParam {

	private static final long serialVersionUID = 1L;

	private Long areaId;
	
	private String areaName;
	
	private String areaCode;
	
	private Long opId;

	

}
