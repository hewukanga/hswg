package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PatrolListPointDTO;
import com.jiumai.base.biz.entity.PatrolListPoint;
import com.jiumai.base.biz.mapper.PatrolListPointMapper;
import com.jiumai.base.biz.query.PatrolListPointQuery;
import com.jiumai.base.biz.service.PatrolListPointService;
import com.jiumai.base.biz.vo.PatrolListPointVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 巡查单点位表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PatrolListPointServiceImpl extends ServiceImpl<PatrolListPointMapper, PatrolListPoint> implements PatrolListPointService {

    @Resource
    private PatrolListPointMapper patrolListPointMapper;

    @Override
    public Page<PatrolListPointVO> findPatrolListPointPage(PatrolListPointQuery query) {
        Page<PatrolListPointVO> page = new Page<>(query.getPage(), query.getSize());
        List<PatrolListPointVO> list = patrolListPointMapper.findPatrolListPointPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePatrolListPoint(PatrolListPointDTO patrolListPointDTO) {
        PatrolListPoint patrolListPoint = new PatrolListPoint();

        BeanUtils.copyProperties(patrolListPointDTO, patrolListPoint);

        this.saveOrUpdate(patrolListPoint);

        return patrolListPoint.getId();
    }

    @Override
    public PatrolListPointVO getPatrolListPointById(Long id) {
        PatrolListPoint patrolListPoint = this.getById(id);
        if (CommonFuntions.isEmptyObject(patrolListPoint)) {
            throw new BizException("查询失败，巡查单点位表不存在");
        }

        PatrolListPointVO patrolListPointVO = new PatrolListPointVO();
        BeanUtils.copyProperties(patrolListPoint, patrolListPointVO);
        return patrolListPointVO;
    }
}
