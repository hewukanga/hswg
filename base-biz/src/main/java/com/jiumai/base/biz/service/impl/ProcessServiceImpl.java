package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ProcessDTO;
import com.jiumai.base.biz.entity.Process;
import com.jiumai.base.biz.mapper.ProcessMapper;
import com.jiumai.base.biz.query.ProcessQuery;
import com.jiumai.base.biz.service.ProcessService;
import com.jiumai.base.biz.vo.ProcessVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 流程表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class ProcessServiceImpl extends ServiceImpl<ProcessMapper, Process> implements ProcessService {

    @Resource
    private ProcessMapper processMapper;

    @Override
    public Page<ProcessVO> findProcessPage(ProcessQuery query) {
        Page<ProcessVO> page = new Page<>(query.getPage(), query.getSize());
        List<ProcessVO> list = processMapper.findProcessPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateProcess(ProcessDTO processDTO) {
        Process process = new Process();

        BeanUtils.copyProperties(processDTO, process);

        this.saveOrUpdate(process);

        return process.getId();
    }

    @Override
    public ProcessVO getProcessById(Long id) {
        Process process = this.getById(id);
        if (CommonFuntions.isEmptyObject(process)) {
            throw new BizException("查询失败，流程表不存在");
        }

        ProcessVO processVO = new ProcessVO();
        BeanUtils.copyProperties(process, processVO);
        return processVO;
    }
}
