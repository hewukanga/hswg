package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.MiniClickStatisticsDetailsDTO;
import com.jiumai.base.biz.entity.MiniClickStatisticsDetails;
import com.jiumai.base.biz.mapper.MiniClickStatisticsDetailsMapper;
import com.jiumai.base.biz.query.MiniClickStatisticsDetailsQuery;
import com.jiumai.base.biz.service.MiniClickStatisticsDetailsService;
import com.jiumai.base.biz.vo.MiniClickStatisticsDetailsVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 小程序点击统计详情表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class MiniClickStatisticsDetailsServiceImpl extends ServiceImpl<MiniClickStatisticsDetailsMapper, MiniClickStatisticsDetails> implements MiniClickStatisticsDetailsService {

    @Resource
    private MiniClickStatisticsDetailsMapper miniClickStatisticsDetailsMapper;

    @Override
    public Page<MiniClickStatisticsDetailsVO> findMiniClickStatisticsDetailsPage(MiniClickStatisticsDetailsQuery query) {
        Page<MiniClickStatisticsDetailsVO> page = new Page<>(query.getPage(), query.getSize());
        List<MiniClickStatisticsDetailsVO> list = miniClickStatisticsDetailsMapper.findMiniClickStatisticsDetailsPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateMiniClickStatisticsDetails(MiniClickStatisticsDetailsDTO miniClickStatisticsDetailsDTO) {
        MiniClickStatisticsDetails miniClickStatisticsDetails = new MiniClickStatisticsDetails();

        BeanUtils.copyProperties(miniClickStatisticsDetailsDTO, miniClickStatisticsDetails);

        this.saveOrUpdate(miniClickStatisticsDetails);

        return miniClickStatisticsDetails.getId();
    }

    @Override
    public MiniClickStatisticsDetailsVO getMiniClickStatisticsDetailsById(Long id) {
        MiniClickStatisticsDetails miniClickStatisticsDetails = this.getById(id);
        if (CommonFuntions.isEmptyObject(miniClickStatisticsDetails)) {
            throw new BizException("查询失败，小程序点击统计详情表不存在");
        }

        MiniClickStatisticsDetailsVO miniClickStatisticsDetailsVO = new MiniClickStatisticsDetailsVO();
        BeanUtils.copyProperties(miniClickStatisticsDetails, miniClickStatisticsDetailsVO);
        return miniClickStatisticsDetailsVO;
    }
}
