package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.HousePropertyDTO;
import com.jiumai.base.biz.entity.HouseProperty;
import com.jiumai.base.biz.query.HousePropertyQuery;
import com.jiumai.base.biz.vo.HousePropertyVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 房产管理 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface HousePropertyService extends IService<HouseProperty> {

    /**
     * 分页查询房产管理
     * @param query
     * @return
     */
    Page<HousePropertyVO> findHousePropertyPage(HousePropertyQuery query);

    /**
     * 添加或更新房产管理
     * @param housePropertyDTO
     * @return
     */
    Long saveOrUpdateHouseProperty(HousePropertyDTO housePropertyDTO);

    /**
     * 通过id查询成功房产管理详情
     * @param id
     * @return
     */
    HousePropertyVO getHousePropertyById(Long id);
}
