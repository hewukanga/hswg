package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.CleaningAreaOpDTO;
import com.jiumai.base.biz.entity.CleaningAreaOp;
import com.jiumai.base.biz.mapper.CleaningAreaOpMapper;
import com.jiumai.base.biz.query.CleaningAreaOpQuery;
import com.jiumai.base.biz.service.CleaningAreaOpService;
import com.jiumai.base.biz.vo.CleaningAreaOpVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 保洁区域人员 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class CleaningAreaOpServiceImpl extends ServiceImpl<CleaningAreaOpMapper, CleaningAreaOp> implements CleaningAreaOpService {

    @Resource
    private CleaningAreaOpMapper cleaningAreaOpMapper;

    @Override
    public Page<CleaningAreaOpVO> findCleaningAreaOpPage(CleaningAreaOpQuery query) {
        Page<CleaningAreaOpVO> page = new Page<>(query.getPage(), query.getSize());
        List<CleaningAreaOpVO> list = cleaningAreaOpMapper.findCleaningAreaOpPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateCleaningAreaOp(CleaningAreaOpDTO cleaningAreaOpDTO) {
        CleaningAreaOp cleaningAreaOp = new CleaningAreaOp();

        BeanUtils.copyProperties(cleaningAreaOpDTO, cleaningAreaOp);

        this.saveOrUpdate(cleaningAreaOp);

        return cleaningAreaOp.getId();
    }

    @Override
    public CleaningAreaOpVO getCleaningAreaOpById(Long id) {
        CleaningAreaOp cleaningAreaOp = this.getById(id);
        if (CommonFuntions.isEmptyObject(cleaningAreaOp)) {
            throw new BizException("查询失败，保洁区域人员不存在");
        }

        CleaningAreaOpVO cleaningAreaOpVO = new CleaningAreaOpVO();
        BeanUtils.copyProperties(cleaningAreaOp, cleaningAreaOpVO);
        return cleaningAreaOpVO;
    }
}
