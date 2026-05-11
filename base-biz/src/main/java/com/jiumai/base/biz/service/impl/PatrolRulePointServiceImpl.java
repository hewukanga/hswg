package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PatrolRulePointDTO;
import com.jiumai.base.biz.entity.PatrolRulePoint;
import com.jiumai.base.biz.mapper.PatrolRulePointMapper;
import com.jiumai.base.biz.query.PatrolRulePointQuery;
import com.jiumai.base.biz.service.PatrolRulePointService;
import com.jiumai.base.biz.vo.PatrolRulePointVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 巡查单规则点位表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PatrolRulePointServiceImpl extends ServiceImpl<PatrolRulePointMapper, PatrolRulePoint> implements PatrolRulePointService {

    @Resource
    private PatrolRulePointMapper patrolRulePointMapper;

    @Override
    public Page<PatrolRulePointVO> findPatrolRulePointPage(PatrolRulePointQuery query) {
        Page<PatrolRulePointVO> page = new Page<>(query.getPage(), query.getSize());
        List<PatrolRulePointVO> list = patrolRulePointMapper.findPatrolRulePointPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePatrolRulePoint(PatrolRulePointDTO patrolRulePointDTO) {
        PatrolRulePoint patrolRulePoint = new PatrolRulePoint();

        BeanUtils.copyProperties(patrolRulePointDTO, patrolRulePoint);

        this.saveOrUpdate(patrolRulePoint);

        return patrolRulePoint.getId();
    }

    @Override
    public PatrolRulePointVO getPatrolRulePointById(Long id) {
        PatrolRulePoint patrolRulePoint = this.getById(id);
        if (CommonFuntions.isEmptyObject(patrolRulePoint)) {
            throw new BizException("查询失败，巡查单规则点位表不存在");
        }

        PatrolRulePointVO patrolRulePointVO = new PatrolRulePointVO();
        BeanUtils.copyProperties(patrolRulePoint, patrolRulePointVO);
        return patrolRulePointVO;
    }
}
