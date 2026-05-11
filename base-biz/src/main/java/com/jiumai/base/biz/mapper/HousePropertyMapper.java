package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.HouseProperty;
import com.jiumai.base.biz.query.HousePropertyQuery;
import com.jiumai.base.biz.vo.HousePropertyVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 房产管理 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface HousePropertyMapper extends BaseMapper<HouseProperty> {

    /**
     * 分页查询房产管理
     * @param page
     * @param query
     * @return
     */
    List<HousePropertyVO> findHousePropertyPage(Page<HousePropertyVO> page, @Param("query") HousePropertyQuery query);
}
