package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PatrolRulePoint;
import com.jiumai.base.biz.query.PatrolRulePointQuery;
import com.jiumai.base.biz.vo.PatrolRulePointVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 巡查单规则点位表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PatrolRulePointMapper extends BaseMapper<PatrolRulePoint> {

    /**
     * 分页查询巡查单规则点位表
     * @param page
     * @param query
     * @return
     */
    List<PatrolRulePointVO> findPatrolRulePointPage(Page<PatrolRulePointVO> page, @Param("query") PatrolRulePointQuery query);
}
