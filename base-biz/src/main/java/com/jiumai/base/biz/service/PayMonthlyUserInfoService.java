package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PayMonthlyUserInfoDTO;
import com.jiumai.base.biz.entity.PayMonthlyUserInfo;
import com.jiumai.base.biz.query.PayMonthlyUserInfoQuery;
import com.jiumai.base.biz.vo.PayMonthlyUserInfoVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 包月用户信息(数泊推送) 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PayMonthlyUserInfoService extends IService<PayMonthlyUserInfo> {

    /**
     * 分页查询包月用户信息(数泊推送)
     * @param query
     * @return
     */
    Page<PayMonthlyUserInfoVO> findPayMonthlyUserInfoPage(PayMonthlyUserInfoQuery query);

    /**
     * 添加或更新包月用户信息(数泊推送)
     * @param payMonthlyUserInfoDTO
     * @return
     */
    Long saveOrUpdatePayMonthlyUserInfo(PayMonthlyUserInfoDTO payMonthlyUserInfoDTO);

    /**
     * 通过id查询成功包月用户信息(数泊推送)详情
     * @param id
     * @return
     */
    PayMonthlyUserInfoVO getPayMonthlyUserInfoById(Long id);
}
