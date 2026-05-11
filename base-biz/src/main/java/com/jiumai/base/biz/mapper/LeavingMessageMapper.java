package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.LeavingMessage;
import com.jiumai.base.biz.query.LeavingMessageQuery;
import com.jiumai.base.biz.vo.LeavingMessageVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 留言表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface LeavingMessageMapper extends BaseMapper<LeavingMessage> {

    /**
     * 分页查询留言表
     * @param page
     * @param query
     * @return
     */
    List<LeavingMessageVO> findLeavingMessagePage(Page<LeavingMessageVO> page, @Param("query") LeavingMessageQuery query);
}
