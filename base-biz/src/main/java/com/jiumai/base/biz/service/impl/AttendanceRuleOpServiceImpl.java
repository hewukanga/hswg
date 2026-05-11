package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.AttendanceRuleOpDTO;
import com.jiumai.base.biz.entity.AttendanceRuleOp;
import com.jiumai.base.biz.mapper.AttendanceRuleOpMapper;
import com.jiumai.base.biz.query.AttendanceRuleOpQuery;
import com.jiumai.base.biz.service.AttendanceRuleOpService;
import com.jiumai.base.biz.vo.AttendanceRuleOpVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 考勤规则人员表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class AttendanceRuleOpServiceImpl extends ServiceImpl<AttendanceRuleOpMapper, AttendanceRuleOp> implements AttendanceRuleOpService {

    @Resource
    private AttendanceRuleOpMapper attendanceRuleOpMapper;

    @Override
    public Page<AttendanceRuleOpVO> findAttendanceRuleOpPage(AttendanceRuleOpQuery query) {
        Page<AttendanceRuleOpVO> page = new Page<>(query.getPage(), query.getSize());
        List<AttendanceRuleOpVO> list = attendanceRuleOpMapper.findAttendanceRuleOpPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateAttendanceRuleOp(AttendanceRuleOpDTO attendanceRuleOpDTO) {
        AttendanceRuleOp attendanceRuleOp = new AttendanceRuleOp();

        BeanUtils.copyProperties(attendanceRuleOpDTO, attendanceRuleOp);

        this.saveOrUpdate(attendanceRuleOp);

        return attendanceRuleOp.getId();
    }

    @Override
    public AttendanceRuleOpVO getAttendanceRuleOpById(Long id) {
        AttendanceRuleOp attendanceRuleOp = this.getById(id);
        if (CommonFuntions.isEmptyObject(attendanceRuleOp)) {
            throw new BizException("查询失败，考勤规则人员表不存在");
        }

        AttendanceRuleOpVO attendanceRuleOpVO = new AttendanceRuleOpVO();
        BeanUtils.copyProperties(attendanceRuleOp, attendanceRuleOpVO);
        return attendanceRuleOpVO;
    }
}
