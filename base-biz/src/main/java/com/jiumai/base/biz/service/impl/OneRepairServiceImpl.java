package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.OneRepairDTO;
import com.jiumai.base.biz.entity.OneRepair;
import com.jiumai.base.biz.mapper.OneRepairMapper;
import com.jiumai.base.biz.query.OneRepairQuery;
import com.jiumai.base.biz.service.OneRepairService;
import com.jiumai.base.biz.vo.OneRepairVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 一键报修表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class OneRepairServiceImpl extends ServiceImpl<OneRepairMapper, OneRepair> implements OneRepairService {

    @Resource
    private OneRepairMapper oneRepairMapper;

    @Override
    public Page<OneRepairVO> findOneRepairPage(OneRepairQuery query) {
        Page<OneRepairVO> page = new Page<>(query.getPage(), query.getSize());
        List<OneRepairVO> list = oneRepairMapper.findOneRepairPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateOneRepair(OneRepairDTO oneRepairDTO) {
        OneRepair oneRepair = new OneRepair();

        BeanUtils.copyProperties(oneRepairDTO, oneRepair);

        this.saveOrUpdate(oneRepair);

        return oneRepair.getId();
    }

    @Override
    public OneRepairVO getOneRepairById(Long id) {
        OneRepair oneRepair = this.getById(id);
        if (CommonFuntions.isEmptyObject(oneRepair)) {
            throw new BizException("查询失败，一键报修表不存在");
        }

        OneRepairVO oneRepairVO = new OneRepairVO();
        BeanUtils.copyProperties(oneRepair, oneRepairVO);
        return oneRepairVO;
    }
}
