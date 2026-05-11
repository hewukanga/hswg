package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PatrolRecordDTO;
import com.jiumai.base.biz.entity.PatrolRecord;
import com.jiumai.base.biz.mapper.PatrolRecordMapper;
import com.jiumai.base.biz.query.PatrolRecordQuery;
import com.jiumai.base.biz.service.PatrolRecordService;
import com.jiumai.base.biz.vo.PatrolRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 巡查记录表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PatrolRecordServiceImpl extends ServiceImpl<PatrolRecordMapper, PatrolRecord> implements PatrolRecordService {

    @Resource
    private PatrolRecordMapper patrolRecordMapper;

    @Override
    public Page<PatrolRecordVO> findPatrolRecordPage(PatrolRecordQuery query) {
        Page<PatrolRecordVO> page = new Page<>(query.getPage(), query.getSize());
        List<PatrolRecordVO> list = patrolRecordMapper.findPatrolRecordPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePatrolRecord(PatrolRecordDTO patrolRecordDTO) {
        PatrolRecord patrolRecord = new PatrolRecord();

        BeanUtils.copyProperties(patrolRecordDTO, patrolRecord);

        this.saveOrUpdate(patrolRecord);

        return patrolRecord.getId();
    }

    @Override
    public PatrolRecordVO getPatrolRecordById(Long id) {
        PatrolRecord patrolRecord = this.getById(id);
        if (CommonFuntions.isEmptyObject(patrolRecord)) {
            throw new BizException("查询失败，巡查记录表不存在");
        }

        PatrolRecordVO patrolRecordVO = new PatrolRecordVO();
        BeanUtils.copyProperties(patrolRecord, patrolRecordVO);
        return patrolRecordVO;
    }
}
