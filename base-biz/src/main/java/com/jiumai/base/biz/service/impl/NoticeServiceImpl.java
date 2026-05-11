package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.NoticeDTO;
import com.jiumai.base.biz.entity.Notice;
import com.jiumai.base.biz.mapper.NoticeMapper;
import com.jiumai.base.biz.query.NoticeQuery;
import com.jiumai.base.biz.service.NoticeService;
import com.jiumai.base.biz.vo.NoticeVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 公告表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public Page<NoticeVO> findNoticePage(NoticeQuery query) {
        Page<NoticeVO> page = new Page<>(query.getPage(), query.getSize());
        List<NoticeVO> list = noticeMapper.findNoticePage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateNotice(NoticeDTO noticeDTO) {
        Notice notice = new Notice();

        BeanUtils.copyProperties(noticeDTO, notice);

        this.saveOrUpdate(notice);

        return notice.getId();
    }

    @Override
    public NoticeVO getNoticeById(Long id) {
        Notice notice = this.getById(id);
        if (CommonFuntions.isEmptyObject(notice)) {
            throw new BizException("查询失败，公告表不存在");
        }

        NoticeVO noticeVO = new NoticeVO();
        BeanUtils.copyProperties(notice, noticeVO);
        return noticeVO;
    }
}
