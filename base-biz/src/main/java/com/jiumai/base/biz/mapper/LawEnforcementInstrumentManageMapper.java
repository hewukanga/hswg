package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.LawEnforcementInstrumentManage;
import com.jiumai.base.biz.query.LawEnforcementInstrumentManageQuery;
import com.jiumai.base.biz.vo.LawEnforcementInstrumentManageVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 执法仪管理 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface LawEnforcementInstrumentManageMapper extends BaseMapper<LawEnforcementInstrumentManage> {

    /**
     * 分页查询执法仪管理
     * @param page
     * @param query
     * @return
     */
    List<LawEnforcementInstrumentManageVO> findLawEnforcementInstrumentManagePage(Page<LawEnforcementInstrumentManageVO> page, @Param("query") LawEnforcementInstrumentManageQuery query);
}
