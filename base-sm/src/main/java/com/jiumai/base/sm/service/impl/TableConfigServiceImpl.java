package com.jiumai.base.sm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiumai.base.sm.dto.TableConfigDTO;
import com.jiumai.base.sm.entity.TableConfig;
import com.jiumai.base.sm.mapper.TableConfigMapper;
import com.jiumai.base.sm.service.TableConfigService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mysql gen
 * @since 2023-02-23
 */
@Service
public class TableConfigServiceImpl extends ServiceImpl<TableConfigMapper, TableConfig> implements TableConfigService {

    @Override
    public void saveTableConfig(TableConfigDTO tableConfigDTO, Long operatorId) {
        // 查询是否保存过
        LambdaQueryWrapper<TableConfig> wrapper = Wrappers.lambdaQuery(TableConfig.class)
                .eq(TableConfig::getOpId, operatorId)
                .eq(TableConfig::getTableCode, tableConfigDTO.getTableCode());
        TableConfig dbTableConfig = this.getOne(wrapper, false);

        TableConfig newConfig = BeanUtil.copyProperties(tableConfigDTO, TableConfig.class);
        newConfig.setOpId(operatorId);
        if (dbTableConfig != null) {
            newConfig.setId(dbTableConfig.getId());
        }
        boolean b = this.saveOrUpdate(newConfig);

    }
}
