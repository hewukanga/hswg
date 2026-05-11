package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PatrolRuleSuitUser;
import com.jiumai.base.biz.query.PatrolRuleSuitUserQuery;
import com.jiumai.base.biz.vo.PatrolRuleSuitUserVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 巡查单规则适用人员表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PatrolRuleSuitUserMapper extends BaseMapper<PatrolRuleSuitUser> {

    /**
     * 分页查询巡查单规则适用人员表
     * @param page
     * @param query
     * @return
     */
    List<PatrolRuleSuitUserVO> findPatrolRuleSuitUserPage(Page<PatrolRuleSuitUserVO> page, @Param("query") PatrolRuleSuitUserQuery query);
}
