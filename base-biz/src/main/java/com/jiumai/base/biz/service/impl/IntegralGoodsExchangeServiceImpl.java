package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.IntegralGoodsExchangeDTO;
import com.jiumai.base.biz.entity.IntegralGoodsExchange;
import com.jiumai.base.biz.mapper.IntegralGoodsExchangeMapper;
import com.jiumai.base.biz.query.IntegralGoodsExchangeQuery;
import com.jiumai.base.biz.service.IntegralGoodsExchangeService;
import com.jiumai.base.biz.vo.IntegralGoodsExchangeVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 积分商品兑换记录 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class IntegralGoodsExchangeServiceImpl extends ServiceImpl<IntegralGoodsExchangeMapper, IntegralGoodsExchange> implements IntegralGoodsExchangeService {

    @Resource
    private IntegralGoodsExchangeMapper integralGoodsExchangeMapper;

    @Override
    public Page<IntegralGoodsExchangeVO> findIntegralGoodsExchangePage(IntegralGoodsExchangeQuery query) {
        Page<IntegralGoodsExchangeVO> page = new Page<>(query.getPage(), query.getSize());
        List<IntegralGoodsExchangeVO> list = integralGoodsExchangeMapper.findIntegralGoodsExchangePage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateIntegralGoodsExchange(IntegralGoodsExchangeDTO integralGoodsExchangeDTO) {
        IntegralGoodsExchange integralGoodsExchange = new IntegralGoodsExchange();

        BeanUtils.copyProperties(integralGoodsExchangeDTO, integralGoodsExchange);

        this.saveOrUpdate(integralGoodsExchange);

        return integralGoodsExchange.getId();
    }

    @Override
    public IntegralGoodsExchangeVO getIntegralGoodsExchangeById(Long id) {
        IntegralGoodsExchange integralGoodsExchange = this.getById(id);
        if (CommonFuntions.isEmptyObject(integralGoodsExchange)) {
            throw new BizException("查询失败，积分商品兑换记录不存在");
        }

        IntegralGoodsExchangeVO integralGoodsExchangeVO = new IntegralGoodsExchangeVO();
        BeanUtils.copyProperties(integralGoodsExchange, integralGoodsExchangeVO);
        return integralGoodsExchangeVO;
    }
}
