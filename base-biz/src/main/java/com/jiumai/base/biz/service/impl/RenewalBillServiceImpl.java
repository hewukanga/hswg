package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.RenewalBillDTO;
import com.jiumai.base.biz.entity.RenewalBill;
import com.jiumai.base.biz.mapper.RenewalBillMapper;
import com.jiumai.base.biz.query.RenewalBillQuery;
import com.jiumai.base.biz.service.RenewalBillService;
import com.jiumai.base.biz.vo.RenewalBillVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 续费账单 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class RenewalBillServiceImpl extends ServiceImpl<RenewalBillMapper, RenewalBill> implements RenewalBillService {

    @Resource
    private RenewalBillMapper renewalBillMapper;

    @Override
    public Page<RenewalBillVO> findRenewalBillPage(RenewalBillQuery query) {
        Page<RenewalBillVO> page = new Page<>(query.getPage(), query.getSize());
        List<RenewalBillVO> list = renewalBillMapper.findRenewalBillPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateRenewalBill(RenewalBillDTO renewalBillDTO) {
        RenewalBill renewalBill = new RenewalBill();

        BeanUtils.copyProperties(renewalBillDTO, renewalBill);

        this.saveOrUpdate(renewalBill);

        return renewalBill.getId();
    }

    @Override
    public RenewalBillVO getRenewalBillById(Long id) {
        RenewalBill renewalBill = this.getById(id);
        if (CommonFuntions.isEmptyObject(renewalBill)) {
            throw new BizException("查询失败，续费账单不存在");
        }

        RenewalBillVO renewalBillVO = new RenewalBillVO();
        BeanUtils.copyProperties(renewalBill, renewalBillVO);
        return renewalBillVO;
    }
}
