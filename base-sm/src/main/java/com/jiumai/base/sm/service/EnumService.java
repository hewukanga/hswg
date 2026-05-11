package com.jiumai.base.sm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiumai.base.sm.dto.EnumConfDTO;
import com.jiumai.base.sm.entity.SmEnumConf;
import com.jiumai.base.sm.query.EnumQuery;
import java.util.List;

public interface EnumService extends IService<SmEnumConf> {

    /**
     * 更新系统内置枚举，先删除，插入，需要解决ID过大的问题。
     *
     * @param list
     */
    void batchUpdateSysEnums(List<SmEnumConf> list);

    /**
     * 更新enum
     */
    void saveOrUpdateUserEnums(List<SmEnumConf> configs) throws Exception;

    /**
     * 分页查询enum
     *
     * @param enumQuery
     * @return
     * @throws Exception
     */
    IPage<EnumConfDTO> findEnumPaging(EnumQuery enumQuery);

    /**
     * 根据枚举名称，获取该枚举下的所有枚举值
     *
     * @param enumCode
     * @return
     */
    List<SmEnumConf> findEnumConfByEnumCode(String enumCode) throws Exception;

    /**
     * 根据枚举编号和上级枚举值获取下级枚举值
     *
     * @param enumCode
     * @param parentValue
     * @return
     */
    List<SmEnumConf> findEnumConfByParent(String enumCode, String parentValue) throws Exception;

    /**
     * 判断枚举编码是否存在
     *
     * @param enumCode
     */
    Boolean checkEnumCodeIsUniquen(String enumCode) throws Exception;

    /**
     * 批量删除枚举
     *
     * @param enumIds
     * @throws Exception
     */
    void deleteBatchEnums(List<Long> enumIds) throws Exception;

}
