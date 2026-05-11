package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.CamerasLocation;
import com.jiumai.base.biz.query.CamerasLocationQuery;
import com.jiumai.base.biz.vo.CamerasLocationVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 执法仪经纬度记录表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface CamerasLocationMapper extends BaseMapper<CamerasLocation> {

    /**
     * 分页查询执法仪经纬度记录表
     * @param page
     * @param query
     * @return
     */
    List<CamerasLocationVO> findCamerasLocationPage(Page<CamerasLocationVO> page, @Param("query") CamerasLocationQuery query);
}
