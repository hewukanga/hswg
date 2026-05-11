package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.UserDTO;
import com.jiumai.base.biz.entity.User;
import com.jiumai.base.biz.mapper.UserMapper;
import com.jiumai.base.biz.query.UserQuery;
import com.jiumai.base.biz.service.UserService;
import com.jiumai.base.biz.vo.UserVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 业主表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Page<UserVO> findUserPage(UserQuery query) {
        Page<UserVO> page = new Page<>(query.getPage(), query.getSize());
        List<UserVO> list = userMapper.findUserPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateUser(UserDTO userDTO) {
        User user = new User();

        BeanUtils.copyProperties(userDTO, user);

        this.saveOrUpdate(user);

        return user.getId();
    }

    @Override
    public UserVO getUserById(Long id) {
        User user = this.getById(id);
        if (CommonFuntions.isEmptyObject(user)) {
            throw new BizException("查询失败，业主表不存在");
        }

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }
}
