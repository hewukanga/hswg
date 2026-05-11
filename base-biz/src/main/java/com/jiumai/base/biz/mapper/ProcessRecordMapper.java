package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.ProcessRecord;
import com.jiumai.base.biz.query.ProcessRecordQuery;
import com.jiumai.base.biz.vo.ProcessRecordVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 流程记录表(流程创建时生成全部记录) Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ProcessRecordMapper extends BaseMapper<ProcessRecord> {

    /**
     * 分页查询流程记录表(流程创建时生成全部记录)
     * @param page
     * @param query
     * @return
     */
    List<ProcessRecordVO> findProcessRecordPage(Page<ProcessRecordVO> page, @Param("query") ProcessRecordQuery query);
}
