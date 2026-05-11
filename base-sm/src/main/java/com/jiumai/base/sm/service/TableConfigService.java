package com.jiumai.base.sm.service;

import com.jiumai.base.sm.dto.TableConfigDTO;
import com.jiumai.base.sm.entity.TableConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mysql gen
 * @since 2023-02-23
 */
public interface TableConfigService extends IService<TableConfig> {

    /**
     * 保存个性化配置
     * @param tableConfigDTO
     * @param operatorId
     */
    void saveTableConfig(TableConfigDTO tableConfigDTO, Long operatorId);

}
