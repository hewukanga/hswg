package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.RectificationFormDTO;
import com.jiumai.base.biz.entity.RectificationForm;
import com.jiumai.base.biz.query.RectificationFormQuery;
import com.jiumai.base.biz.vo.RectificationFormVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 整改单 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface RectificationFormService extends IService<RectificationForm> {

    /**
     * 分页查询整改单
     * @param query
     * @return
     */
    Page<RectificationFormVO> findRectificationFormPage(RectificationFormQuery query);

    /**
     * 添加或更新整改单
     * @param rectificationFormDTO
     * @return
     */
    Long saveOrUpdateRectificationForm(RectificationFormDTO rectificationFormDTO);

    /**
     * 通过id查询成功整改单详情
     * @param id
     * @return
     */
    RectificationFormVO getRectificationFormById(Long id);
}
