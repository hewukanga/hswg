package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.RectificationForm;
import com.jiumai.base.biz.query.RectificationFormQuery;
import com.jiumai.base.biz.vo.RectificationFormVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 整改单 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface RectificationFormMapper extends BaseMapper<RectificationForm> {

    /**
     * 分页查询整改单
     * @param page
     * @param query
     * @return
     */
    List<RectificationFormVO> findRectificationFormPage(Page<RectificationFormVO> page, @Param("query") RectificationFormQuery query);
}
