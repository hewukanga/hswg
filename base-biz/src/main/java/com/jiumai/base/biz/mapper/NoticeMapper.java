package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.Notice;
import com.jiumai.base.biz.query.NoticeQuery;
import com.jiumai.base.biz.vo.NoticeVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 公告表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface NoticeMapper extends BaseMapper<Notice> {

    /**
     * 分页查询公告表
     * @param page
     * @param query
     * @return
     */
    List<NoticeVO> findNoticePage(Page<NoticeVO> page, @Param("query") NoticeQuery query);
}
