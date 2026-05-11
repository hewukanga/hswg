package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.MessageDTO;
import com.jiumai.base.biz.entity.Message;
import com.jiumai.base.biz.query.MessageQuery;
import com.jiumai.base.biz.vo.MessageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 通知互动消息表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface MessageService extends IService<Message> {

    /**
     * 分页查询通知互动消息表
     * @param query
     * @return
     */
    Page<MessageVO> findMessagePage(MessageQuery query);

    /**
     * 添加或更新通知互动消息表
     * @param messageDTO
     * @return
     */
    Long saveOrUpdateMessage(MessageDTO messageDTO);

    /**
     * 通过id查询成功通知互动消息表详情
     * @param id
     * @return
     */
    MessageVO getMessageById(Long id);
}
