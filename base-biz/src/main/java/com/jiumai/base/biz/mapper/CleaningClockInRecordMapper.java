package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.CleaningClockInRecord;
import com.jiumai.base.biz.query.CleaningClockInRecordQuery;
import com.jiumai.base.biz.vo.CleaningClockInRecordVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 保洁打卡记录 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface CleaningClockInRecordMapper extends BaseMapper<CleaningClockInRecord> {

    /**
     * 分页查询保洁打卡记录
     * @param page
     * @param query
     * @return
     */
    List<CleaningClockInRecordVO> findCleaningClockInRecordPage(Page<CleaningClockInRecordVO> page, @Param("query") CleaningClockInRecordQuery query);
}
