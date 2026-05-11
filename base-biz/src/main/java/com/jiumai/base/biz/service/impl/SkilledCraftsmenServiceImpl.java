package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.SkilledCraftsmenDTO;
import com.jiumai.base.biz.entity.SkilledCraftsmen;
import com.jiumai.base.biz.mapper.SkilledCraftsmenMapper;
import com.jiumai.base.biz.query.SkilledCraftsmenQuery;
import com.jiumai.base.biz.service.SkilledCraftsmenService;
import com.jiumai.base.biz.vo.SkilledCraftsmenVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 能工巧匠信息 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class SkilledCraftsmenServiceImpl extends ServiceImpl<SkilledCraftsmenMapper, SkilledCraftsmen> implements SkilledCraftsmenService {

    @Resource
    private SkilledCraftsmenMapper skilledCraftsmenMapper;

    @Override
    public Page<SkilledCraftsmenVO> findSkilledCraftsmenPage(SkilledCraftsmenQuery query) {
        Page<SkilledCraftsmenVO> page = new Page<>(query.getPage(), query.getSize());
        List<SkilledCraftsmenVO> list = skilledCraftsmenMapper.findSkilledCraftsmenPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateSkilledCraftsmen(SkilledCraftsmenDTO skilledCraftsmenDTO) {
        SkilledCraftsmen skilledCraftsmen = new SkilledCraftsmen();

        BeanUtils.copyProperties(skilledCraftsmenDTO, skilledCraftsmen);

        this.saveOrUpdate(skilledCraftsmen);

        return skilledCraftsmen.getId();
    }

    @Override
    public SkilledCraftsmenVO getSkilledCraftsmenById(Long id) {
        SkilledCraftsmen skilledCraftsmen = this.getById(id);
        if (CommonFuntions.isEmptyObject(skilledCraftsmen)) {
            throw new BizException("查询失败，能工巧匠信息不存在");
        }

        SkilledCraftsmenVO skilledCraftsmenVO = new SkilledCraftsmenVO();
        BeanUtils.copyProperties(skilledCraftsmen, skilledCraftsmenVO);
        return skilledCraftsmenVO;
    }
}
