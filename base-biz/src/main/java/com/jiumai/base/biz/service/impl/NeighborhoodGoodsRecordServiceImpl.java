package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.NeighborhoodGoodsRecordDTO;
import com.jiumai.base.biz.entity.NeighborhoodGoodsRecord;
import com.jiumai.base.biz.mapper.NeighborhoodGoodsRecordMapper;
import com.jiumai.base.biz.query.NeighborhoodGoodsRecordQuery;
import com.jiumai.base.biz.service.NeighborhoodGoodsRecordService;
import com.jiumai.base.biz.vo.NeighborhoodGoodsRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 邻里商品操作记录表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class NeighborhoodGoodsRecordServiceImpl extends ServiceImpl<NeighborhoodGoodsRecordMapper, NeighborhoodGoodsRecord> implements NeighborhoodGoodsRecordService {

    @Resource
    private NeighborhoodGoodsRecordMapper neighborhoodGoodsRecordMapper;

    @Override
    public Page<NeighborhoodGoodsRecordVO> findNeighborhoodGoodsRecordPage(NeighborhoodGoodsRecordQuery query) {
        Page<NeighborhoodGoodsRecordVO> page = new Page<>(query.getPage(), query.getSize());
        List<NeighborhoodGoodsRecordVO> list = neighborhoodGoodsRecordMapper.findNeighborhoodGoodsRecordPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateNeighborhoodGoodsRecord(NeighborhoodGoodsRecordDTO neighborhoodGoodsRecordDTO) {
        NeighborhoodGoodsRecord neighborhoodGoodsRecord = new NeighborhoodGoodsRecord();

        BeanUtils.copyProperties(neighborhoodGoodsRecordDTO, neighborhoodGoodsRecord);

        this.saveOrUpdate(neighborhoodGoodsRecord);

        return neighborhoodGoodsRecord.getId();
    }

    @Override
    public NeighborhoodGoodsRecordVO getNeighborhoodGoodsRecordById(Long id) {
        NeighborhoodGoodsRecord neighborhoodGoodsRecord = this.getById(id);
        if (CommonFuntions.isEmptyObject(neighborhoodGoodsRecord)) {
            throw new BizException("查询失败，邻里商品操作记录表不存在");
        }

        NeighborhoodGoodsRecordVO neighborhoodGoodsRecordVO = new NeighborhoodGoodsRecordVO();
        BeanUtils.copyProperties(neighborhoodGoodsRecord, neighborhoodGoodsRecordVO);
        return neighborhoodGoodsRecordVO;
    }
}
