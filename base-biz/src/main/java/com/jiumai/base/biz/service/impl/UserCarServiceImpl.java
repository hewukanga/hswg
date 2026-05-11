package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.UserCarDTO;
import com.jiumai.base.biz.entity.UserCar;
import com.jiumai.base.biz.mapper.UserCarMapper;
import com.jiumai.base.biz.query.UserCarQuery;
import com.jiumai.base.biz.service.UserCarService;
import com.jiumai.base.biz.vo.UserCarVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户车辆管理 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class UserCarServiceImpl extends ServiceImpl<UserCarMapper, UserCar> implements UserCarService {

    @Resource
    private UserCarMapper userCarMapper;

    @Override
    public Page<UserCarVO> findUserCarPage(UserCarQuery query) {
        Page<UserCarVO> page = new Page<>(query.getPage(), query.getSize());
        List<UserCarVO> list = userCarMapper.findUserCarPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateUserCar(UserCarDTO userCarDTO) {
        UserCar userCar = new UserCar();

        BeanUtils.copyProperties(userCarDTO, userCar);

        this.saveOrUpdate(userCar);

        return userCar.getId();
    }

    @Override
    public UserCarVO getUserCarById(Long id) {
        UserCar userCar = this.getById(id);
        if (CommonFuntions.isEmptyObject(userCar)) {
            throw new BizException("查询失败，用户车辆管理不存在");
        }

        UserCarVO userCarVO = new UserCarVO();
        BeanUtils.copyProperties(userCar, userCarVO);
        return userCarVO;
    }
}
