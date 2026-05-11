package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.CleaningAreaDTO;
import com.jiumai.base.biz.entity.CleaningArea;
import com.jiumai.base.biz.query.CleaningAreaQuery;
import com.jiumai.base.biz.vo.CleaningAreaVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 保洁区域管理 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface CleaningAreaService extends IService<CleaningArea> {

    /**
     * 分页查询保洁区域管理
     * @param query
     * @return
     */
    Page<CleaningAreaVO> findCleaningAreaPage(CleaningAreaQuery query);

    /**
     * 添加或更新保洁区域管理
     * @param cleaningAreaDTO
     * @return
     */
    Long saveOrUpdateCleaningArea(CleaningAreaDTO cleaningAreaDTO);

    /**
     * 通过id查询成功保洁区域管理详情
     * @param id
     * @return
     */
    CleaningAreaVO getCleaningAreaById(Long id);
}
