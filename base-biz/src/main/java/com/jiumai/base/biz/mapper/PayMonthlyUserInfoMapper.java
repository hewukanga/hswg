package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PayMonthlyUserInfo;
import com.jiumai.base.biz.query.PayMonthlyUserInfoQuery;
import com.jiumai.base.biz.vo.PayMonthlyUserInfoVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 包月用户信息(数泊推送) Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PayMonthlyUserInfoMapper extends BaseMapper<PayMonthlyUserInfo> {

    /**
     * 分页查询包月用户信息(数泊推送)
     * @param page
     * @param query
     * @return
     */
    List<PayMonthlyUserInfoVO> findPayMonthlyUserInfoPage(Page<PayMonthlyUserInfoVO> page, @Param("query") PayMonthlyUserInfoQuery query);
}
