package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.NewIntegralConfigDTO;
import com.jiumai.base.biz.entity.NewIntegralConfig;
import com.jiumai.base.biz.mapper.NewIntegralConfigMapper;
import com.jiumai.base.biz.query.NewIntegralConfigQuery;
import com.jiumai.base.biz.service.NewIntegralConfigService;
import com.jiumai.base.biz.vo.NewIntegralConfigVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 新积分配置表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class NewIntegralConfigServiceImpl extends ServiceImpl<NewIntegralConfigMapper, NewIntegralConfig> implements NewIntegralConfigService {

    @Resource
    private NewIntegralConfigMapper newIntegralConfigMapper;

    @Override
    public Page<NewIntegralConfigVO> findNewIntegralConfigPage(NewIntegralConfigQuery query) {
        Page<NewIntegralConfigVO> page = new Page<>(query.getPage(), query.getSize());
        List<NewIntegralConfigVO> list = newIntegralConfigMapper.findNewIntegralConfigPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateNewIntegralConfig(NewIntegralConfigDTO newIntegralConfigDTO) {
        NewIntegralConfig newIntegralConfig = new NewIntegralConfig();

        BeanUtils.copyProperties(newIntegralConfigDTO, newIntegralConfig);

        this.saveOrUpdate(newIntegralConfig);

        return newIntegralConfig.getId();
    }

    @Override
    public NewIntegralConfigVO getNewIntegralConfigById(Long id) {
        NewIntegralConfig newIntegralConfig = this.getById(id);
        if (CommonFuntions.isEmptyObject(newIntegralConfig)) {
            throw new BizException("查询失败，新积分配置表不存在");
        }

        NewIntegralConfigVO newIntegralConfigVO = new NewIntegralConfigVO();
        BeanUtils.copyProperties(newIntegralConfig, newIntegralConfigVO);
        return newIntegralConfigVO;
    }
}
