package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.RectificationItemDTO;
import com.jiumai.base.biz.entity.RectificationItem;
import com.jiumai.base.biz.query.RectificationItemQuery;
import com.jiumai.base.biz.vo.RectificationItemVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 整改项 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface RectificationItemService extends IService<RectificationItem> {

    /**
     * 分页查询整改项
     * @param query
     * @return
     */
    Page<RectificationItemVO> findRectificationItemPage(RectificationItemQuery query);

    /**
     * 添加或更新整改项
     * @param rectificationItemDTO
     * @return
     */
    Long saveOrUpdateRectificationItem(RectificationItemDTO rectificationItemDTO);

    /**
     * 通过id查询成功整改项详情
     * @param id
     * @return
     */
    RectificationItemVO getRectificationItemById(Long id);
}
