package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.HelpDTO;
import com.jiumai.base.biz.entity.Help;
import com.jiumai.base.biz.mapper.HelpMapper;
import com.jiumai.base.biz.query.HelpQuery;
import com.jiumai.base.biz.service.HelpService;
import com.jiumai.base.biz.vo.HelpVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 帮扶 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class HelpServiceImpl extends ServiceImpl<HelpMapper, Help> implements HelpService {

    @Resource
    private HelpMapper helpMapper;

    @Override
    public Page<HelpVO> findHelpPage(HelpQuery query) {
        Page<HelpVO> page = new Page<>(query.getPage(), query.getSize());
        List<HelpVO> list = helpMapper.findHelpPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateHelp(HelpDTO helpDTO) {
        Help help = new Help();

        BeanUtils.copyProperties(helpDTO, help);

        this.saveOrUpdate(help);

        return help.getId();
    }

    @Override
    public HelpVO getHelpById(Long id) {
        Help help = this.getById(id);
        if (CommonFuntions.isEmptyObject(help)) {
            throw new BizException("查询失败，帮扶不存在");
        }

        HelpVO helpVO = new HelpVO();
        BeanUtils.copyProperties(help, helpVO);
        return helpVO;
    }
}
