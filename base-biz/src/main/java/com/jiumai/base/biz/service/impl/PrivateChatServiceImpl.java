package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PrivateChatDTO;
import com.jiumai.base.biz.entity.PrivateChat;
import com.jiumai.base.biz.mapper.PrivateChatMapper;
import com.jiumai.base.biz.query.PrivateChatQuery;
import com.jiumai.base.biz.service.PrivateChatService;
import com.jiumai.base.biz.vo.PrivateChatVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 私聊消息记录表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PrivateChatServiceImpl extends ServiceImpl<PrivateChatMapper, PrivateChat> implements PrivateChatService {

    @Resource
    private PrivateChatMapper privateChatMapper;

    @Override
    public Page<PrivateChatVO> findPrivateChatPage(PrivateChatQuery query) {
        Page<PrivateChatVO> page = new Page<>(query.getPage(), query.getSize());
        List<PrivateChatVO> list = privateChatMapper.findPrivateChatPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePrivateChat(PrivateChatDTO privateChatDTO) {
        PrivateChat privateChat = new PrivateChat();

        BeanUtils.copyProperties(privateChatDTO, privateChat);

        this.saveOrUpdate(privateChat);

        return privateChat.getId();
    }

    @Override
    public PrivateChatVO getPrivateChatById(Long id) {
        PrivateChat privateChat = this.getById(id);
        if (CommonFuntions.isEmptyObject(privateChat)) {
            throw new BizException("查询失败，私聊消息记录表不存在");
        }

        PrivateChatVO privateChatVO = new PrivateChatVO();
        BeanUtils.copyProperties(privateChat, privateChatVO);
        return privateChatVO;
    }
}
