package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PayMonthlyUserInfoDTO;
import com.jiumai.base.biz.entity.PayMonthlyUserInfo;
import com.jiumai.base.biz.mapper.PayMonthlyUserInfoMapper;
import com.jiumai.base.biz.query.PayMonthlyUserInfoQuery;
import com.jiumai.base.biz.service.PayMonthlyUserInfoService;
import com.jiumai.base.biz.vo.PayMonthlyUserInfoVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 包月用户信息(数泊推送) 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PayMonthlyUserInfoServiceImpl extends ServiceImpl<PayMonthlyUserInfoMapper, PayMonthlyUserInfo> implements PayMonthlyUserInfoService {

    @Resource
    private PayMonthlyUserInfoMapper payMonthlyUserInfoMapper;

    @Override
    public Page<PayMonthlyUserInfoVO> findPayMonthlyUserInfoPage(PayMonthlyUserInfoQuery query) {
        Page<PayMonthlyUserInfoVO> page = new Page<>(query.getPage(), query.getSize());
        List<PayMonthlyUserInfoVO> list = payMonthlyUserInfoMapper.findPayMonthlyUserInfoPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePayMonthlyUserInfo(PayMonthlyUserInfoDTO payMonthlyUserInfoDTO) {
        PayMonthlyUserInfo payMonthlyUserInfo = new PayMonthlyUserInfo();

        BeanUtils.copyProperties(payMonthlyUserInfoDTO, payMonthlyUserInfo);

        this.saveOrUpdate(payMonthlyUserInfo);

        return payMonthlyUserInfo.getId();
    }

    @Override
    public PayMonthlyUserInfoVO getPayMonthlyUserInfoById(Long id) {
        PayMonthlyUserInfo payMonthlyUserInfo = this.getById(id);
        if (CommonFuntions.isEmptyObject(payMonthlyUserInfo)) {
            throw new BizException("查询失败，包月用户信息(数泊推送)不存在");
        }

        PayMonthlyUserInfoVO payMonthlyUserInfoVO = new PayMonthlyUserInfoVO();
        BeanUtils.copyProperties(payMonthlyUserInfo, payMonthlyUserInfoVO);
        return payMonthlyUserInfoVO;
    }
}
