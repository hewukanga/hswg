package com.jiumai.base.sm.query;

import com.jiumai.base.common.core.query.PageParam;
import com.jiumai.base.sm.enums.NoticeTypeEnum;
import lombok.Data;

@Data
public class NoticeQuery extends PageParam {

	private static final long serialVersionUID = 1L;
	/**
	 * 通知类型
	 */
	private NoticeTypeEnum type;
	/**
	 * 接收者ID
	 */
	private Long receiveId;
	/**
	 * 创建者ID
	 */
	private Long createOpId;


	
	
	
	

}
