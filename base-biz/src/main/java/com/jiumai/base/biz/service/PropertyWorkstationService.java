package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PropertyWorkstationDTO;
import com.jiumai.base.biz.entity.PropertyWorkstation;
import com.jiumai.base.biz.query.PropertyWorkstationQuery;
import com.jiumai.base.biz.vo.PropertyWorkstationVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 物业工作站 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PropertyWorkstationService extends IService<PropertyWorkstation> {

    /**
     * 分页查询物业工作站
     * @param query
     * @return
     */
    Page<PropertyWorkstationVO> findPropertyWorkstationPage(PropertyWorkstationQuery query);

    /**
     * 添加或更新物业工作站
     * @param propertyWorkstationDTO
     * @return
     */
    Long saveOrUpdatePropertyWorkstation(PropertyWorkstationDTO propertyWorkstationDTO);

    /**
     * 通过id查询成功物业工作站详情
     * @param id
     * @return
     */
    PropertyWorkstationVO getPropertyWorkstationById(Long id);
}
