package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.User;
import com.jiumai.base.biz.query.UserQuery;
import com.jiumai.base.biz.vo.UserVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 业主表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 分页查询业主表
     * @param page
     * @param query
     * @return
     */
    List<UserVO> findUserPage(Page<UserVO> page, @Param("query") UserQuery query);
}
