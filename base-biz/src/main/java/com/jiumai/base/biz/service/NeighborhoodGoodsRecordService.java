package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.NeighborhoodGoodsRecordDTO;
import com.jiumai.base.biz.entity.NeighborhoodGoodsRecord;
import com.jiumai.base.biz.query.NeighborhoodGoodsRecordQuery;
import com.jiumai.base.biz.vo.NeighborhoodGoodsRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 邻里商品操作记录表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface NeighborhoodGoodsRecordService extends IService<NeighborhoodGoodsRecord> {

    /**
     * 分页查询邻里商品操作记录表
     * @param query
     * @return
     */
    Page<NeighborhoodGoodsRecordVO> findNeighborhoodGoodsRecordPage(NeighborhoodGoodsRecordQuery query);

    /**
     * 添加或更新邻里商品操作记录表
     * @param neighborhoodGoodsRecordDTO
     * @return
     */
    Long saveOrUpdateNeighborhoodGoodsRecord(NeighborhoodGoodsRecordDTO neighborhoodGoodsRecordDTO);

    /**
     * 通过id查询成功邻里商品操作记录表详情
     * @param id
     * @return
     */
    NeighborhoodGoodsRecordVO getNeighborhoodGoodsRecordById(Long id);
}
