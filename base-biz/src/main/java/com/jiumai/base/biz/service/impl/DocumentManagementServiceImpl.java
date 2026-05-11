package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.DocumentManagementDTO;
import com.jiumai.base.biz.entity.DocumentManagement;
import com.jiumai.base.biz.mapper.DocumentManagementMapper;
import com.jiumai.base.biz.query.DocumentManagementQuery;
import com.jiumai.base.biz.service.DocumentManagementService;
import com.jiumai.base.biz.vo.DocumentManagementVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 文档管理 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class DocumentManagementServiceImpl extends ServiceImpl<DocumentManagementMapper, DocumentManagement> implements DocumentManagementService {

    @Resource
    private DocumentManagementMapper documentManagementMapper;

    @Override
    public Page<DocumentManagementVO> findDocumentManagementPage(DocumentManagementQuery query) {
        Page<DocumentManagementVO> page = new Page<>(query.getPage(), query.getSize());
        List<DocumentManagementVO> list = documentManagementMapper.findDocumentManagementPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateDocumentManagement(DocumentManagementDTO documentManagementDTO) {
        DocumentManagement documentManagement = new DocumentManagement();

        BeanUtils.copyProperties(documentManagementDTO, documentManagement);

        this.saveOrUpdate(documentManagement);

        return documentManagement.getId();
    }

    @Override
    public DocumentManagementVO getDocumentManagementById(Long id) {
        DocumentManagement documentManagement = this.getById(id);
        if (CommonFuntions.isEmptyObject(documentManagement)) {
            throw new BizException("查询失败，文档管理不存在");
        }

        DocumentManagementVO documentManagementVO = new DocumentManagementVO();
        BeanUtils.copyProperties(documentManagement, documentManagementVO);
        return documentManagementVO;
    }
}
