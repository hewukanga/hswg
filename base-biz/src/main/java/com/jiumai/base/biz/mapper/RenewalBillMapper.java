package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.RenewalBill;
import com.jiumai.base.biz.query.RenewalBillQuery;
import com.jiumai.base.biz.vo.RenewalBillVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 续费账单 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface RenewalBillMapper extends BaseMapper<RenewalBill> {

    /**
     * 分页查询续费账单
     * @param page
     * @param query
     * @return
     */
    List<RenewalBillVO> findRenewalBillPage(Page<RenewalBillVO> page, @Param("query") RenewalBillQuery query);
}
