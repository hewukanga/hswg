package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PartyMemberDTO;
import com.jiumai.base.biz.entity.PartyMember;
import com.jiumai.base.biz.mapper.PartyMemberMapper;
import com.jiumai.base.biz.query.PartyMemberQuery;
import com.jiumai.base.biz.service.PartyMemberService;
import com.jiumai.base.biz.vo.PartyMemberVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 党员信息 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PartyMemberServiceImpl extends ServiceImpl<PartyMemberMapper, PartyMember> implements PartyMemberService {

    @Resource
    private PartyMemberMapper partyMemberMapper;

    @Override
    public Page<PartyMemberVO> findPartyMemberPage(PartyMemberQuery query) {
        Page<PartyMemberVO> page = new Page<>(query.getPage(), query.getSize());
        List<PartyMemberVO> list = partyMemberMapper.findPartyMemberPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePartyMember(PartyMemberDTO partyMemberDTO) {
        PartyMember partyMember = new PartyMember();

        BeanUtils.copyProperties(partyMemberDTO, partyMember);

        this.saveOrUpdate(partyMember);

        return partyMember.getId();
    }

    @Override
    public PartyMemberVO getPartyMemberById(Long id) {
        PartyMember partyMember = this.getById(id);
        if (CommonFuntions.isEmptyObject(partyMember)) {
            throw new BizException("查询失败，党员信息不存在");
        }

        PartyMemberVO partyMemberVO = new PartyMemberVO();
        BeanUtils.copyProperties(partyMember, partyMemberVO);
        return partyMemberVO;
    }
}
