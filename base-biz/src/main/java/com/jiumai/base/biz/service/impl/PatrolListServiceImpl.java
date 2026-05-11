package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PatrolListDTO;
import com.jiumai.base.biz.entity.PatrolList;
import com.jiumai.base.biz.mapper.PatrolListMapper;
import com.jiumai.base.biz.query.PatrolListQuery;
import com.jiumai.base.biz.service.PatrolListService;
import com.jiumai.base.biz.vo.PatrolListVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 巡查单表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PatrolListServiceImpl extends ServiceImpl<PatrolListMapper, PatrolList> implements PatrolListService {

    @Resource
    private PatrolListMapper patrolListMapper;

    @Override
    public Page<PatrolListVO> findPatrolListPage(PatrolListQuery query) {
        Page<PatrolListVO> page = new Page<>(query.getPage(), query.getSize());
        List<PatrolListVO> list = patrolListMapper.findPatrolListPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePatrolList(PatrolListDTO patrolListDTO) {
        PatrolList patrolList = new PatrolList();

        BeanUtils.copyProperties(patrolListDTO, patrolList);

        this.saveOrUpdate(patrolList);

        return patrolList.getId();
    }

    @Override
    public PatrolListVO getPatrolListById(Long id) {
        PatrolList patrolList = this.getById(id);
        if (CommonFuntions.isEmptyObject(patrolList)) {
            throw new BizException("查询失败，巡查单表不存在");
        }

        PatrolListVO patrolListVO = new PatrolListVO();
        BeanUtils.copyProperties(patrolList, patrolListVO);
        return patrolListVO;
    }
}
