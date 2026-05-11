package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.RectificationItemDTO;
import com.jiumai.base.biz.entity.RectificationItem;
import com.jiumai.base.biz.mapper.RectificationItemMapper;
import com.jiumai.base.biz.query.RectificationItemQuery;
import com.jiumai.base.biz.service.RectificationItemService;
import com.jiumai.base.biz.vo.RectificationItemVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 整改项 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class RectificationItemServiceImpl extends ServiceImpl<RectificationItemMapper, RectificationItem> implements RectificationItemService {

    @Resource
    private RectificationItemMapper rectificationItemMapper;

    @Override
    public Page<RectificationItemVO> findRectificationItemPage(RectificationItemQuery query) {
        Page<RectificationItemVO> page = new Page<>(query.getPage(), query.getSize());
        List<RectificationItemVO> list = rectificationItemMapper.findRectificationItemPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateRectificationItem(RectificationItemDTO rectificationItemDTO) {
        RectificationItem rectificationItem = new RectificationItem();

        BeanUtils.copyProperties(rectificationItemDTO, rectificationItem);

        this.saveOrUpdate(rectificationItem);

        return rectificationItem.getId();
    }

    @Override
    public RectificationItemVO getRectificationItemById(Long id) {
        RectificationItem rectificationItem = this.getById(id);
        if (CommonFuntions.isEmptyObject(rectificationItem)) {
            throw new BizException("查询失败，整改项不存在");
        }

        RectificationItemVO rectificationItemVO = new RectificationItemVO();
        BeanUtils.copyProperties(rectificationItem, rectificationItemVO);
        return rectificationItemVO;
    }
}
