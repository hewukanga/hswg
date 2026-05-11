package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PartyMemberServiceCenterDTO;
import com.jiumai.base.biz.entity.PartyMemberServiceCenter;
import com.jiumai.base.biz.mapper.PartyMemberServiceCenterMapper;
import com.jiumai.base.biz.query.PartyMemberServiceCenterQuery;
import com.jiumai.base.biz.service.PartyMemberServiceCenterService;
import com.jiumai.base.biz.vo.PartyMemberServiceCenterVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 党员服务中心 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PartyMemberServiceCenterServiceImpl extends ServiceImpl<PartyMemberServiceCenterMapper, PartyMemberServiceCenter> implements PartyMemberServiceCenterService {

    @Resource
    private PartyMemberServiceCenterMapper partyMemberServiceCenterMapper;

    @Override
    public Page<PartyMemberServiceCenterVO> findPartyMemberServiceCenterPage(PartyMemberServiceCenterQuery query) {
        Page<PartyMemberServiceCenterVO> page = new Page<>(query.getPage(), query.getSize());
        List<PartyMemberServiceCenterVO> list = partyMemberServiceCenterMapper.findPartyMemberServiceCenterPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePartyMemberServiceCenter(PartyMemberServiceCenterDTO partyMemberServiceCenterDTO) {
        PartyMemberServiceCenter partyMemberServiceCenter = new PartyMemberServiceCenter();

        BeanUtils.copyProperties(partyMemberServiceCenterDTO, partyMemberServiceCenter);

        this.saveOrUpdate(partyMemberServiceCenter);

        return partyMemberServiceCenter.getId();
    }

    @Override
    public PartyMemberServiceCenterVO getPartyMemberServiceCenterById(Long id) {
        PartyMemberServiceCenter partyMemberServiceCenter = this.getById(id);
        if (CommonFuntions.isEmptyObject(partyMemberServiceCenter)) {
            throw new BizException("查询失败，党员服务中心不存在");
        }

        PartyMemberServiceCenterVO partyMemberServiceCenterVO = new PartyMemberServiceCenterVO();
        BeanUtils.copyProperties(partyMemberServiceCenter, partyMemberServiceCenterVO);
        return partyMemberServiceCenterVO;
    }
}
