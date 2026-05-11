package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.GeneralTransactionDTO;
import com.jiumai.base.biz.entity.GeneralTransaction;
import com.jiumai.base.biz.query.GeneralTransactionQuery;
import com.jiumai.base.biz.vo.GeneralTransactionVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 交易流水表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface GeneralTransactionService extends IService<GeneralTransaction> {

    /**
     * 分页查询交易流水表
     * @param query
     * @return
     */
    Page<GeneralTransactionVO> findGeneralTransactionPage(GeneralTransactionQuery query);

    /**
     * 添加或更新交易流水表
     * @param generalTransactionDTO
     * @return
     */
    Long saveOrUpdateGeneralTransaction(GeneralTransactionDTO generalTransactionDTO);

    /**
     * 通过id查询成功交易流水表详情
     * @param id
     * @return
     */
    GeneralTransactionVO getGeneralTransactionById(Long id);
}
