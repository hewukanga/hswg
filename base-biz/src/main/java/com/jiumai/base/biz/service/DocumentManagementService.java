package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.DocumentManagementDTO;
import com.jiumai.base.biz.entity.DocumentManagement;
import com.jiumai.base.biz.query.DocumentManagementQuery;
import com.jiumai.base.biz.vo.DocumentManagementVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文档管理 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface DocumentManagementService extends IService<DocumentManagement> {

    /**
     * 分页查询文档管理
     * @param query
     * @return
     */
    Page<DocumentManagementVO> findDocumentManagementPage(DocumentManagementQuery query);

    /**
     * 添加或更新文档管理
     * @param documentManagementDTO
     * @return
     */
    Long saveOrUpdateDocumentManagement(DocumentManagementDTO documentManagementDTO);

    /**
     * 通过id查询成功文档管理详情
     * @param id
     * @return
     */
    DocumentManagementVO getDocumentManagementById(Long id);
}
