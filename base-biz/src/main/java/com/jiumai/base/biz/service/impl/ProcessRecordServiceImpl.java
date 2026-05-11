package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ProcessRecordDTO;
import com.jiumai.base.biz.entity.ProcessRecord;
import com.jiumai.base.biz.mapper.ProcessRecordMapper;
import com.jiumai.base.biz.query.ProcessRecordQuery;
import com.jiumai.base.biz.service.ProcessRecordService;
import com.jiumai.base.biz.vo.ProcessRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 流程记录表(流程创建时生成全部记录) 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class ProcessRecordServiceImpl extends ServiceImpl<ProcessRecordMapper, ProcessRecord> implements ProcessRecordService {

    @Resource
    private ProcessRecordMapper processRecordMapper;

    @Override
    public Page<ProcessRecordVO> findProcessRecordPage(ProcessRecordQuery query) {
        Page<ProcessRecordVO> page = new Page<>(query.getPage(), query.getSize());
        List<ProcessRecordVO> list = processRecordMapper.findProcessRecordPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateProcessRecord(ProcessRecordDTO processRecordDTO) {
        ProcessRecord processRecord = new ProcessRecord();

        BeanUtils.copyProperties(processRecordDTO, processRecord);

        this.saveOrUpdate(processRecord);

        return processRecord.getId();
    }

    @Override
    public ProcessRecordVO getProcessRecordById(Long id) {
        ProcessRecord processRecord = this.getById(id);
        if (CommonFuntions.isEmptyObject(processRecord)) {
            throw new BizException("查询失败，流程记录表(流程创建时生成全部记录)不存在");
        }

        ProcessRecordVO processRecordVO = new ProcessRecordVO();
        BeanUtils.copyProperties(processRecord, processRecordVO);
        return processRecordVO;
    }
}
