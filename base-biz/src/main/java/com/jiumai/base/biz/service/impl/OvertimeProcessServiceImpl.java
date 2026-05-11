package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.OvertimeProcessDTO;
import com.jiumai.base.biz.entity.OvertimeProcess;
import com.jiumai.base.biz.mapper.OvertimeProcessMapper;
import com.jiumai.base.biz.query.OvertimeProcessQuery;
import com.jiumai.base.biz.service.OvertimeProcessService;
import com.jiumai.base.biz.vo.OvertimeProcessVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 加班记录表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class OvertimeProcessServiceImpl extends ServiceImpl<OvertimeProcessMapper, OvertimeProcess> implements OvertimeProcessService {

    @Resource
    private OvertimeProcessMapper overtimeProcessMapper;

    @Override
    public Page<OvertimeProcessVO> findOvertimeProcessPage(OvertimeProcessQuery query) {
        Page<OvertimeProcessVO> page = new Page<>(query.getPage(), query.getSize());
        List<OvertimeProcessVO> list = overtimeProcessMapper.findOvertimeProcessPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateOvertimeProcess(OvertimeProcessDTO overtimeProcessDTO) {
        OvertimeProcess overtimeProcess = new OvertimeProcess();

        BeanUtils.copyProperties(overtimeProcessDTO, overtimeProcess);

        this.saveOrUpdate(overtimeProcess);

        return overtimeProcess.getId();
    }

    @Override
    public OvertimeProcessVO getOvertimeProcessById(Long id) {
        OvertimeProcess overtimeProcess = this.getById(id);
        if (CommonFuntions.isEmptyObject(overtimeProcess)) {
            throw new BizException("查询失败，加班记录表不存在");
        }

        OvertimeProcessVO overtimeProcessVO = new OvertimeProcessVO();
        BeanUtils.copyProperties(overtimeProcess, overtimeProcessVO);
        return overtimeProcessVO;
    }
}
