package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.OilCardRecordDTO;
import com.jiumai.base.biz.entity.OilCardRecord;
import com.jiumai.base.biz.mapper.OilCardRecordMapper;
import com.jiumai.base.biz.query.OilCardRecordQuery;
import com.jiumai.base.biz.service.OilCardRecordService;
import com.jiumai.base.biz.vo.OilCardRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 油卡上报记录表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class OilCardRecordServiceImpl extends ServiceImpl<OilCardRecordMapper, OilCardRecord> implements OilCardRecordService {

    @Resource
    private OilCardRecordMapper oilCardRecordMapper;

    @Override
    public Page<OilCardRecordVO> findOilCardRecordPage(OilCardRecordQuery query) {
        Page<OilCardRecordVO> page = new Page<>(query.getPage(), query.getSize());
        List<OilCardRecordVO> list = oilCardRecordMapper.findOilCardRecordPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateOilCardRecord(OilCardRecordDTO oilCardRecordDTO) {
        OilCardRecord oilCardRecord = new OilCardRecord();

        BeanUtils.copyProperties(oilCardRecordDTO, oilCardRecord);

        this.saveOrUpdate(oilCardRecord);

        return oilCardRecord.getId();
    }

    @Override
    public OilCardRecordVO getOilCardRecordById(Long id) {
        OilCardRecord oilCardRecord = this.getById(id);
        if (CommonFuntions.isEmptyObject(oilCardRecord)) {
            throw new BizException("查询失败，油卡上报记录表不存在");
        }

        OilCardRecordVO oilCardRecordVO = new OilCardRecordVO();
        BeanUtils.copyProperties(oilCardRecord, oilCardRecordVO);
        return oilCardRecordVO;
    }
}
