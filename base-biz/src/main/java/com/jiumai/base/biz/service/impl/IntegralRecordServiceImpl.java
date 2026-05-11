package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.IntegralRecordDTO;
import com.jiumai.base.biz.entity.IntegralRecord;
import com.jiumai.base.biz.mapper.IntegralRecordMapper;
import com.jiumai.base.biz.query.IntegralRecordQuery;
import com.jiumai.base.biz.service.IntegralRecordService;
import com.jiumai.base.biz.vo.IntegralRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 积分记录 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class IntegralRecordServiceImpl extends ServiceImpl<IntegralRecordMapper, IntegralRecord> implements IntegralRecordService {

    @Resource
    private IntegralRecordMapper integralRecordMapper;

    @Override
    public Page<IntegralRecordVO> findIntegralRecordPage(IntegralRecordQuery query) {
        Page<IntegralRecordVO> page = new Page<>(query.getPage(), query.getSize());
        List<IntegralRecordVO> list = integralRecordMapper.findIntegralRecordPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateIntegralRecord(IntegralRecordDTO integralRecordDTO) {
        IntegralRecord integralRecord = new IntegralRecord();

        BeanUtils.copyProperties(integralRecordDTO, integralRecord);

        this.saveOrUpdate(integralRecord);

        return integralRecord.getId();
    }

    @Override
    public IntegralRecordVO getIntegralRecordById(Long id) {
        IntegralRecord integralRecord = this.getById(id);
        if (CommonFuntions.isEmptyObject(integralRecord)) {
            throw new BizException("查询失败，积分记录不存在");
        }

        IntegralRecordVO integralRecordVO = new IntegralRecordVO();
        BeanUtils.copyProperties(integralRecord, integralRecordVO);
        return integralRecordVO;
    }
}
