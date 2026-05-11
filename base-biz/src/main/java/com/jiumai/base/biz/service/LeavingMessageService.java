package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.LeavingMessageDTO;
import com.jiumai.base.biz.entity.LeavingMessage;
import com.jiumai.base.biz.query.LeavingMessageQuery;
import com.jiumai.base.biz.vo.LeavingMessageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 留言表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface LeavingMessageService extends IService<LeavingMessage> {

    /**
     * 分页查询留言表
     * @param query
     * @return
     */
    Page<LeavingMessageVO> findLeavingMessagePage(LeavingMessageQuery query);

    /**
     * 添加或更新留言表
     * @param leavingMessageDTO
     * @return
     */
    Long saveOrUpdateLeavingMessage(LeavingMessageDTO leavingMessageDTO);

    /**
     * 通过id查询成功留言表详情
     * @param id
     * @return
     */
    LeavingMessageVO getLeavingMessageById(Long id);
}
