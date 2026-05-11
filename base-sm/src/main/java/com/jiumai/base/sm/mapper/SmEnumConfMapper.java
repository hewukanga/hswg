package com.jiumai.base.sm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jiumai.base.sm.dto.AreaDTO;
import com.jiumai.base.sm.dto.EnumConfDTO;
import com.jiumai.base.sm.entity.SmEnumConf;
import com.jiumai.base.sm.query.AreaQuery;
import com.jiumai.base.sm.query.EnumQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 枚举 Mapper 接口
 * </p>
 *
 * @author mysql gen
 * @since 2023-01-09
 */
public interface SmEnumConfMapper extends BaseMapper<SmEnumConf> {
    /**
     * 查询当前最大的自定义枚举ID
     * @return
     */
    Long findMaxUserEnumId();

    /**
     * 分页查询枚举
     * @param page
     * @param query
     * @return
     */
    IPage<EnumConfDTO> findEnumPaging(@Param("page") IPage<EnumConfDTO> page, @Param("query") EnumQuery query);

}
