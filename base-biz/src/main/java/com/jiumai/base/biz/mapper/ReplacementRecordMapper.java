package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.ReplacementRecord;
import com.jiumai.base.biz.query.ReplacementRecordQuery;
import com.jiumai.base.biz.vo.ReplacementRecordVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * pm_replacement_record Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ReplacementRecordMapper extends BaseMapper<ReplacementRecord> {

    /**
     * 分页查询pm_replacement_record
     * @param page
     * @param query
     * @return
     */
    List<ReplacementRecordVO> findReplacementRecordPage(Page<ReplacementRecordVO> page, @Param("query") ReplacementRecordQuery query);
}
