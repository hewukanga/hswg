package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PatrolRecord;
import com.jiumai.base.biz.query.PatrolRecordQuery;
import com.jiumai.base.biz.vo.PatrolRecordVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 巡查记录表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PatrolRecordMapper extends BaseMapper<PatrolRecord> {

    /**
     * 分页查询巡查记录表
     * @param page
     * @param query
     * @return
     */
    List<PatrolRecordVO> findPatrolRecordPage(Page<PatrolRecordVO> page, @Param("query") PatrolRecordQuery query);
}
