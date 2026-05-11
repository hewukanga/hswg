package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.SkilledCraftsmen;
import com.jiumai.base.biz.query.SkilledCraftsmenQuery;
import com.jiumai.base.biz.vo.SkilledCraftsmenVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 能工巧匠信息 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface SkilledCraftsmenMapper extends BaseMapper<SkilledCraftsmen> {

    /**
     * 分页查询能工巧匠信息
     * @param page
     * @param query
     * @return
     */
    List<SkilledCraftsmenVO> findSkilledCraftsmenPage(Page<SkilledCraftsmenVO> page, @Param("query") SkilledCraftsmenQuery query);
}
