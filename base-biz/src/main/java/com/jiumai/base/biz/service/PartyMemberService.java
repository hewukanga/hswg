package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PartyMemberDTO;
import com.jiumai.base.biz.entity.PartyMember;
import com.jiumai.base.biz.query.PartyMemberQuery;
import com.jiumai.base.biz.vo.PartyMemberVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 党员信息 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PartyMemberService extends IService<PartyMember> {

    /**
     * 分页查询党员信息
     * @param query
     * @return
     */
    Page<PartyMemberVO> findPartyMemberPage(PartyMemberQuery query);

    /**
     * 添加或更新党员信息
     * @param partyMemberDTO
     * @return
     */
    Long saveOrUpdatePartyMember(PartyMemberDTO partyMemberDTO);

    /**
     * 通过id查询成功党员信息详情
     * @param id
     * @return
     */
    PartyMemberVO getPartyMemberById(Long id);
}
