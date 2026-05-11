package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.CleaningAreaOp;
import com.jiumai.base.biz.query.CleaningAreaOpQuery;
import com.jiumai.base.biz.vo.CleaningAreaOpVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 保洁区域人员 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface CleaningAreaOpMapper extends BaseMapper<CleaningAreaOp> {

    /**
     * 分页查询保洁区域人员
     * @param page
     * @param query
     * @return
     */
    List<CleaningAreaOpVO> findCleaningAreaOpPage(Page<CleaningAreaOpVO> page, @Param("query") CleaningAreaOpQuery query);
}
