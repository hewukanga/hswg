package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ShopDTO;
import com.jiumai.base.biz.entity.Shop;
import com.jiumai.base.biz.mapper.ShopMapper;
import com.jiumai.base.biz.query.ShopQuery;
import com.jiumai.base.biz.service.ShopService;
import com.jiumai.base.biz.vo.ShopVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 店铺信息表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

    @Resource
    private ShopMapper shopMapper;

    @Override
    public Page<ShopVO> findShopPage(ShopQuery query) {
        Page<ShopVO> page = new Page<>(query.getPage(), query.getSize());
        List<ShopVO> list = shopMapper.findShopPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateShop(ShopDTO shopDTO) {
        Shop shop = new Shop();

        BeanUtils.copyProperties(shopDTO, shop);

        this.saveOrUpdate(shop);

        return shop.getId();
    }

    @Override
    public ShopVO getShopById(Long id) {
        Shop shop = this.getById(id);
        if (CommonFuntions.isEmptyObject(shop)) {
            throw new BizException("查询失败，店铺信息表不存在");
        }

        ShopVO shopVO = new ShopVO();
        BeanUtils.copyProperties(shop, shopVO);
        return shopVO;
    }
}
