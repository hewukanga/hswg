package com.jiumai.base.common.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiumai.base.common.core.entity.SmOpLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OpLogMapper extends BaseMapper<SmOpLog> {
}
