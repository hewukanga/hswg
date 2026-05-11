package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.OilCardRecord;
import com.jiumai.base.biz.query.OilCardRecordQuery;
import com.jiumai.base.biz.vo.OilCardRecordVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 油卡上报记录表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface OilCardRecordMapper extends BaseMapper<OilCardRecord> {

    /**
     * 分页查询油卡上报记录表
     * @param page
     * @param query
     * @return
     */
    List<OilCardRecordVO> findOilCardRecordPage(Page<OilCardRecordVO> page, @Param("query") OilCardRecordQuery query);
}
