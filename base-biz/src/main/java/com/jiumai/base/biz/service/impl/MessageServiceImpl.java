package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.MessageDTO;
import com.jiumai.base.biz.entity.Message;
import com.jiumai.base.biz.mapper.MessageMapper;
import com.jiumai.base.biz.query.MessageQuery;
import com.jiumai.base.biz.service.MessageService;
import com.jiumai.base.biz.vo.MessageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 通知互动消息表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Override
    public Page<MessageVO> findMessagePage(MessageQuery query) {
        Page<MessageVO> page = new Page<>(query.getPage(), query.getSize());
        List<MessageVO> list = messageMapper.findMessagePage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateMessage(MessageDTO messageDTO) {
        Message message = new Message();

        BeanUtils.copyProperties(messageDTO, message);

        this.saveOrUpdate(message);

        return message.getId();
    }

    @Override
    public MessageVO getMessageById(Long id) {
        Message message = this.getById(id);
        if (CommonFuntions.isEmptyObject(message)) {
            throw new BizException("查询失败，通知互动消息表不存在");
        }

        MessageVO messageVO = new MessageVO();
        BeanUtils.copyProperties(message, messageVO);
        return messageVO;
    }
}
