package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.NewIntegralRecordDTO;
import com.jiumai.base.biz.entity.NewIntegralRecord;
import com.jiumai.base.biz.mapper.NewIntegralRecordMapper;
import com.jiumai.base.biz.query.NewIntegralRecordQuery;
import com.jiumai.base.biz.service.NewIntegralRecordService;
import com.jiumai.base.biz.vo.NewIntegralRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 新积分记录表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class NewIntegralRecordServiceImpl extends ServiceImpl<NewIntegralRecordMapper, NewIntegralRecord> implements NewIntegralRecordService {

    @Resource
    private NewIntegralRecordMapper newIntegralRecordMapper;

    @Override
    public Page<NewIntegralRecordVO> findNewIntegralRecordPage(NewIntegralRecordQuery query) {
        Page<NewIntegralRecordVO> page = new Page<>(query.getPage(), query.getSize());
        List<NewIntegralRecordVO> list = newIntegralRecordMapper.findNewIntegralRecordPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateNewIntegralRecord(NewIntegralRecordDTO newIntegralRecordDTO) {
        NewIntegralRecord newIntegralRecord = new NewIntegralRecord();

        BeanUtils.copyProperties(newIntegralRecordDTO, newIntegralRecord);

        this.saveOrUpdate(newIntegralRecord);

        return newIntegralRecord.getId();
    }

    @Override
    public NewIntegralRecordVO getNewIntegralRecordById(Long id) {
        NewIntegralRecord newIntegralRecord = this.getById(id);
        if (CommonFuntions.isEmptyObject(newIntegralRecord)) {
            throw new BizException("查询失败，新积分记录表不存在");
        }

        NewIntegralRecordVO newIntegralRecordVO = new NewIntegralRecordVO();
        BeanUtils.copyProperties(newIntegralRecord, newIntegralRecordVO);
        return newIntegralRecordVO;
    }
}
