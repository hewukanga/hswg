package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PrivateChatRelationDTO;
import com.jiumai.base.biz.entity.PrivateChatRelation;
import com.jiumai.base.biz.query.PrivateChatRelationQuery;
import com.jiumai.base.biz.vo.PrivateChatRelationVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 私聊消息记录关系表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PrivateChatRelationService extends IService<PrivateChatRelation> {

    /**
     * 分页查询私聊消息记录关系表
     * @param query
     * @return
     */
    Page<PrivateChatRelationVO> findPrivateChatRelationPage(PrivateChatRelationQuery query);

    /**
     * 添加或更新私聊消息记录关系表
     * @param privateChatRelationDTO
     * @return
     */
    Long saveOrUpdatePrivateChatRelation(PrivateChatRelationDTO privateChatRelationDTO);

    /**
     * 通过id查询成功私聊消息记录关系表详情
     * @param id
     * @return
     */
    PrivateChatRelationVO getPrivateChatRelationById(Long id);
}
