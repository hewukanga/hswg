package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.NoticeDTO;
import com.jiumai.base.biz.entity.Notice;
import com.jiumai.base.biz.query.NoticeQuery;
import com.jiumai.base.biz.vo.NoticeVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 公告表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface NoticeService extends IService<Notice> {

    /**
     * 分页查询公告表
     * @param query
     * @return
     */
    Page<NoticeVO> findNoticePage(NoticeQuery query);

    /**
     * 添加或更新公告表
     * @param noticeDTO
     * @return
     */
    Long saveOrUpdateNotice(NoticeDTO noticeDTO);

    /**
     * 通过id查询成功公告表详情
     * @param id
     * @return
     */
    NoticeVO getNoticeById(Long id);
}
