package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.MessageNotice;
import com.jiumai.base.biz.query.MessageNoticeQuery;
import com.jiumai.base.biz.vo.MessageNoticeVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 消息通知表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface MessageNoticeMapper extends BaseMapper<MessageNotice> {

    /**
     * 分页查询消息通知表
     * @param page
     * @param query
     * @return
     */
    List<MessageNoticeVO> findMessageNoticePage(Page<MessageNoticeVO> page, @Param("query") MessageNoticeQuery query);
}
