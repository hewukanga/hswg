package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.OvertimeProcessConfigDTO;
import com.jiumai.base.biz.entity.OvertimeProcessConfig;
import com.jiumai.base.biz.mapper.OvertimeProcessConfigMapper;
import com.jiumai.base.biz.query.OvertimeProcessConfigQuery;
import com.jiumai.base.biz.service.OvertimeProcessConfigService;
import com.jiumai.base.biz.vo.OvertimeProcessConfigVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 加班配置表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class OvertimeProcessConfigServiceImpl extends ServiceImpl<OvertimeProcessConfigMapper, OvertimeProcessConfig> implements OvertimeProcessConfigService {

    @Resource
    private OvertimeProcessConfigMapper overtimeProcessConfigMapper;

    @Override
    public Page<OvertimeProcessConfigVO> findOvertimeProcessConfigPage(OvertimeProcessConfigQuery query) {
        Page<OvertimeProcessConfigVO> page = new Page<>(query.getPage(), query.getSize());
        List<OvertimeProcessConfigVO> list = overtimeProcessConfigMapper.findOvertimeProcessConfigPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateOvertimeProcessConfig(OvertimeProcessConfigDTO overtimeProcessConfigDTO) {
        OvertimeProcessConfig overtimeProcessConfig = new OvertimeProcessConfig();

        BeanUtils.copyProperties(overtimeProcessConfigDTO, overtimeProcessConfig);

        this.saveOrUpdate(overtimeProcessConfig);

        return overtimeProcessConfig.getId();
    }

    @Override
    public OvertimeProcessConfigVO getOvertimeProcessConfigById(Long id) {
        OvertimeProcessConfig overtimeProcessConfig = this.getById(id);
        if (CommonFuntions.isEmptyObject(overtimeProcessConfig)) {
            throw new BizException("查询失败，加班配置表不存在");
        }

        OvertimeProcessConfigVO overtimeProcessConfigVO = new OvertimeProcessConfigVO();
        BeanUtils.copyProperties(overtimeProcessConfig, overtimeProcessConfigVO);
        return overtimeProcessConfigVO;
    }
}
