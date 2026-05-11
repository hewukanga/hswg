package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ProcessAuditDTO;
import com.jiumai.base.biz.entity.ProcessAudit;
import com.jiumai.base.biz.mapper.ProcessAuditMapper;
import com.jiumai.base.biz.query.ProcessAuditQuery;
import com.jiumai.base.biz.service.ProcessAuditService;
import com.jiumai.base.biz.vo.ProcessAuditVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 流程审批表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class ProcessAuditServiceImpl extends ServiceImpl<ProcessAuditMapper, ProcessAudit> implements ProcessAuditService {

    @Resource
    private ProcessAuditMapper processAuditMapper;

    @Override
    public Page<ProcessAuditVO> findProcessAuditPage(ProcessAuditQuery query) {
        Page<ProcessAuditVO> page = new Page<>(query.getPage(), query.getSize());
        List<ProcessAuditVO> list = processAuditMapper.findProcessAuditPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateProcessAudit(ProcessAuditDTO processAuditDTO) {
        ProcessAudit processAudit = new ProcessAudit();

        BeanUtils.copyProperties(processAuditDTO, processAudit);

        this.saveOrUpdate(processAudit);

        return processAudit.getId();
    }

    @Override
    public ProcessAuditVO getProcessAuditById(Long id) {
        ProcessAudit processAudit = this.getById(id);
        if (CommonFuntions.isEmptyObject(processAudit)) {
            throw new BizException("查询失败，流程审批表不存在");
        }

        ProcessAuditVO processAuditVO = new ProcessAuditVO();
        BeanUtils.copyProperties(processAudit, processAuditVO);
        return processAuditVO;
    }
}
