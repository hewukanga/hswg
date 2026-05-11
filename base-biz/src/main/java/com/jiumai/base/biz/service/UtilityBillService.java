package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.UtilityBillDTO;
import com.jiumai.base.biz.entity.UtilityBill;
import com.jiumai.base.biz.query.UtilityBillQuery;
import com.jiumai.base.biz.vo.UtilityBillVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 物业账单 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface UtilityBillService extends IService<UtilityBill> {

    /**
     * 分页查询物业账单
     * @param query
     * @return
     */
    Page<UtilityBillVO> findUtilityBillPage(UtilityBillQuery query);

    /**
     * 添加或更新物业账单
     * @param utilityBillDTO
     * @return
     */
    Long saveOrUpdateUtilityBill(UtilityBillDTO utilityBillDTO);

    /**
     * 通过id查询成功物业账单详情
     * @param id
     * @return
     */
    UtilityBillVO getUtilityBillById(Long id);
}
