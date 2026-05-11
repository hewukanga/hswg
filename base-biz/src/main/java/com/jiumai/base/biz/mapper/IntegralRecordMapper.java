package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.IntegralRecord;
import com.jiumai.base.biz.query.IntegralRecordQuery;
import com.jiumai.base.biz.vo.IntegralRecordVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 积分记录 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface IntegralRecordMapper extends BaseMapper<IntegralRecord> {

    /**
     * 分页查询积分记录
     * @param page
     * @param query
     * @return
     */
    List<IntegralRecordVO> findIntegralRecordPage(Page<IntegralRecordVO> page, @Param("query") IntegralRecordQuery query);
}
