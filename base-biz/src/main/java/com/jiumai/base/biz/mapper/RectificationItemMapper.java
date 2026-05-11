package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.RectificationItem;
import com.jiumai.base.biz.query.RectificationItemQuery;
import com.jiumai.base.biz.vo.RectificationItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 整改项 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface RectificationItemMapper extends BaseMapper<RectificationItem> {

    /**
     * 分页查询整改项
     * @param page
     * @param query
     * @return
     */
    List<RectificationItemVO> findRectificationItemPage(Page<RectificationItemVO> page, @Param("query") RectificationItemQuery query);
}
