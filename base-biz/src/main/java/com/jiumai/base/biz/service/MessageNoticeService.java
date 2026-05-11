package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.MessageNoticeDTO;
import com.jiumai.base.biz.entity.MessageNotice;
import com.jiumai.base.biz.query.MessageNoticeQuery;
import com.jiumai.base.biz.vo.MessageNoticeVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 消息通知表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface MessageNoticeService extends IService<MessageNotice> {

    /**
     * 分页查询消息通知表
     * @param query
     * @return
     */
    Page<MessageNoticeVO> findMessageNoticePage(MessageNoticeQuery query);

    /**
     * 添加或更新消息通知表
     * @param messageNoticeDTO
     * @return
     */
    Long saveOrUpdateMessageNotice(MessageNoticeDTO messageNoticeDTO);

    /**
     * 通过id查询成功消息通知表详情
     * @param id
     * @return
     */
    MessageNoticeVO getMessageNoticeById(Long id);
}
