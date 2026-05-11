package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PictureDocumentDTO;
import com.jiumai.base.biz.entity.PictureDocument;
import com.jiumai.base.biz.query.PictureDocumentQuery;
import com.jiumai.base.biz.vo.PictureDocumentVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文档管理表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PictureDocumentService extends IService<PictureDocument> {

    /**
     * 分页查询文档管理表
     * @param query
     * @return
     */
    Page<PictureDocumentVO> findPictureDocumentPage(PictureDocumentQuery query);

    /**
     * 添加或更新文档管理表
     * @param pictureDocumentDTO
     * @return
     */
    Long saveOrUpdatePictureDocument(PictureDocumentDTO pictureDocumentDTO);

    /**
     * 通过id查询成功文档管理表详情
     * @param id
     * @return
     */
    PictureDocumentVO getPictureDocumentById(Long id);
}
