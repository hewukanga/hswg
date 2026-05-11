package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PictureDocumentFileDTO;
import com.jiumai.base.biz.entity.PictureDocumentFile;
import com.jiumai.base.biz.mapper.PictureDocumentFileMapper;
import com.jiumai.base.biz.query.PictureDocumentFileQuery;
import com.jiumai.base.biz.service.PictureDocumentFileService;
import com.jiumai.base.biz.vo.PictureDocumentFileVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 迎检附件表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PictureDocumentFileServiceImpl extends ServiceImpl<PictureDocumentFileMapper, PictureDocumentFile> implements PictureDocumentFileService {

    @Resource
    private PictureDocumentFileMapper pictureDocumentFileMapper;

    @Override
    public Page<PictureDocumentFileVO> findPictureDocumentFilePage(PictureDocumentFileQuery query) {
        Page<PictureDocumentFileVO> page = new Page<>(query.getPage(), query.getSize());
        List<PictureDocumentFileVO> list = pictureDocumentFileMapper.findPictureDocumentFilePage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePictureDocumentFile(PictureDocumentFileDTO pictureDocumentFileDTO) {
        PictureDocumentFile pictureDocumentFile = new PictureDocumentFile();

        BeanUtils.copyProperties(pictureDocumentFileDTO, pictureDocumentFile);

        this.saveOrUpdate(pictureDocumentFile);

        return pictureDocumentFile.getId();
    }

    @Override
    public PictureDocumentFileVO getPictureDocumentFileById(Long id) {
        PictureDocumentFile pictureDocumentFile = this.getById(id);
        if (CommonFuntions.isEmptyObject(pictureDocumentFile)) {
            throw new BizException("查询失败，迎检附件表不存在");
        }

        PictureDocumentFileVO pictureDocumentFileVO = new PictureDocumentFileVO();
        BeanUtils.copyProperties(pictureDocumentFile, pictureDocumentFileVO);
        return pictureDocumentFileVO;
    }
}
