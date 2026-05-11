package com.jiumai.base.common.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiumai.base.common.core.entity.SmQuartzRunRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 定时任务执行记录 Mapper 接口
 * </p>
 *
 * @author mysql gen
 * @since 2023-02-03
 */
@Mapper
public interface QuartzRunRecordMapper extends BaseMapper<SmQuartzRunRecord> {

}
