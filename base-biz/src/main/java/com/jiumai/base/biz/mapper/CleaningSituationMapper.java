package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.CleaningSituation;
import com.jiumai.base.biz.query.CleaningSituationQuery;
import com.jiumai.base.biz.vo.CleaningSituationVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 保洁情况 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface CleaningSituationMapper extends BaseMapper<CleaningSituation> {

    /**
     * 分页查询保洁情况
     * @param page
     * @param query
     * @return
     */
    List<CleaningSituationVO> findCleaningSituationPage(Page<CleaningSituationVO> page, @Param("query") CleaningSituationQuery query);
}
