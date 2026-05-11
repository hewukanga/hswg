package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.CameraDTO;
import com.jiumai.base.biz.entity.Camera;
import com.jiumai.base.biz.mapper.CameraMapper;
import com.jiumai.base.biz.query.CameraQuery;
import com.jiumai.base.biz.service.CameraService;
import com.jiumai.base.biz.vo.CameraVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * pm_camera 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class CameraServiceImpl extends ServiceImpl<CameraMapper, Camera> implements CameraService {

    @Resource
    private CameraMapper cameraMapper;

    @Override
    public Page<CameraVO> findCameraPage(CameraQuery query) {
        Page<CameraVO> page = new Page<>(query.getPage(), query.getSize());
        List<CameraVO> list = cameraMapper.findCameraPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateCamera(CameraDTO cameraDTO) {
        Camera camera = new Camera();

        BeanUtils.copyProperties(cameraDTO, camera);

        this.saveOrUpdate(camera);

        return camera.getId();
    }

    @Override
    public CameraVO getCameraById(Long id) {
        Camera camera = this.getById(id);
        if (CommonFuntions.isEmptyObject(camera)) {
            throw new BizException("查询失败，pm_camera不存在");
        }

        CameraVO cameraVO = new CameraVO();
        BeanUtils.copyProperties(camera, cameraVO);
        return cameraVO;
    }
}
