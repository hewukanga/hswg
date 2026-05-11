package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ReplacementRecordDTO;
import com.jiumai.base.biz.entity.ReplacementRecord;
import com.jiumai.base.biz.mapper.ReplacementRecordMapper;
import com.jiumai.base.biz.query.ReplacementRecordQuery;
import com.jiumai.base.biz.service.ReplacementRecordService;
import com.jiumai.base.biz.vo.ReplacementRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * pm_replacement_record 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class ReplacementRecordServiceImpl extends ServiceImpl<ReplacementRecordMapper, ReplacementRecord> implements ReplacementRecordService {

    @Resource
    private ReplacementRecordMapper replacementRecordMapper;

    @Override
    public Page<ReplacementRecordVO> findReplacementRecordPage(ReplacementRecordQuery query) {
        Page<ReplacementRecordVO> page = new Page<>(query.getPage(), query.getSize());
        List<ReplacementRecordVO> list = replacementRecordMapper.findReplacementRecordPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateReplacementRecord(ReplacementRecordDTO replacementRecordDTO) {
        ReplacementRecord replacementRecord = new ReplacementRecord();

        BeanUtils.copyProperties(replacementRecordDTO, replacementRecord);

        this.saveOrUpdate(replacementRecord);

        return replacementRecord.getId();
    }

    @Override
    public ReplacementRecordVO getReplacementRecordById(Long id) {
        ReplacementRecord replacementRecord = this.getById(id);
        if (CommonFuntions.isEmptyObject(replacementRecord)) {
            throw new BizException("查询失败，pm_replacement_record不存在");
        }

        ReplacementRecordVO replacementRecordVO = new ReplacementRecordVO();
        BeanUtils.copyProperties(replacementRecord, replacementRecordVO);
        return replacementRecordVO;
    }
}
