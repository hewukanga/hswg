package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.MqttConfig;
import com.jiumai.base.biz.query.MqttConfigQuery;
import com.jiumai.base.biz.vo.MqttConfigVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * mqtt参数表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface MqttConfigMapper extends BaseMapper<MqttConfig> {

    /**
     * 分页查询mqtt参数表
     * @param page
     * @param query
     * @return
     */
    List<MqttConfigVO> findMqttConfigPage(Page<MqttConfigVO> page, @Param("query") MqttConfigQuery query);
}
