package com.jiumai.base.sm.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiumai.base.common.core.dto.LoginOperator;
import com.jiumai.base.sm.entity.SmOpLog;
import com.jiumai.base.sm.query.OpLogQuery;

public interface OpLogService extends IService<SmOpLog> {

    IPage<SmOpLog> findPaging(OpLogQuery query, LoginOperator operator);

    SmOpLog getOpLogById(Long opLogId);
}
