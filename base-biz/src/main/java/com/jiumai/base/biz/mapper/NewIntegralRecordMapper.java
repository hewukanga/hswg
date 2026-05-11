package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.NewIntegralRecord;
import com.jiumai.base.biz.query.NewIntegralRecordQuery;
import com.jiumai.base.biz.vo.NewIntegralRecordVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 新积分记录表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface NewIntegralRecordMapper extends BaseMapper<NewIntegralRecord> {

    /**
     * 分页查询新积分记录表
     * @param page
     * @param query
     * @return
     */
    List<NewIntegralRecordVO> findNewIntegralRecordPage(Page<NewIntegralRecordVO> page, @Param("query") NewIntegralRecordQuery query);
}
