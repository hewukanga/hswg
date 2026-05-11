package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.AttendanceRuleDTO;
import com.jiumai.base.biz.entity.AttendanceRule;
import com.jiumai.base.biz.mapper.AttendanceRuleMapper;
import com.jiumai.base.biz.query.AttendanceRuleQuery;
import com.jiumai.base.biz.service.AttendanceRuleService;
import com.jiumai.base.biz.vo.AttendanceRuleVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 考勤规则表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class AttendanceRuleServiceImpl extends ServiceImpl<AttendanceRuleMapper, AttendanceRule> implements AttendanceRuleService {

    @Resource
    private AttendanceRuleMapper attendanceRuleMapper;

    @Override
    public Page<AttendanceRuleVO> findAttendanceRulePage(AttendanceRuleQuery query) {
        Page<AttendanceRuleVO> page = new Page<>(query.getPage(), query.getSize());
        List<AttendanceRuleVO> list = attendanceRuleMapper.findAttendanceRulePage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateAttendanceRule(AttendanceRuleDTO attendanceRuleDTO) {
        AttendanceRule attendanceRule = new AttendanceRule();

        BeanUtils.copyProperties(attendanceRuleDTO, attendanceRule);

        this.saveOrUpdate(attendanceRule);

        return attendanceRule.getId();
    }

    @Override
    public AttendanceRuleVO getAttendanceRuleById(Long id) {
        AttendanceRule attendanceRule = this.getById(id);
        if (CommonFuntions.isEmptyObject(attendanceRule)) {
            throw new BizException("查询失败，考勤规则表不存在");
        }

        AttendanceRuleVO attendanceRuleVO = new AttendanceRuleVO();
        BeanUtils.copyProperties(attendanceRule, attendanceRuleVO);
        return attendanceRuleVO;
    }
}
