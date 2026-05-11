package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.MqttConfigDTO;
import com.jiumai.base.biz.entity.MqttConfig;
import com.jiumai.base.biz.mapper.MqttConfigMapper;
import com.jiumai.base.biz.query.MqttConfigQuery;
import com.jiumai.base.biz.service.MqttConfigService;
import com.jiumai.base.biz.vo.MqttConfigVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * mqtt参数表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class MqttConfigServiceImpl extends ServiceImpl<MqttConfigMapper, MqttConfig> implements MqttConfigService {

    @Resource
    private MqttConfigMapper mqttConfigMapper;

    @Override
    public Page<MqttConfigVO> findMqttConfigPage(MqttConfigQuery query) {
        Page<MqttConfigVO> page = new Page<>(query.getPage(), query.getSize());
        List<MqttConfigVO> list = mqttConfigMapper.findMqttConfigPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Integer saveOrUpdateMqttConfig(MqttConfigDTO mqttConfigDTO) {
        MqttConfig mqttConfig = new MqttConfig();

        BeanUtils.copyProperties(mqttConfigDTO, mqttConfig);

        this.saveOrUpdate(mqttConfig);

        return mqttConfig.getId();
    }

    @Override
    public MqttConfigVO getMqttConfigById(Integer id) {
        MqttConfig mqttConfig = this.getById(id);
        if (CommonFuntions.isEmptyObject(mqttConfig)) {
            throw new BizException("查询失败，mqtt参数表不存在");
        }

        MqttConfigVO mqttConfigVO = new MqttConfigVO();
        BeanUtils.copyProperties(mqttConfig, mqttConfigVO);
        return mqttConfigVO;
    }
}
