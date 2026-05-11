package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ProcessAuditConfigDTO;
import com.jiumai.base.biz.entity.ProcessAuditConfig;
import com.jiumai.base.biz.mapper.ProcessAuditConfigMapper;
import com.jiumai.base.biz.query.ProcessAuditConfigQuery;
import com.jiumai.base.biz.service.ProcessAuditConfigService;
import com.jiumai.base.biz.vo.ProcessAuditConfigVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 流程审批配置表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class ProcessAuditConfigServiceImpl extends ServiceImpl<ProcessAuditConfigMapper, ProcessAuditConfig> implements ProcessAuditConfigService {

    @Resource
    private ProcessAuditConfigMapper processAuditConfigMapper;

    @Override
    public Page<ProcessAuditConfigVO> findProcessAuditConfigPage(ProcessAuditConfigQuery query) {
        Page<ProcessAuditConfigVO> page = new Page<>(query.getPage(), query.getSize());
        List<ProcessAuditConfigVO> list = processAuditConfigMapper.findProcessAuditConfigPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateProcessAuditConfig(ProcessAuditConfigDTO processAuditConfigDTO) {
        ProcessAuditConfig processAuditConfig = new ProcessAuditConfig();

        BeanUtils.copyProperties(processAuditConfigDTO, processAuditConfig);

        this.saveOrUpdate(processAuditConfig);

        return processAuditConfig.getId();
    }

    @Override
    public ProcessAuditConfigVO getProcessAuditConfigById(Long id) {
        ProcessAuditConfig processAuditConfig = this.getById(id);
        if (CommonFuntions.isEmptyObject(processAuditConfig)) {
            throw new BizException("查询失败，流程审批配置表不存在");
        }

        ProcessAuditConfigVO processAuditConfigVO = new ProcessAuditConfigVO();
        BeanUtils.copyProperties(processAuditConfig, processAuditConfigVO);
        return processAuditConfigVO;
    }
}
