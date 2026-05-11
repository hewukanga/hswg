package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.FamilyCardInfo;
import com.jiumai.base.biz.query.FamilyCardInfoQuery;
import com.jiumai.base.biz.vo.FamilyCardInfoVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 亲情卡数据 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface FamilyCardInfoMapper extends BaseMapper<FamilyCardInfo> {

    /**
     * 分页查询亲情卡数据
     * @param page
     * @param query
     * @return
     */
    List<FamilyCardInfoVO> findFamilyCardInfoPage(Page<FamilyCardInfoVO> page, @Param("query") FamilyCardInfoQuery query);
}
