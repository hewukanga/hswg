package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PartyMemberServiceCenterDTO;
import com.jiumai.base.biz.entity.PartyMemberServiceCenter;
import com.jiumai.base.biz.query.PartyMemberServiceCenterQuery;
import com.jiumai.base.biz.vo.PartyMemberServiceCenterVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 党员服务中心 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PartyMemberServiceCenterService extends IService<PartyMemberServiceCenter> {

    /**
     * 分页查询党员服务中心
     * @param query
     * @return
     */
    Page<PartyMemberServiceCenterVO> findPartyMemberServiceCenterPage(PartyMemberServiceCenterQuery query);

    /**
     * 添加或更新党员服务中心
     * @param partyMemberServiceCenterDTO
     * @return
     */
    Long saveOrUpdatePartyMemberServiceCenter(PartyMemberServiceCenterDTO partyMemberServiceCenterDTO);

    /**
     * 通过id查询成功党员服务中心详情
     * @param id
     * @return
     */
    PartyMemberServiceCenterVO getPartyMemberServiceCenterById(Long id);
}
