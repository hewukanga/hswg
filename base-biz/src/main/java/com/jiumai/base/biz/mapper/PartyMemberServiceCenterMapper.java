package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PartyMemberServiceCenter;
import com.jiumai.base.biz.query.PartyMemberServiceCenterQuery;
import com.jiumai.base.biz.vo.PartyMemberServiceCenterVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 党员服务中心 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PartyMemberServiceCenterMapper extends BaseMapper<PartyMemberServiceCenter> {

    /**
     * 分页查询党员服务中心
     * @param page
     * @param query
     * @return
     */
    List<PartyMemberServiceCenterVO> findPartyMemberServiceCenterPage(Page<PartyMemberServiceCenterVO> page, @Param("query") PartyMemberServiceCenterQuery query);
}
