package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.MqSourceDTO;
import com.jiumai.base.biz.entity.MqSource;
import com.jiumai.base.biz.query.MqSourceQuery;
import com.jiumai.base.biz.vo.MqSourceVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 消息队列消息原文 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface MqSourceService extends IService<MqSource> {

    /**
     * 分页查询消息队列消息原文
     * @param query
     * @return
     */
    Page<MqSourceVO> findMqSourcePage(MqSourceQuery query);

    /**
     * 添加或更新消息队列消息原文
     * @param mqSourceDTO
     * @return
     */
    String saveOrUpdateMqSource(MqSourceDTO mqSourceDTO);

    /**
     * 通过id查询成功消息队列消息原文详情
     * @param id
     * @return
     */
    MqSourceVO getMqSourceById(String id);
}
