package com.jiumai.base.sm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jiumai.base.sm.entity.SmOpLog;
import com.jiumai.base.sm.query.OpLogQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 操作日志记录 Mapper 接口
 * </p>
 *
 * @author mysql gen
 * @since 2023-01-09
 */
public interface SmOpLogMapper extends BaseMapper<SmOpLog> {

    IPage<SmOpLog> findOpLogPaging(@Param("page") IPage<SmOpLog> page, @Param("query") OpLogQuery query);

}
