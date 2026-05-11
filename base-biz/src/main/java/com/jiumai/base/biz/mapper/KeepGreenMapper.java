package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.KeepGreen;
import com.jiumai.base.biz.query.KeepGreenQuery;
import com.jiumai.base.biz.vo.KeepGreenVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 保绿 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface KeepGreenMapper extends BaseMapper<KeepGreen> {

    /**
     * 分页查询保绿
     * @param page
     * @param query
     * @return
     */
    List<KeepGreenVO> findKeepGreenPage(Page<KeepGreenVO> page, @Param("query") KeepGreenQuery query);
}
