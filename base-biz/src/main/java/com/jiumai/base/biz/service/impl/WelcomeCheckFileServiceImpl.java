package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.WelcomeCheckFileDTO;
import com.jiumai.base.biz.entity.WelcomeCheckFile;
import com.jiumai.base.biz.mapper.WelcomeCheckFileMapper;
import com.jiumai.base.biz.query.WelcomeCheckFileQuery;
import com.jiumai.base.biz.service.WelcomeCheckFileService;
import com.jiumai.base.biz.vo.WelcomeCheckFileVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 迎检附件表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class WelcomeCheckFileServiceImpl extends ServiceImpl<WelcomeCheckFileMapper, WelcomeCheckFile> implements WelcomeCheckFileService {

    @Resource
    private WelcomeCheckFileMapper welcomeCheckFileMapper;

    @Override
    public Page<WelcomeCheckFileVO> findWelcomeCheckFilePage(WelcomeCheckFileQuery query) {
        Page<WelcomeCheckFileVO> page = new Page<>(query.getPage(), query.getSize());
        List<WelcomeCheckFileVO> list = welcomeCheckFileMapper.findWelcomeCheckFilePage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateWelcomeCheckFile(WelcomeCheckFileDTO welcomeCheckFileDTO) {
        WelcomeCheckFile welcomeCheckFile = new WelcomeCheckFile();

        BeanUtils.copyProperties(welcomeCheckFileDTO, welcomeCheckFile);

        this.saveOrUpdate(welcomeCheckFile);

        return welcomeCheckFile.getId();
    }

    @Override
    public WelcomeCheckFileVO getWelcomeCheckFileById(Long id) {
        WelcomeCheckFile welcomeCheckFile = this.getById(id);
        if (CommonFuntions.isEmptyObject(welcomeCheckFile)) {
            throw new BizException("查询失败，迎检附件表不存在");
        }

        WelcomeCheckFileVO welcomeCheckFileVO = new WelcomeCheckFileVO();
        BeanUtils.copyProperties(welcomeCheckFile, welcomeCheckFileVO);
        return welcomeCheckFileVO;
    }
}
