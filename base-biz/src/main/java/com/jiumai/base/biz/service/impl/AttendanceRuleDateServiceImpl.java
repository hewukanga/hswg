package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.AttendanceRuleDateDTO;
import com.jiumai.base.biz.entity.AttendanceRuleDate;
import com.jiumai.base.biz.mapper.AttendanceRuleDateMapper;
import com.jiumai.base.biz.query.AttendanceRuleDateQuery;
import com.jiumai.base.biz.service.AttendanceRuleDateService;
import com.jiumai.base.biz.vo.AttendanceRuleDateVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 考勤规则日期表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class AttendanceRuleDateServiceImpl extends ServiceImpl<AttendanceRuleDateMapper, AttendanceRuleDate> implements AttendanceRuleDateService {

    @Resource
    private AttendanceRuleDateMapper attendanceRuleDateMapper;

    @Override
    public Page<AttendanceRuleDateVO> findAttendanceRuleDatePage(AttendanceRuleDateQuery query) {
        Page<AttendanceRuleDateVO> page = new Page<>(query.getPage(), query.getSize());
        List<AttendanceRuleDateVO> list = attendanceRuleDateMapper.findAttendanceRuleDatePage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateAttendanceRuleDate(AttendanceRuleDateDTO attendanceRuleDateDTO) {
        AttendanceRuleDate attendanceRuleDate = new AttendanceRuleDate();

        BeanUtils.copyProperties(attendanceRuleDateDTO, attendanceRuleDate);

        this.saveOrUpdate(attendanceRuleDate);

        return attendanceRuleDate.getId();
    }

    @Override
    public AttendanceRuleDateVO getAttendanceRuleDateById(Long id) {
        AttendanceRuleDate attendanceRuleDate = this.getById(id);
        if (CommonFuntions.isEmptyObject(attendanceRuleDate)) {
            throw new BizException("查询失败，考勤规则日期表不存在");
        }

        AttendanceRuleDateVO attendanceRuleDateVO = new AttendanceRuleDateVO();
        BeanUtils.copyProperties(attendanceRuleDate, attendanceRuleDateVO);
        return attendanceRuleDateVO;
    }
}
