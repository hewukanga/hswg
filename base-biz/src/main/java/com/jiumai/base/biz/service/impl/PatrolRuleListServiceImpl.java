package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PatrolRuleListDTO;
import com.jiumai.base.biz.entity.PatrolRuleList;
import com.jiumai.base.biz.mapper.PatrolRuleListMapper;
import com.jiumai.base.biz.query.PatrolRuleListQuery;
import com.jiumai.base.biz.service.PatrolRuleListService;
import com.jiumai.base.biz.vo.PatrolRuleListVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 巡查单规则表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PatrolRuleListServiceImpl extends ServiceImpl<PatrolRuleListMapper, PatrolRuleList> implements PatrolRuleListService {

    @Resource
    private PatrolRuleListMapper patrolRuleListMapper;

    @Override
    public Page<PatrolRuleListVO> findPatrolRuleListPage(PatrolRuleListQuery query) {
        Page<PatrolRuleListVO> page = new Page<>(query.getPage(), query.getSize());
        List<PatrolRuleListVO> list = patrolRuleListMapper.findPatrolRuleListPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePatrolRuleList(PatrolRuleListDTO patrolRuleListDTO) {
        PatrolRuleList patrolRuleList = new PatrolRuleList();

        BeanUtils.copyProperties(patrolRuleListDTO, patrolRuleList);

        this.saveOrUpdate(patrolRuleList);

        return patrolRuleList.getId();
    }

    @Override
    public PatrolRuleListVO getPatrolRuleListById(Long id) {
        PatrolRuleList patrolRuleList = this.getById(id);
        if (CommonFuntions.isEmptyObject(patrolRuleList)) {
            throw new BizException("查询失败，巡查单规则表不存在");
        }

        PatrolRuleListVO patrolRuleListVO = new PatrolRuleListVO();
        BeanUtils.copyProperties(patrolRuleList, patrolRuleListVO);
        return patrolRuleListVO;
    }
}
