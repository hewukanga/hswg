package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.CleaningAreaDTO;
import com.jiumai.base.biz.entity.CleaningArea;
import com.jiumai.base.biz.mapper.CleaningAreaMapper;
import com.jiumai.base.biz.query.CleaningAreaQuery;
import com.jiumai.base.biz.service.CleaningAreaService;
import com.jiumai.base.biz.vo.CleaningAreaVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 保洁区域管理 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class CleaningAreaServiceImpl extends ServiceImpl<CleaningAreaMapper, CleaningArea> implements CleaningAreaService {

    @Resource
    private CleaningAreaMapper cleaningAreaMapper;

    @Override
    public Page<CleaningAreaVO> findCleaningAreaPage(CleaningAreaQuery query) {
        Page<CleaningAreaVO> page = new Page<>(query.getPage(), query.getSize());
        List<CleaningAreaVO> list = cleaningAreaMapper.findCleaningAreaPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateCleaningArea(CleaningAreaDTO cleaningAreaDTO) {
        CleaningArea cleaningArea = new CleaningArea();

        BeanUtils.copyProperties(cleaningAreaDTO, cleaningArea);

        this.saveOrUpdate(cleaningArea);

        return cleaningArea.getId();
    }

    @Override
    public CleaningAreaVO getCleaningAreaById(Long id) {
        CleaningArea cleaningArea = this.getById(id);
        if (CommonFuntions.isEmptyObject(cleaningArea)) {
            throw new BizException("查询失败，保洁区域管理不存在");
        }

        CleaningAreaVO cleaningAreaVO = new CleaningAreaVO();
        BeanUtils.copyProperties(cleaningArea, cleaningAreaVO);
        return cleaningAreaVO;
    }
}
