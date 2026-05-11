package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.GeneralTransaction;
import com.jiumai.base.biz.query.GeneralTransactionQuery;
import com.jiumai.base.biz.vo.GeneralTransactionVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 交易流水表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface GeneralTransactionMapper extends BaseMapper<GeneralTransaction> {

    /**
     * 分页查询交易流水表
     * @param page
     * @param query
     * @return
     */
    List<GeneralTransactionVO> findGeneralTransactionPage(Page<GeneralTransactionVO> page, @Param("query") GeneralTransactionQuery query);
}
