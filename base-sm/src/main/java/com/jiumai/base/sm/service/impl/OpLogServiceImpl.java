package com.jiumai.base.sm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiumai.base.common.core.dto.LoginOperator;
import com.jiumai.base.common.core.utils.PageUtils;
import com.jiumai.base.sm.entity.SmOpLog;
import com.jiumai.base.sm.mapper.SmOpLogMapper;
import com.jiumai.base.sm.query.OpLogQuery;
import com.jiumai.base.sm.service.OpLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OpLogServiceImpl extends ServiceImpl<SmOpLogMapper, SmOpLog> implements OpLogService {
    @Resource
    private SmOpLogMapper smOpLogMapper;

    @Override
    public IPage<SmOpLog> findPaging(OpLogQuery query, LoginOperator operator) {
        PageUtils<SmOpLog> pageUtils = new PageUtils<>();
        Page<SmOpLog> page = pageUtils.getPageByPageParam(query);
        smOpLogMapper.findOpLogPaging(page, query);
        return page;
    }

    @Override
    public SmOpLog getOpLogById(Long opLogId) {
        return getById(opLogId);
    }
}
