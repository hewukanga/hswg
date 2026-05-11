package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.MqSource;
import com.jiumai.base.biz.query.MqSourceQuery;
import com.jiumai.base.biz.vo.MqSourceVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 消息队列消息原文 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface MqSourceMapper extends BaseMapper<MqSource> {

    /**
     * 分页查询消息队列消息原文
     * @param page
     * @param query
     * @return
     */
    List<MqSourceVO> findMqSourcePage(Page<MqSourceVO> page, @Param("query") MqSourceQuery query);
}
