package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.UserHousePropertyDTO;
import com.jiumai.base.biz.entity.UserHouseProperty;
import com.jiumai.base.biz.query.UserHousePropertyQuery;
import com.jiumai.base.biz.vo.UserHousePropertyVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 业主关联房产表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface UserHousePropertyService extends IService<UserHouseProperty> {

    /**
     * 分页查询业主关联房产表
     * @param query
     * @return
     */
    Page<UserHousePropertyVO> findUserHousePropertyPage(UserHousePropertyQuery query);

    /**
     * 添加或更新业主关联房产表
     * @param userHousePropertyDTO
     * @return
     */
    Long saveOrUpdateUserHouseProperty(UserHousePropertyDTO userHousePropertyDTO);

    /**
     * 通过id查询成功业主关联房产表详情
     * @param id
     * @return
     */
    UserHousePropertyVO getUserHousePropertyById(Long id);
}
