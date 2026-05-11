package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.CleaningClockInRecordDTO;
import com.jiumai.base.biz.entity.CleaningClockInRecord;
import com.jiumai.base.biz.mapper.CleaningClockInRecordMapper;
import com.jiumai.base.biz.query.CleaningClockInRecordQuery;
import com.jiumai.base.biz.service.CleaningClockInRecordService;
import com.jiumai.base.biz.vo.CleaningClockInRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 保洁打卡记录 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class CleaningClockInRecordServiceImpl extends ServiceImpl<CleaningClockInRecordMapper, CleaningClockInRecord> implements CleaningClockInRecordService {

    @Resource
    private CleaningClockInRecordMapper cleaningClockInRecordMapper;

    @Override
    public Page<CleaningClockInRecordVO> findCleaningClockInRecordPage(CleaningClockInRecordQuery query) {
        Page<CleaningClockInRecordVO> page = new Page<>(query.getPage(), query.getSize());
        List<CleaningClockInRecordVO> list = cleaningClockInRecordMapper.findCleaningClockInRecordPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateCleaningClockInRecord(CleaningClockInRecordDTO cleaningClockInRecordDTO) {
        CleaningClockInRecord cleaningClockInRecord = new CleaningClockInRecord();

        BeanUtils.copyProperties(cleaningClockInRecordDTO, cleaningClockInRecord);

        this.saveOrUpdate(cleaningClockInRecord);

        return cleaningClockInRecord.getId();
    }

    @Override
    public CleaningClockInRecordVO getCleaningClockInRecordById(Long id) {
        CleaningClockInRecord cleaningClockInRecord = this.getById(id);
        if (CommonFuntions.isEmptyObject(cleaningClockInRecord)) {
            throw new BizException("查询失败，保洁打卡记录不存在");
        }

        CleaningClockInRecordVO cleaningClockInRecordVO = new CleaningClockInRecordVO();
        BeanUtils.copyProperties(cleaningClockInRecord, cleaningClockInRecordVO);
        return cleaningClockInRecordVO;
    }
}
