package com.jiumai.base.sm.service;

import com.jiumai.base.sm.dto.InterfaceConfDTO;
import com.jiumai.base.sm.dto.InterfaceConfTreeDTO;
import com.jiumai.base.sm.query.InterfaceConfQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiumai.base.sm.entity.InterfaceConf;
import com.baomidou.mybatisplus.extension.service.IService;
import jdk.internal.dynalink.linker.LinkerServices;

import java.util.List;

/**
 * <p>
 * 接口管理 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2023-08-21
 */
public interface InterfaceConfService extends IService<InterfaceConf> {


    /**
     * 分页查询接口管理
     * @param query
     * @return
     */
    Page<InterfaceConfDTO> findInterfaceConfPage(InterfaceConfQuery query);

    /**
     * 添加或更新接口管理
     * @param interfaceConfDTO
     * @return
     */
    Long saveOrUpdateInterfaceConf(InterfaceConfDTO interfaceConfDTO);

    /**
     * 通过id查询接口管理详情
     * @param id
     * @return
     */
    InterfaceConfDTO getInterfaceConfById(Long id);

    /**
     * 根据ID删除接口管理
     * @param id
     */
    void removeInterfaceConfById(Long id);

    /**
     * 获取接口配置树形结构
     * @return
     */
    List<InterfaceConfTreeDTO> findInterfaceConfTree();
}
