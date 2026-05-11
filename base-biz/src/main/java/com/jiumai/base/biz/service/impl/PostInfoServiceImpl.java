package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PostInfoDTO;
import com.jiumai.base.biz.entity.PostInfo;
import com.jiumai.base.biz.mapper.PostInfoMapper;
import com.jiumai.base.biz.query.PostInfoQuery;
import com.jiumai.base.biz.service.PostInfoService;
import com.jiumai.base.biz.vo.PostInfoVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 岗位信息表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PostInfoServiceImpl extends ServiceImpl<PostInfoMapper, PostInfo> implements PostInfoService {

    @Resource
    private PostInfoMapper postInfoMapper;

    @Override
    public Page<PostInfoVO> findPostInfoPage(PostInfoQuery query) {
        Page<PostInfoVO> page = new Page<>(query.getPage(), query.getSize());
        List<PostInfoVO> list = postInfoMapper.findPostInfoPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePostInfo(PostInfoDTO postInfoDTO) {
        PostInfo postInfo = new PostInfo();

        BeanUtils.copyProperties(postInfoDTO, postInfo);

        this.saveOrUpdate(postInfo);

        return postInfo.getId();
    }

    @Override
    public PostInfoVO getPostInfoById(Long id) {
        PostInfo postInfo = this.getById(id);
        if (CommonFuntions.isEmptyObject(postInfo)) {
            throw new BizException("查询失败，岗位信息表不存在");
        }

        PostInfoVO postInfoVO = new PostInfoVO();
        BeanUtils.copyProperties(postInfo, postInfoVO);
        return postInfoVO;
    }
}
