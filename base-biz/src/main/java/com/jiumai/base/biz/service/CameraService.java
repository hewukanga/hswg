package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.CameraDTO;
import com.jiumai.base.biz.entity.Camera;
import com.jiumai.base.biz.query.CameraQuery;
import com.jiumai.base.biz.vo.CameraVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * pm_camera 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface CameraService extends IService<Camera> {

    /**
     * 分页查询pm_camera
     * @param query
     * @return
     */
    Page<CameraVO> findCameraPage(CameraQuery query);

    /**
     * 添加或更新pm_camera
     * @param cameraDTO
     * @return
     */
    Long saveOrUpdateCamera(CameraDTO cameraDTO);

    /**
     * 通过id查询成功pm_camera详情
     * @param id
     * @return
     */
    CameraVO getCameraById(Long id);
}
