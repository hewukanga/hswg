package com.jiumai.base.sm.dto;

import com.jiumai.base.sm.entity.SmEnumConf;
import lombok.Data;
import java.util.List;

@Data
public class EnumConfDTO extends SmEnumConf {

	private static final long serialVersionUID = -816196244183437202L;

	private List<SmEnumConf> entitys;
	
	private String parentName;
	private String parentValue;

	
}
