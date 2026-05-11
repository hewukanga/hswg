package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PictureDocumentDTO;
import com.jiumai.base.biz.entity.PictureDocument;
import com.jiumai.base.biz.mapper.PictureDocumentMapper;
import com.jiumai.base.biz.query.PictureDocumentQuery;
import com.jiumai.base.biz.service.PictureDocumentService;
import com.jiumai.base.biz.vo.PictureDocumentVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 文档管理表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PictureDocumentServiceImpl extends ServiceImpl<PictureDocumentMapper, PictureDocument> implements PictureDocumentService {

    @Resource
    private PictureDocumentMapper pictureDocumentMapper;

    @Override
    public Page<PictureDocumentVO> findPictureDocumentPage(PictureDocumentQuery query) {
        Page<PictureDocumentVO> page = new Page<>(query.getPage(), query.getSize());
        List<PictureDocumentVO> list = pictureDocumentMapper.findPictureDocumentPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePictureDocument(PictureDocumentDTO pictureDocumentDTO) {
        PictureDocument pictureDocument = new PictureDocument();

        BeanUtils.copyProperties(pictureDocumentDTO, pictureDocument);

        this.saveOrUpdate(pictureDocument);

        return pictureDocument.getId();
    }

    @Override
    public PictureDocumentVO getPictureDocumentById(Long id) {
        PictureDocument pictureDocument = this.getById(id);
        if (CommonFuntions.isEmptyObject(pictureDocument)) {
            throw new BizException("查询失败，文档管理表不存在");
        }

        PictureDocumentVO pictureDocumentVO = new PictureDocumentVO();
        BeanUtils.copyProperties(pictureDocument, pictureDocumentVO);
        return pictureDocumentVO;
    }
}
