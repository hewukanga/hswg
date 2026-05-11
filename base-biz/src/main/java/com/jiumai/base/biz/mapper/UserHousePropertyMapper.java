package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.UserHouseProperty;
import com.jiumai.base.biz.query.UserHousePropertyQuery;
import com.jiumai.base.biz.vo.UserHousePropertyVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 业主关联房产表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface UserHousePropertyMapper extends BaseMapper<UserHouseProperty> {

    /**
     * 分页查询业主关联房产表
     * @param page
     * @param query
     * @return
     */
    List<UserHousePropertyVO> findUserHousePropertyPage(Page<UserHousePropertyVO> page, @Param("query") UserHousePropertyQuery query);
}
