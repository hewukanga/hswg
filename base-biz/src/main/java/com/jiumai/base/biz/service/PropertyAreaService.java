package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PropertyAreaDTO;
import com.jiumai.base.biz.entity.PropertyArea;
import com.jiumai.base.biz.query.PropertyAreaQuery;
import com.jiumai.base.biz.vo.PropertyAreaVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 物业区域表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PropertyAreaService extends IService<PropertyArea> {

    /**
     * 分页查询物业区域表
     * @param query
     * @return
     */
    Page<PropertyAreaVO> findPropertyAreaPage(PropertyAreaQuery query);

    /**
     * 添加或更新物业区域表
     * @param propertyAreaDTO
     * @return
     */
    Long saveOrUpdatePropertyArea(PropertyAreaDTO propertyAreaDTO);

    /**
     * 通过id查询成功物业区域表详情
     * @param id
     * @return
     */
    PropertyAreaVO getPropertyAreaById(Long id);
}
