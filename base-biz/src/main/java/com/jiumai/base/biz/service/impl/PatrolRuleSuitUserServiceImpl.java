package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PatrolRuleSuitUserDTO;
import com.jiumai.base.biz.entity.PatrolRuleSuitUser;
import com.jiumai.base.biz.mapper.PatrolRuleSuitUserMapper;
import com.jiumai.base.biz.query.PatrolRuleSuitUserQuery;
import com.jiumai.base.biz.service.PatrolRuleSuitUserService;
import com.jiumai.base.biz.vo.PatrolRuleSuitUserVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 巡查单规则适用人员表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PatrolRuleSuitUserServiceImpl extends ServiceImpl<PatrolRuleSuitUserMapper, PatrolRuleSuitUser> implements PatrolRuleSuitUserService {

    @Resource
    private PatrolRuleSuitUserMapper patrolRuleSuitUserMapper;

    @Override
    public Page<PatrolRuleSuitUserVO> findPatrolRuleSuitUserPage(PatrolRuleSuitUserQuery query) {
        Page<PatrolRuleSuitUserVO> page = new Page<>(query.getPage(), query.getSize());
        List<PatrolRuleSuitUserVO> list = patrolRuleSuitUserMapper.findPatrolRuleSuitUserPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePatrolRuleSuitUser(PatrolRuleSuitUserDTO patrolRuleSuitUserDTO) {
        PatrolRuleSuitUser patrolRuleSuitUser = new PatrolRuleSuitUser();

        BeanUtils.copyProperties(patrolRuleSuitUserDTO, patrolRuleSuitUser);

        this.saveOrUpdate(patrolRuleSuitUser);

        return patrolRuleSuitUser.getId();
    }

    @Override
    public PatrolRuleSuitUserVO getPatrolRuleSuitUserById(Long id) {
        PatrolRuleSuitUser patrolRuleSuitUser = this.getById(id);
        if (CommonFuntions.isEmptyObject(patrolRuleSuitUser)) {
            throw new BizException("查询失败，巡查单规则适用人员表不存在");
        }

        PatrolRuleSuitUserVO patrolRuleSuitUserVO = new PatrolRuleSuitUserVO();
        BeanUtils.copyProperties(patrolRuleSuitUser, patrolRuleSuitUserVO);
        return patrolRuleSuitUserVO;
    }
}
