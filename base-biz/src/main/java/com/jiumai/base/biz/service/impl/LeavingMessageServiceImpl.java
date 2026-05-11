package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.LeavingMessageDTO;
import com.jiumai.base.biz.entity.LeavingMessage;
import com.jiumai.base.biz.mapper.LeavingMessageMapper;
import com.jiumai.base.biz.query.LeavingMessageQuery;
import com.jiumai.base.biz.service.LeavingMessageService;
import com.jiumai.base.biz.vo.LeavingMessageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 留言表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class LeavingMessageServiceImpl extends ServiceImpl<LeavingMessageMapper, LeavingMessage> implements LeavingMessageService {

    @Resource
    private LeavingMessageMapper leavingMessageMapper;

    @Override
    public Page<LeavingMessageVO> findLeavingMessagePage(LeavingMessageQuery query) {
        Page<LeavingMessageVO> page = new Page<>(query.getPage(), query.getSize());
        List<LeavingMessageVO> list = leavingMessageMapper.findLeavingMessagePage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateLeavingMessage(LeavingMessageDTO leavingMessageDTO) {
        LeavingMessage leavingMessage = new LeavingMessage();

        BeanUtils.copyProperties(leavingMessageDTO, leavingMessage);

        this.saveOrUpdate(leavingMessage);

        return leavingMessage.getId();
    }

    @Override
    public LeavingMessageVO getLeavingMessageById(Long id) {
        LeavingMessage leavingMessage = this.getById(id);
        if (CommonFuntions.isEmptyObject(leavingMessage)) {
            throw new BizException("查询失败，留言表不存在");
        }

        LeavingMessageVO leavingMessageVO = new LeavingMessageVO();
        BeanUtils.copyProperties(leavingMessage, leavingMessageVO);
        return leavingMessageVO;
    }
}
