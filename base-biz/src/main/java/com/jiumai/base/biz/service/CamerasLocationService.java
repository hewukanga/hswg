package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.CamerasLocationDTO;
import com.jiumai.base.biz.entity.CamerasLocation;
import com.jiumai.base.biz.query.CamerasLocationQuery;
import com.jiumai.base.biz.vo.CamerasLocationVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 执法仪经纬度记录表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface CamerasLocationService extends IService<CamerasLocation> {

    /**
     * 分页查询执法仪经纬度记录表
     * @param query
     * @return
     */
    Page<CamerasLocationVO> findCamerasLocationPage(CamerasLocationQuery query);

    /**
     * 添加或更新执法仪经纬度记录表
     * @param camerasLocationDTO
     * @return
     */
    String saveOrUpdateCamerasLocation(CamerasLocationDTO camerasLocationDTO);

    /**
     * 通过id查询成功执法仪经纬度记录表详情
     * @param id
     * @return
     */
    CamerasLocationVO getCamerasLocationById(String id);
}
