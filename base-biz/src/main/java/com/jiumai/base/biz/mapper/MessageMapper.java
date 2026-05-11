package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.Message;
import com.jiumai.base.biz.query.MessageQuery;
import com.jiumai.base.biz.vo.MessageVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 通知互动消息表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface MessageMapper extends BaseMapper<Message> {

    /**
     * 分页查询通知互动消息表
     * @param page
     * @param query
     * @return
     */
    List<MessageVO> findMessagePage(Page<MessageVO> page, @Param("query") MessageQuery query);
}
