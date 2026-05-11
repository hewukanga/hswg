package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PropertyDeviceRecord;
import com.jiumai.base.biz.query.PropertyDeviceRecordQuery;
import com.jiumai.base.biz.vo.PropertyDeviceRecordVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 物业设备领用记录 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PropertyDeviceRecordMapper extends BaseMapper<PropertyDeviceRecord> {

    /**
     * 分页查询物业设备领用记录
     * @param page
     * @param query
     * @return
     */
    List<PropertyDeviceRecordVO> findPropertyDeviceRecordPage(Page<PropertyDeviceRecordVO> page, @Param("query") PropertyDeviceRecordQuery query);
}
