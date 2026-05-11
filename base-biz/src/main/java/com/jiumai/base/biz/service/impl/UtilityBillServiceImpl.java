package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.UtilityBillDTO;
import com.jiumai.base.biz.entity.UtilityBill;
import com.jiumai.base.biz.mapper.UtilityBillMapper;
import com.jiumai.base.biz.query.UtilityBillQuery;
import com.jiumai.base.biz.service.UtilityBillService;
import com.jiumai.base.biz.vo.UtilityBillVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 物业账单 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class UtilityBillServiceImpl extends ServiceImpl<UtilityBillMapper, UtilityBill> implements UtilityBillService {

    @Resource
    private UtilityBillMapper utilityBillMapper;

    @Override
    public Page<UtilityBillVO> findUtilityBillPage(UtilityBillQuery query) {
        Page<UtilityBillVO> page = new Page<>(query.getPage(), query.getSize());
        List<UtilityBillVO> list = utilityBillMapper.findUtilityBillPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateUtilityBill(UtilityBillDTO utilityBillDTO) {
        UtilityBill utilityBill = new UtilityBill();

        BeanUtils.copyProperties(utilityBillDTO, utilityBill);

        this.saveOrUpdate(utilityBill);

        return utilityBill.getId();
    }

    @Override
    public UtilityBillVO getUtilityBillById(Long id) {
        UtilityBill utilityBill = this.getById(id);
        if (CommonFuntions.isEmptyObject(utilityBill)) {
            throw new BizException("查询失败，物业账单不存在");
        }

        UtilityBillVO utilityBillVO = new UtilityBillVO();
        BeanUtils.copyProperties(utilityBill, utilityBillVO);
        return utilityBillVO;
    }
}
