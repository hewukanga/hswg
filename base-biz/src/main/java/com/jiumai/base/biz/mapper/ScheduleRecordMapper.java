package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.ScheduleRecord;
import com.jiumai.base.biz.query.ScheduleRecordQuery;
import com.jiumai.base.biz.vo.ScheduleRecordVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 排班记录表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ScheduleRecordMapper extends BaseMapper<ScheduleRecord> {

    /**
     * 分页查询排班记录表
     * @param page
     * @param query
     * @return
     */
    List<ScheduleRecordVO> findScheduleRecordPage(Page<ScheduleRecordVO> page, @Param("query") ScheduleRecordQuery query);
}
