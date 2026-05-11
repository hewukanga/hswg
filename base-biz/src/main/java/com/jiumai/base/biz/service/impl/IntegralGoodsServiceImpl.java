package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.IntegralGoodsDTO;
import com.jiumai.base.biz.entity.IntegralGoods;
import com.jiumai.base.biz.mapper.IntegralGoodsMapper;
import com.jiumai.base.biz.query.IntegralGoodsQuery;
import com.jiumai.base.biz.service.IntegralGoodsService;
import com.jiumai.base.biz.vo.IntegralGoodsVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 积分商品 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class IntegralGoodsServiceImpl extends ServiceImpl<IntegralGoodsMapper, IntegralGoods> implements IntegralGoodsService {

    @Resource
    private IntegralGoodsMapper integralGoodsMapper;

    @Override
    public Page<IntegralGoodsVO> findIntegralGoodsPage(IntegralGoodsQuery query) {
        Page<IntegralGoodsVO> page = new Page<>(query.getPage(), query.getSize());
        List<IntegralGoodsVO> list = integralGoodsMapper.findIntegralGoodsPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateIntegralGoods(IntegralGoodsDTO integralGoodsDTO) {
        IntegralGoods integralGoods = new IntegralGoods();

        BeanUtils.copyProperties(integralGoodsDTO, integralGoods);

        this.saveOrUpdate(integralGoods);

        return integralGoods.getId();
    }

    @Override
    public IntegralGoodsVO getIntegralGoodsById(Long id) {
        IntegralGoods integralGoods = this.getById(id);
        if (CommonFuntions.isEmptyObject(integralGoods)) {
            throw new BizException("查询失败，积分商品不存在");
        }

        IntegralGoodsVO integralGoodsVO = new IntegralGoodsVO();
        BeanUtils.copyProperties(integralGoods, integralGoodsVO);
        return integralGoodsVO;
    }
}
