package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PrivateChatRelationDTO;
import com.jiumai.base.biz.entity.PrivateChatRelation;
import com.jiumai.base.biz.mapper.PrivateChatRelationMapper;
import com.jiumai.base.biz.query.PrivateChatRelationQuery;
import com.jiumai.base.biz.service.PrivateChatRelationService;
import com.jiumai.base.biz.vo.PrivateChatRelationVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 私聊消息记录关系表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PrivateChatRelationServiceImpl extends ServiceImpl<PrivateChatRelationMapper, PrivateChatRelation> implements PrivateChatRelationService {

    @Resource
    private PrivateChatRelationMapper privateChatRelationMapper;

    @Override
    public Page<PrivateChatRelationVO> findPrivateChatRelationPage(PrivateChatRelationQuery query) {
        Page<PrivateChatRelationVO> page = new Page<>(query.getPage(), query.getSize());
        List<PrivateChatRelationVO> list = privateChatRelationMapper.findPrivateChatRelationPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePrivateChatRelation(PrivateChatRelationDTO privateChatRelationDTO) {
        PrivateChatRelation privateChatRelation = new PrivateChatRelation();

        BeanUtils.copyProperties(privateChatRelationDTO, privateChatRelation);

        this.saveOrUpdate(privateChatRelation);

        return privateChatRelation.getId();
    }

    @Override
    public PrivateChatRelationVO getPrivateChatRelationById(Long id) {
        PrivateChatRelation privateChatRelation = this.getById(id);
        if (CommonFuntions.isEmptyObject(privateChatRelation)) {
            throw new BizException("查询失败，私聊消息记录关系表不存在");
        }

        PrivateChatRelationVO privateChatRelationVO = new PrivateChatRelationVO();
        BeanUtils.copyProperties(privateChatRelation, privateChatRelationVO);
        return privateChatRelationVO;
    }
}
