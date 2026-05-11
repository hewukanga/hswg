package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.MqSourceDTO;
import com.jiumai.base.biz.entity.MqSource;
import com.jiumai.base.biz.mapper.MqSourceMapper;
import com.jiumai.base.biz.query.MqSourceQuery;
import com.jiumai.base.biz.service.MqSourceService;
import com.jiumai.base.biz.vo.MqSourceVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 消息队列消息原文 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class MqSourceServiceImpl extends ServiceImpl<MqSourceMapper, MqSource> implements MqSourceService {

    @Resource
    private MqSourceMapper mqSourceMapper;

    @Override
    public Page<MqSourceVO> findMqSourcePage(MqSourceQuery query) {
        Page<MqSourceVO> page = new Page<>(query.getPage(), query.getSize());
        List<MqSourceVO> list = mqSourceMapper.findMqSourcePage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public String saveOrUpdateMqSource(MqSourceDTO mqSourceDTO) {
        MqSource mqSource = new MqSource();

        BeanUtils.copyProperties(mqSourceDTO, mqSource);

        this.saveOrUpdate(mqSource);

        return mqSource.getId();
    }

    @Override
    public MqSourceVO getMqSourceById(String id) {
        MqSource mqSource = this.getById(id);
        if (CommonFuntions.isEmptyObject(mqSource)) {
            throw new BizException("查询失败，消息队列消息原文不存在");
        }

        MqSourceVO mqSourceVO = new MqSourceVO();
        BeanUtils.copyProperties(mqSource, mqSourceVO);
        return mqSourceVO;
    }
}
