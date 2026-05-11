package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.MiniClickStatisticsDTO;
import com.jiumai.base.biz.entity.MiniClickStatistics;
import com.jiumai.base.biz.mapper.MiniClickStatisticsMapper;
import com.jiumai.base.biz.query.MiniClickStatisticsQuery;
import com.jiumai.base.biz.service.MiniClickStatisticsService;
import com.jiumai.base.biz.vo.MiniClickStatisticsVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 小程序点击统计表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class MiniClickStatisticsServiceImpl extends ServiceImpl<MiniClickStatisticsMapper, MiniClickStatistics> implements MiniClickStatisticsService {

    @Resource
    private MiniClickStatisticsMapper miniClickStatisticsMapper;

    @Override
    public Page<MiniClickStatisticsVO> findMiniClickStatisticsPage(MiniClickStatisticsQuery query) {
        Page<MiniClickStatisticsVO> page = new Page<>(query.getPage(), query.getSize());
        List<MiniClickStatisticsVO> list = miniClickStatisticsMapper.findMiniClickStatisticsPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateMiniClickStatistics(MiniClickStatisticsDTO miniClickStatisticsDTO) {
        MiniClickStatistics miniClickStatistics = new MiniClickStatistics();

        BeanUtils.copyProperties(miniClickStatisticsDTO, miniClickStatistics);

        this.saveOrUpdate(miniClickStatistics);

        return miniClickStatistics.getId();
    }

    @Override
    public MiniClickStatisticsVO getMiniClickStatisticsById(Long id) {
        MiniClickStatistics miniClickStatistics = this.getById(id);
        if (CommonFuntions.isEmptyObject(miniClickStatistics)) {
            throw new BizException("查询失败，小程序点击统计表不存在");
        }

        MiniClickStatisticsVO miniClickStatisticsVO = new MiniClickStatisticsVO();
        BeanUtils.copyProperties(miniClickStatistics, miniClickStatisticsVO);
        return miniClickStatisticsVO;
    }
}
