package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.UserHousePropertyDTO;
import com.jiumai.base.biz.entity.UserHouseProperty;
import com.jiumai.base.biz.mapper.UserHousePropertyMapper;
import com.jiumai.base.biz.query.UserHousePropertyQuery;
import com.jiumai.base.biz.service.UserHousePropertyService;
import com.jiumai.base.biz.vo.UserHousePropertyVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 业主关联房产表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class UserHousePropertyServiceImpl extends ServiceImpl<UserHousePropertyMapper, UserHouseProperty> implements UserHousePropertyService {

    @Resource
    private UserHousePropertyMapper userHousePropertyMapper;

    @Override
    public Page<UserHousePropertyVO> findUserHousePropertyPage(UserHousePropertyQuery query) {
        Page<UserHousePropertyVO> page = new Page<>(query.getPage(), query.getSize());
        List<UserHousePropertyVO> list = userHousePropertyMapper.findUserHousePropertyPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateUserHouseProperty(UserHousePropertyDTO userHousePropertyDTO) {
        UserHouseProperty userHouseProperty = new UserHouseProperty();

        BeanUtils.copyProperties(userHousePropertyDTO, userHouseProperty);

        this.saveOrUpdate(userHouseProperty);

        return userHouseProperty.getId();
    }

    @Override
    public UserHousePropertyVO getUserHousePropertyById(Long id) {
        UserHouseProperty userHouseProperty = this.getById(id);
        if (CommonFuntions.isEmptyObject(userHouseProperty)) {
            throw new BizException("查询失败，业主关联房产表不存在");
        }

        UserHousePropertyVO userHousePropertyVO = new UserHousePropertyVO();
        BeanUtils.copyProperties(userHouseProperty, userHousePropertyVO);
        return userHousePropertyVO;
    }
}
