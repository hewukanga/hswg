package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.NeighborhoodGoodsRecord;
import com.jiumai.base.biz.query.NeighborhoodGoodsRecordQuery;
import com.jiumai.base.biz.vo.NeighborhoodGoodsRecordVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 邻里商品操作记录表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface NeighborhoodGoodsRecordMapper extends BaseMapper<NeighborhoodGoodsRecord> {

    /**
     * 分页查询邻里商品操作记录表
     * @param page
     * @param query
     * @return
     */
    List<NeighborhoodGoodsRecordVO> findNeighborhoodGoodsRecordPage(Page<NeighborhoodGoodsRecordVO> page, @Param("query") NeighborhoodGoodsRecordQuery query);
}
