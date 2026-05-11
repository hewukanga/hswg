package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PrivateChatRelation;
import com.jiumai.base.biz.query.PrivateChatRelationQuery;
import com.jiumai.base.biz.vo.PrivateChatRelationVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 私聊消息记录关系表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PrivateChatRelationMapper extends BaseMapper<PrivateChatRelation> {

    /**
     * 分页查询私聊消息记录关系表
     * @param page
     * @param query
     * @return
     */
    List<PrivateChatRelationVO> findPrivateChatRelationPage(Page<PrivateChatRelationVO> page, @Param("query") PrivateChatRelationQuery query);
}
