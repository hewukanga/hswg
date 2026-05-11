package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.MqttConfigDTO;
import com.jiumai.base.biz.entity.MqttConfig;
import com.jiumai.base.biz.query.MqttConfigQuery;
import com.jiumai.base.biz.vo.MqttConfigVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * mqtt参数表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface MqttConfigService extends IService<MqttConfig> {

    /**
     * 分页查询mqtt参数表
     * @param query
     * @return
     */
    Page<MqttConfigVO> findMqttConfigPage(MqttConfigQuery query);

    /**
     * 添加或更新mqtt参数表
     * @param mqttConfigDTO
     * @return
     */
    Integer saveOrUpdateMqttConfig(MqttConfigDTO mqttConfigDTO);

    /**
     * 通过id查询成功mqtt参数表详情
     * @param id
     * @return
     */
    MqttConfigVO getMqttConfigById(Integer id);
}
