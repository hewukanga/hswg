package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PropertyWorkstation;
import com.jiumai.base.biz.query.PropertyWorkstationQuery;
import com.jiumai.base.biz.vo.PropertyWorkstationVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 物业工作站 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PropertyWorkstationMapper extends BaseMapper<PropertyWorkstation> {

    /**
     * 分页查询物业工作站
     * @param page
     * @param query
     * @return
     */
    List<PropertyWorkstationVO> findPropertyWorkstationPage(Page<PropertyWorkstationVO> page, @Param("query") PropertyWorkstationQuery query);
}
