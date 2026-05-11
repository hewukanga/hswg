package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.MessageNoticeDTO;
import com.jiumai.base.biz.entity.MessageNotice;
import com.jiumai.base.biz.mapper.MessageNoticeMapper;
import com.jiumai.base.biz.query.MessageNoticeQuery;
import com.jiumai.base.biz.service.MessageNoticeService;
import com.jiumai.base.biz.vo.MessageNoticeVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 消息通知表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class MessageNoticeServiceImpl extends ServiceImpl<MessageNoticeMapper, MessageNotice> implements MessageNoticeService {

    @Resource
    private MessageNoticeMapper messageNoticeMapper;

    @Override
    public Page<MessageNoticeVO> findMessageNoticePage(MessageNoticeQuery query) {
        Page<MessageNoticeVO> page = new Page<>(query.getPage(), query.getSize());
        List<MessageNoticeVO> list = messageNoticeMapper.findMessageNoticePage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateMessageNotice(MessageNoticeDTO messageNoticeDTO) {
        MessageNotice messageNotice = new MessageNotice();

        BeanUtils.copyProperties(messageNoticeDTO, messageNotice);

        this.saveOrUpdate(messageNotice);

        return messageNotice.getId();
    }

    @Override
    public MessageNoticeVO getMessageNoticeById(Long id) {
        MessageNotice messageNotice = this.getById(id);
        if (CommonFuntions.isEmptyObject(messageNotice)) {
            throw new BizException("查询失败，消息通知表不存在");
        }

        MessageNoticeVO messageNoticeVO = new MessageNoticeVO();
        BeanUtils.copyProperties(messageNotice, messageNoticeVO);
        return messageNoticeVO;
    }
}
