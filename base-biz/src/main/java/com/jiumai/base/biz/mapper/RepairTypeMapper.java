package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.RepairType;
import com.jiumai.base.biz.query.RepairTypeQuery;
import com.jiumai.base.biz.vo.RepairTypeVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 维修项目配置表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface RepairTypeMapper extends BaseMapper<RepairType> {

    /**
     * 分页查询维修项目配置表
     * @param page
     * @param query
     * @return
     */
    List<RepairTypeVO> findRepairTypePage(Page<RepairTypeVO> page, @Param("query") RepairTypeQuery query);
}
