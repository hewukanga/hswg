package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.GeneralTransactionDTO;
import com.jiumai.base.biz.entity.GeneralTransaction;
import com.jiumai.base.biz.mapper.GeneralTransactionMapper;
import com.jiumai.base.biz.query.GeneralTransactionQuery;
import com.jiumai.base.biz.service.GeneralTransactionService;
import com.jiumai.base.biz.vo.GeneralTransactionVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 交易流水表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class GeneralTransactionServiceImpl extends ServiceImpl<GeneralTransactionMapper, GeneralTransaction> implements GeneralTransactionService {

    @Resource
    private GeneralTransactionMapper generalTransactionMapper;

    @Override
    public Page<GeneralTransactionVO> findGeneralTransactionPage(GeneralTransactionQuery query) {
        Page<GeneralTransactionVO> page = new Page<>(query.getPage(), query.getSize());
        List<GeneralTransactionVO> list = generalTransactionMapper.findGeneralTransactionPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateGeneralTransaction(GeneralTransactionDTO generalTransactionDTO) {
        GeneralTransaction generalTransaction = new GeneralTransaction();

        BeanUtils.copyProperties(generalTransactionDTO, generalTransaction);

        this.saveOrUpdate(generalTransaction);

        return generalTransaction.getId();
    }

    @Override
    public GeneralTransactionVO getGeneralTransactionById(Long id) {
        GeneralTransaction generalTransaction = this.getById(id);
        if (CommonFuntions.isEmptyObject(generalTransaction)) {
            throw new BizException("查询失败，交易流水表不存在");
        }

        GeneralTransactionVO generalTransactionVO = new GeneralTransactionVO();
        BeanUtils.copyProperties(generalTransaction, generalTransactionVO);
        return generalTransactionVO;
    }
}
