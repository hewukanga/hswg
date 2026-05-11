package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.Camera;
import com.jiumai.base.biz.query.CameraQuery;
import com.jiumai.base.biz.vo.CameraVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * pm_camera Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface CameraMapper extends BaseMapper<Camera> {

    /**
     * 分页查询pm_camera
     * @param page
     * @param query
     * @return
     */
    List<CameraVO> findCameraPage(Page<CameraVO> page, @Param("query") CameraQuery query);
}
