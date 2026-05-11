package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.OilCard;
import com.jiumai.base.biz.query.OilCardQuery;
import com.jiumai.base.biz.vo.OilCardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 油卡表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface OilCardMapper extends BaseMapper<OilCard> {

    /**
     * 分页查询油卡表
     * @param page
     * @param query
     * @return
     */
    List<OilCardVO> findOilCardPage(Page<OilCardVO> page, @Param("query") OilCardQuery query);
}
