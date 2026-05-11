package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.UserOpinionDTO;
import com.jiumai.base.biz.entity.UserOpinion;
import com.jiumai.base.biz.mapper.UserOpinionMapper;
import com.jiumai.base.biz.query.UserOpinionQuery;
import com.jiumai.base.biz.service.UserOpinionService;
import com.jiumai.base.biz.vo.UserOpinionVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 居民意见表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class UserOpinionServiceImpl extends ServiceImpl<UserOpinionMapper, UserOpinion> implements UserOpinionService {

    @Resource
    private UserOpinionMapper userOpinionMapper;

    @Override
    public Page<UserOpinionVO> findUserOpinionPage(UserOpinionQuery query) {
        Page<UserOpinionVO> page = new Page<>(query.getPage(), query.getSize());
        List<UserOpinionVO> list = userOpinionMapper.findUserOpinionPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateUserOpinion(UserOpinionDTO userOpinionDTO) {
        UserOpinion userOpinion = new UserOpinion();

        BeanUtils.copyProperties(userOpinionDTO, userOpinion);

        this.saveOrUpdate(userOpinion);

        return userOpinion.getId();
    }

    @Override
    public UserOpinionVO getUserOpinionById(Long id) {
        UserOpinion userOpinion = this.getById(id);
        if (CommonFuntions.isEmptyObject(userOpinion)) {
            throw new BizException("查询失败，居民意见表不存在");
        }

        UserOpinionVO userOpinionVO = new UserOpinionVO();
        BeanUtils.copyProperties(userOpinion, userOpinionVO);
        return userOpinionVO;
    }
}
