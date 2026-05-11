package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.UtilityBill;
import com.jiumai.base.biz.query.UtilityBillQuery;
import com.jiumai.base.biz.vo.UtilityBillVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 物业账单 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface UtilityBillMapper extends BaseMapper<UtilityBill> {

    /**
     * 分页查询物业账单
     * @param page
     * @param query
     * @return
     */
    List<UtilityBillVO> findUtilityBillPage(Page<UtilityBillVO> page, @Param("query") UtilityBillQuery query);
}
