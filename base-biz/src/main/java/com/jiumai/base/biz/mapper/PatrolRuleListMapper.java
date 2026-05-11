package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PatrolRuleList;
import com.jiumai.base.biz.query.PatrolRuleListQuery;
import com.jiumai.base.biz.vo.PatrolRuleListVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 巡查单规则表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PatrolRuleListMapper extends BaseMapper<PatrolRuleList> {

    /**
     * 分页查询巡查单规则表
     * @param page
     * @param query
     * @return
     */
    List<PatrolRuleListVO> findPatrolRuleListPage(Page<PatrolRuleListVO> page, @Param("query") PatrolRuleListQuery query);
}
