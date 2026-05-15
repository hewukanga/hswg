package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.WorkResultDTO;
import com.jiumai.base.biz.entity.WorkResult;
import com.jiumai.base.biz.mapper.WorkResultMapper;
import com.jiumai.base.biz.query.WorkResultQuery;
import com.jiumai.base.biz.service.WorkResultService;
import com.jiumai.base.biz.vo.WorkResultVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 工作成果 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-15
 */
@Service
public class WorkResultServiceImpl extends ServiceImpl<WorkResultMapper, WorkResult> implements WorkResultService {

    @Resource
    private WorkResultMapper workResultMapper;

    @Override
    public Page<WorkResultVO> findWorkResultPage(WorkResultQuery query) {
        Page<WorkResultVO> page = new Page<>(query.getPage(), query.getSize());
        List<WorkResultVO> list = workResultMapper.findWorkResultPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateWorkResult(WorkResultDTO workResultDTO) {
        WorkResult workResult = new WorkResult();

        BeanUtils.copyProperties(workResultDTO, workResult);

        this.saveOrUpdate(workResult);

        return workResult.getId();
    }

    @Override
    public WorkResultVO getWorkResultById(Long id) {
        WorkResult workResult = this.getById(id);
        if (CommonFuntions.isEmptyObject(workResult)) {
            throw new BizException("查询失败，工作成果不存在");
        }

        WorkResultVO workResultVO = new WorkResultVO();
        BeanUtils.copyProperties(workResult, workResultVO);
        return workResultVO;
    }
}