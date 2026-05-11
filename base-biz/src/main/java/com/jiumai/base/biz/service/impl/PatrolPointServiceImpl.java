package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PatrolPointDTO;
import com.jiumai.base.biz.entity.PatrolPoint;
import com.jiumai.base.biz.mapper.PatrolPointMapper;
import com.jiumai.base.biz.query.PatrolPointQuery;
import com.jiumai.base.biz.service.PatrolPointService;
import com.jiumai.base.biz.vo.PatrolPointVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 巡查点位表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PatrolPointServiceImpl extends ServiceImpl<PatrolPointMapper, PatrolPoint> implements PatrolPointService {

    @Resource
    private PatrolPointMapper patrolPointMapper;

    @Override
    public Page<PatrolPointVO> findPatrolPointPage(PatrolPointQuery query) {
        Page<PatrolPointVO> page = new Page<>(query.getPage(), query.getSize());
        List<PatrolPointVO> list = patrolPointMapper.findPatrolPointPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePatrolPoint(PatrolPointDTO patrolPointDTO) {
        PatrolPoint patrolPoint = new PatrolPoint();

        BeanUtils.copyProperties(patrolPointDTO, patrolPoint);

        this.saveOrUpdate(patrolPoint);

        return patrolPoint.getId();
    }

    @Override
    public PatrolPointVO getPatrolPointById(Long id) {
        PatrolPoint patrolPoint = this.getById(id);
        if (CommonFuntions.isEmptyObject(patrolPoint)) {
            throw new BizException("查询失败，巡查点位表不存在");
        }

        PatrolPointVO patrolPointVO = new PatrolPointVO();
        BeanUtils.copyProperties(patrolPoint, patrolPointVO);
        return patrolPointVO;
    }
}
