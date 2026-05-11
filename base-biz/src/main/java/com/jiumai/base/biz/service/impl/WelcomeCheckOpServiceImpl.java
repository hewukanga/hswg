package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.WelcomeCheckOpDTO;
import com.jiumai.base.biz.entity.WelcomeCheckOp;
import com.jiumai.base.biz.mapper.WelcomeCheckOpMapper;
import com.jiumai.base.biz.query.WelcomeCheckOpQuery;
import com.jiumai.base.biz.service.WelcomeCheckOpService;
import com.jiumai.base.biz.vo.WelcomeCheckOpVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 迎检通知人员表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class WelcomeCheckOpServiceImpl extends ServiceImpl<WelcomeCheckOpMapper, WelcomeCheckOp> implements WelcomeCheckOpService {

    @Resource
    private WelcomeCheckOpMapper welcomeCheckOpMapper;

    @Override
    public Page<WelcomeCheckOpVO> findWelcomeCheckOpPage(WelcomeCheckOpQuery query) {
        Page<WelcomeCheckOpVO> page = new Page<>(query.getPage(), query.getSize());
        List<WelcomeCheckOpVO> list = welcomeCheckOpMapper.findWelcomeCheckOpPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateWelcomeCheckOp(WelcomeCheckOpDTO welcomeCheckOpDTO) {
        WelcomeCheckOp welcomeCheckOp = new WelcomeCheckOp();

        BeanUtils.copyProperties(welcomeCheckOpDTO, welcomeCheckOp);

        this.saveOrUpdate(welcomeCheckOp);

        return welcomeCheckOp.getId();
    }

    @Override
    public WelcomeCheckOpVO getWelcomeCheckOpById(Long id) {
        WelcomeCheckOp welcomeCheckOp = this.getById(id);
        if (CommonFuntions.isEmptyObject(welcomeCheckOp)) {
            throw new BizException("查询失败，迎检通知人员表不存在");
        }

        WelcomeCheckOpVO welcomeCheckOpVO = new WelcomeCheckOpVO();
        BeanUtils.copyProperties(welcomeCheckOp, welcomeCheckOpVO);
        return welcomeCheckOpVO;
    }
}
