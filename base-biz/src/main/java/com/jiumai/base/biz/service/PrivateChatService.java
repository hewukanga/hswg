package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PrivateChatDTO;
import com.jiumai.base.biz.entity.PrivateChat;
import com.jiumai.base.biz.query.PrivateChatQuery;
import com.jiumai.base.biz.vo.PrivateChatVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 私聊消息记录表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PrivateChatService extends IService<PrivateChat> {

    /**
     * 分页查询私聊消息记录表
     * @param query
     * @return
     */
    Page<PrivateChatVO> findPrivateChatPage(PrivateChatQuery query);

    /**
     * 添加或更新私聊消息记录表
     * @param privateChatDTO
     * @return
     */
    Long saveOrUpdatePrivateChat(PrivateChatDTO privateChatDTO);

    /**
     * 通过id查询成功私聊消息记录表详情
     * @param id
     * @return
     */
    PrivateChatVO getPrivateChatById(Long id);
}
