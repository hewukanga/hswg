package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.WelcomeCheckDTO;
import com.jiumai.base.biz.entity.WelcomeCheck;
import com.jiumai.base.biz.mapper.WelcomeCheckMapper;
import com.jiumai.base.biz.query.WelcomeCheckQuery;
import com.jiumai.base.biz.service.WelcomeCheckService;
import com.jiumai.base.biz.vo.WelcomeCheckVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 迎检表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class WelcomeCheckServiceImpl extends ServiceImpl<WelcomeCheckMapper, WelcomeCheck> implements WelcomeCheckService {

    @Resource
    private WelcomeCheckMapper welcomeCheckMapper;

    @Override
    public Page<WelcomeCheckVO> findWelcomeCheckPage(WelcomeCheckQuery query) {
        Page<WelcomeCheckVO> page = new Page<>(query.getPage(), query.getSize());
        List<WelcomeCheckVO> list = welcomeCheckMapper.findWelcomeCheckPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateWelcomeCheck(WelcomeCheckDTO welcomeCheckDTO) {
        WelcomeCheck welcomeCheck = new WelcomeCheck();

        BeanUtils.copyProperties(welcomeCheckDTO, welcomeCheck);

        this.saveOrUpdate(welcomeCheck);

        return welcomeCheck.getId();
    }

    @Override
    public WelcomeCheckVO getWelcomeCheckById(Long id) {
        WelcomeCheck welcomeCheck = this.getById(id);
        if (CommonFuntions.isEmptyObject(welcomeCheck)) {
            throw new BizException("查询失败，迎检表不存在");
        }

        WelcomeCheckVO welcomeCheckVO = new WelcomeCheckVO();
        BeanUtils.copyProperties(welcomeCheck, welcomeCheckVO);
        return welcomeCheckVO;
    }
}
