package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.IntegralConfigDTO;
import com.jiumai.base.biz.entity.IntegralConfig;
import com.jiumai.base.biz.mapper.IntegralConfigMapper;
import com.jiumai.base.biz.query.IntegralConfigQuery;
import com.jiumai.base.biz.service.IntegralConfigService;
import com.jiumai.base.biz.vo.IntegralConfigVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 积分配置表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class IntegralConfigServiceImpl extends ServiceImpl<IntegralConfigMapper, IntegralConfig> implements IntegralConfigService {

    @Resource
    private IntegralConfigMapper integralConfigMapper;

    @Override
    public Page<IntegralConfigVO> findIntegralConfigPage(IntegralConfigQuery query) {
        Page<IntegralConfigVO> page = new Page<>(query.getPage(), query.getSize());
        List<IntegralConfigVO> list = integralConfigMapper.findIntegralConfigPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateIntegralConfig(IntegralConfigDTO integralConfigDTO) {
        IntegralConfig integralConfig = new IntegralConfig();

        BeanUtils.copyProperties(integralConfigDTO, integralConfig);

        this.saveOrUpdate(integralConfig);

        return integralConfig.getId();
    }

    @Override
    public IntegralConfigVO getIntegralConfigById(Long id) {
        IntegralConfig integralConfig = this.getById(id);
        if (CommonFuntions.isEmptyObject(integralConfig)) {
            throw new BizException("查询失败，积分配置表不存在");
        }

        IntegralConfigVO integralConfigVO = new IntegralConfigVO();
        BeanUtils.copyProperties(integralConfig, integralConfigVO);
        return integralConfigVO;
    }
}
