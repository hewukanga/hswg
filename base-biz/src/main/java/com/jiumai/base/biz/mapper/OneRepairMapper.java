package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.OneRepair;
import com.jiumai.base.biz.query.OneRepairQuery;
import com.jiumai.base.biz.vo.OneRepairVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 一键报修表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface OneRepairMapper extends BaseMapper<OneRepair> {

    /**
     * 分页查询一键报修表
     * @param page
     * @param query
     * @return
     */
    List<OneRepairVO> findOneRepairPage(Page<OneRepairVO> page, @Param("query") OneRepairQuery query);
}
