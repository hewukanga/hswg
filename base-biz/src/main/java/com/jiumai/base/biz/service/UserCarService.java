package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.UserCarDTO;
import com.jiumai.base.biz.entity.UserCar;
import com.jiumai.base.biz.query.UserCarQuery;
import com.jiumai.base.biz.vo.UserCarVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户车辆管理 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface UserCarService extends IService<UserCar> {

    /**
     * 分页查询用户车辆管理
     * @param query
     * @return
     */
    Page<UserCarVO> findUserCarPage(UserCarQuery query);

    /**
     * 添加或更新用户车辆管理
     * @param userCarDTO
     * @return
     */
    Long saveOrUpdateUserCar(UserCarDTO userCarDTO);

    /**
     * 通过id查询成功用户车辆管理详情
     * @param id
     * @return
     */
    UserCarVO getUserCarById(Long id);
}
