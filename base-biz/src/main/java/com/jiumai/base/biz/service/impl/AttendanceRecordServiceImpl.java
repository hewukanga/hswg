package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.AttendanceRecordDTO;
import com.jiumai.base.biz.entity.AttendanceRecord;
import com.jiumai.base.biz.mapper.AttendanceRecordMapper;
import com.jiumai.base.biz.query.AttendanceRecordQuery;
import com.jiumai.base.biz.service.AttendanceRecordService;
import com.jiumai.base.biz.vo.AttendanceRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 考勤记录表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class AttendanceRecordServiceImpl extends ServiceImpl<AttendanceRecordMapper, AttendanceRecord> implements AttendanceRecordService {

    @Resource
    private AttendanceRecordMapper attendanceRecordMapper;

    @Override
    public Page<AttendanceRecordVO> findAttendanceRecordPage(AttendanceRecordQuery query) {
        Page<AttendanceRecordVO> page = new Page<>(query.getPage(), query.getSize());
        List<AttendanceRecordVO> list = attendanceRecordMapper.findAttendanceRecordPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateAttendanceRecord(AttendanceRecordDTO attendanceRecordDTO) {
        AttendanceRecord attendanceRecord = new AttendanceRecord();

        BeanUtils.copyProperties(attendanceRecordDTO, attendanceRecord);

        this.saveOrUpdate(attendanceRecord);

        return attendanceRecord.getId();
    }

    @Override
    public AttendanceRecordVO getAttendanceRecordById(Long id) {
        AttendanceRecord attendanceRecord = this.getById(id);
        if (CommonFuntions.isEmptyObject(attendanceRecord)) {
            throw new BizException("查询失败，考勤记录表不存在");
        }

        AttendanceRecordVO attendanceRecordVO = new AttendanceRecordVO();
        BeanUtils.copyProperties(attendanceRecord, attendanceRecordVO);
        return attendanceRecordVO;
    }
}
