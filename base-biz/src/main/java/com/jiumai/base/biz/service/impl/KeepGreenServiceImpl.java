package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.KeepGreenDTO;
import com.jiumai.base.biz.entity.KeepGreen;
import com.jiumai.base.biz.mapper.KeepGreenMapper;
import com.jiumai.base.biz.query.KeepGreenQuery;
import com.jiumai.base.biz.service.KeepGreenService;
import com.jiumai.base.biz.vo.KeepGreenVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 保绿 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class KeepGreenServiceImpl extends ServiceImpl<KeepGreenMapper, KeepGreen> implements KeepGreenService {

    @Resource
    private KeepGreenMapper keepGreenMapper;

    @Override
    public Page<KeepGreenVO> findKeepGreenPage(KeepGreenQuery query) {
        Page<KeepGreenVO> page = new Page<>(query.getPage(), query.getSize());
        List<KeepGreenVO> list = keepGreenMapper.findKeepGreenPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateKeepGreen(KeepGreenDTO keepGreenDTO) {
        KeepGreen keepGreen = new KeepGreen();

        BeanUtils.copyProperties(keepGreenDTO, keepGreen);

        this.saveOrUpdate(keepGreen);

        return keepGreen.getId();
    }

    @Override
    public KeepGreenVO getKeepGreenById(Long id) {
        KeepGreen keepGreen = this.getById(id);
        if (CommonFuntions.isEmptyObject(keepGreen)) {
            throw new BizException("查询失败，保绿不存在");
        }

        KeepGreenVO keepGreenVO = new KeepGreenVO();
        BeanUtils.copyProperties(keepGreen, keepGreenVO);
        return keepGreenVO;
    }
}
