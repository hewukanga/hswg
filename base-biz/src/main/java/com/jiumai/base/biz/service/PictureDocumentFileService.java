package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PictureDocumentFileDTO;
import com.jiumai.base.biz.entity.PictureDocumentFile;
import com.jiumai.base.biz.query.PictureDocumentFileQuery;
import com.jiumai.base.biz.vo.PictureDocumentFileVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 迎检附件表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PictureDocumentFileService extends IService<PictureDocumentFile> {

    /**
     * 分页查询迎检附件表
     * @param query
     * @return
     */
    Page<PictureDocumentFileVO> findPictureDocumentFilePage(PictureDocumentFileQuery query);

    /**
     * 添加或更新迎检附件表
     * @param pictureDocumentFileDTO
     * @return
     */
    Long saveOrUpdatePictureDocumentFile(PictureDocumentFileDTO pictureDocumentFileDTO);

    /**
     * 通过id查询成功迎检附件表详情
     * @param id
     * @return
     */
    PictureDocumentFileVO getPictureDocumentFileById(Long id);
}
