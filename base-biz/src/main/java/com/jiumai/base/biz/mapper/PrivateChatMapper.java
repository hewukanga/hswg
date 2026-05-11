package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PrivateChat;
import com.jiumai.base.biz.query.PrivateChatQuery;
import com.jiumai.base.biz.vo.PrivateChatVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 私聊消息记录表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PrivateChatMapper extends BaseMapper<PrivateChat> {

    /**
     * 分页查询私聊消息记录表
     * @param page
     * @param query
     * @return
     */
    List<PrivateChatVO> findPrivateChatPage(Page<PrivateChatVO> page, @Param("query") PrivateChatQuery query);
}
