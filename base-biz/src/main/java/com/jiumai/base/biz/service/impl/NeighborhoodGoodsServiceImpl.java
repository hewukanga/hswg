package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.NeighborhoodGoodsDTO;
import com.jiumai.base.biz.entity.NeighborhoodGoods;
import com.jiumai.base.biz.mapper.NeighborhoodGoodsMapper;
import com.jiumai.base.biz.query.NeighborhoodGoodsQuery;
import com.jiumai.base.biz.service.NeighborhoodGoodsService;
import com.jiumai.base.biz.vo.NeighborhoodGoodsVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 邻里商品表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class NeighborhoodGoodsServiceImpl extends ServiceImpl<NeighborhoodGoodsMapper, NeighborhoodGoods> implements NeighborhoodGoodsService {

    @Resource
    private NeighborhoodGoodsMapper neighborhoodGoodsMapper;

    @Override
    public Page<NeighborhoodGoodsVO> findNeighborhoodGoodsPage(NeighborhoodGoodsQuery query) {
        Page<NeighborhoodGoodsVO> page = new Page<>(query.getPage(), query.getSize());
        List<NeighborhoodGoodsVO> list = neighborhoodGoodsMapper.findNeighborhoodGoodsPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateNeighborhoodGoods(NeighborhoodGoodsDTO neighborhoodGoodsDTO) {
        NeighborhoodGoods neighborhoodGoods = new NeighborhoodGoods();

        BeanUtils.copyProperties(neighborhoodGoodsDTO, neighborhoodGoods);

        this.saveOrUpdate(neighborhoodGoods);

        return neighborhoodGoods.getId();
    }

    @Override
    public NeighborhoodGoodsVO getNeighborhoodGoodsById(Long id) {
        NeighborhoodGoods neighborhoodGoods = this.getById(id);
        if (CommonFuntions.isEmptyObject(neighborhoodGoods)) {
            throw new BizException("查询失败，邻里商品表不存在");
        }

        NeighborhoodGoodsVO neighborhoodGoodsVO = new NeighborhoodGoodsVO();
        BeanUtils.copyProperties(neighborhoodGoods, neighborhoodGoodsVO);
        return neighborhoodGoodsVO;
    }
}
