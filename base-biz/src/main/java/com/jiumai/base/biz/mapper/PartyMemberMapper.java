package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PartyMember;
import com.jiumai.base.biz.query.PartyMemberQuery;
import com.jiumai.base.biz.vo.PartyMemberVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 党员信息 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PartyMemberMapper extends BaseMapper<PartyMember> {

    /**
     * 分页查询党员信息
     * @param page
     * @param query
     * @return
     */
    List<PartyMemberVO> findPartyMemberPage(Page<PartyMemberVO> page, @Param("query") PartyMemberQuery query);
}
