package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.GridPersonnelDTO;
import com.jiumai.base.biz.entity.GridPersonnel;
import com.jiumai.base.biz.mapper.GridPersonnelMapper;
import com.jiumai.base.biz.query.GridPersonnelQuery;
import com.jiumai.base.biz.service.GridPersonnelService;
import com.jiumai.base.biz.vo.GridPersonnelVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 网格人员信息 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class GridPersonnelServiceImpl extends ServiceImpl<GridPersonnelMapper, GridPersonnel> implements GridPersonnelService {

    @Resource
    private GridPersonnelMapper gridPersonnelMapper;

    @Override
    public Page<GridPersonnelVO> findGridPersonnelPage(GridPersonnelQuery query) {
        Page<GridPersonnelVO> page = new Page<>(query.getPage(), query.getSize());
        List<GridPersonnelVO> list = gridPersonnelMapper.findGridPersonnelPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateGridPersonnel(GridPersonnelDTO gridPersonnelDTO) {
        GridPersonnel gridPersonnel = new GridPersonnel();

        BeanUtils.copyProperties(gridPersonnelDTO, gridPersonnel);

        this.saveOrUpdate(gridPersonnel);

        return gridPersonnel.getId();
    }

    @Override
    public GridPersonnelVO getGridPersonnelById(Long id) {
        GridPersonnel gridPersonnel = this.getById(id);
        if (CommonFuntions.isEmptyObject(gridPersonnel)) {
            throw new BizException("查询失败，网格人员信息不存在");
        }

        GridPersonnelVO gridPersonnelVO = new GridPersonnelVO();
        BeanUtils.copyProperties(gridPersonnel, gridPersonnelVO);
        return gridPersonnelVO;
    }
}
