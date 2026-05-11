package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.UserDTO;
import com.jiumai.base.biz.entity.User;
import com.jiumai.base.biz.query.UserQuery;
import com.jiumai.base.biz.vo.UserVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 业主表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface UserService extends IService<User> {

    /**
     * 分页查询业主表
     * @param query
     * @return
     */
    Page<UserVO> findUserPage(UserQuery query);

    /**
     * 添加或更新业主表
     * @param userDTO
     * @return
     */
    Long saveOrUpdateUser(UserDTO userDTO);

    /**
     * 通过id查询成功业主表详情
     * @param id
     * @return
     */
    UserVO getUserById(Long id);
}
