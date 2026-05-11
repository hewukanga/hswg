package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.RenewalBillDTO;
import com.jiumai.base.biz.entity.RenewalBill;
import com.jiumai.base.biz.query.RenewalBillQuery;
import com.jiumai.base.biz.vo.RenewalBillVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 续费账单 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface RenewalBillService extends IService<RenewalBill> {

    /**
     * 分页查询续费账单
     * @param query
     * @return
     */
    Page<RenewalBillVO> findRenewalBillPage(RenewalBillQuery query);

    /**
     * 添加或更新续费账单
     * @param renewalBillDTO
     * @return
     */
    Long saveOrUpdateRenewalBill(RenewalBillDTO renewalBillDTO);

    /**
     * 通过id查询成功续费账单详情
     * @param id
     * @return
     */
    RenewalBillVO getRenewalBillById(Long id);
}
