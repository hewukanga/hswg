package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.OvertimeProcessFlowDTO;
import com.jiumai.base.biz.entity.OvertimeProcessFlow;
import com.jiumai.base.biz.mapper.OvertimeProcessFlowMapper;
import com.jiumai.base.biz.query.OvertimeProcessFlowQuery;
import com.jiumai.base.biz.service.OvertimeProcessFlowService;
import com.jiumai.base.biz.vo.OvertimeProcessFlowVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 加班流程表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class OvertimeProcessFlowServiceImpl extends ServiceImpl<OvertimeProcessFlowMapper, OvertimeProcessFlow> implements OvertimeProcessFlowService {

    @Resource
    private OvertimeProcessFlowMapper overtimeProcessFlowMapper;

    @Override
    public Page<OvertimeProcessFlowVO> findOvertimeProcessFlowPage(OvertimeProcessFlowQuery query) {
        Page<OvertimeProcessFlowVO> page = new Page<>(query.getPage(), query.getSize());
        List<OvertimeProcessFlowVO> list = overtimeProcessFlowMapper.findOvertimeProcessFlowPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateOvertimeProcessFlow(OvertimeProcessFlowDTO overtimeProcessFlowDTO) {
        OvertimeProcessFlow overtimeProcessFlow = new OvertimeProcessFlow();

        BeanUtils.copyProperties(overtimeProcessFlowDTO, overtimeProcessFlow);

        this.saveOrUpdate(overtimeProcessFlow);

        return overtimeProcessFlow.getId();
    }

    @Override
    public OvertimeProcessFlowVO getOvertimeProcessFlowById(Long id) {
        OvertimeProcessFlow overtimeProcessFlow = this.getById(id);
        if (CommonFuntions.isEmptyObject(overtimeProcessFlow)) {
            throw new BizException("查询失败，加班流程表不存在");
        }

        OvertimeProcessFlowVO overtimeProcessFlowVO = new OvertimeProcessFlowVO();
        BeanUtils.copyProperties(overtimeProcessFlow, overtimeProcessFlowVO);
        return overtimeProcessFlowVO;
    }
}
