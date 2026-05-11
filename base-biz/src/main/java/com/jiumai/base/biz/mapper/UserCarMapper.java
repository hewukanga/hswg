package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.UserCar;
import com.jiumai.base.biz.query.UserCarQuery;
import com.jiumai.base.biz.vo.UserCarVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户车辆管理 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface UserCarMapper extends BaseMapper<UserCar> {

    /**
     * 分页查询用户车辆管理
     * @param page
     * @param query
     * @return
     */
    List<UserCarVO> findUserCarPage(Page<UserCarVO> page, @Param("query") UserCarQuery query);
}
