package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PostPersonInfoDTO;
import com.jiumai.base.biz.entity.PostPersonInfo;
import com.jiumai.base.biz.mapper.PostPersonInfoMapper;
import com.jiumai.base.biz.query.PostPersonInfoQuery;
import com.jiumai.base.biz.service.PostPersonInfoService;
import com.jiumai.base.biz.vo.PostPersonInfoVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 岗位人员信息表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PostPersonInfoServiceImpl extends ServiceImpl<PostPersonInfoMapper, PostPersonInfo> implements PostPersonInfoService {

    @Resource
    private PostPersonInfoMapper postPersonInfoMapper;

    @Override
    public Page<PostPersonInfoVO> findPostPersonInfoPage(PostPersonInfoQuery query) {
        Page<PostPersonInfoVO> page = new Page<>(query.getPage(), query.getSize());
        List<PostPersonInfoVO> list = postPersonInfoMapper.findPostPersonInfoPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePostPersonInfo(PostPersonInfoDTO postPersonInfoDTO) {
        PostPersonInfo postPersonInfo = new PostPersonInfo();

        BeanUtils.copyProperties(postPersonInfoDTO, postPersonInfo);

        this.saveOrUpdate(postPersonInfo);

        return postPersonInfo.getId();
    }

    @Override
    public PostPersonInfoVO getPostPersonInfoById(Long id) {
        PostPersonInfo postPersonInfo = this.getById(id);
        if (CommonFuntions.isEmptyObject(postPersonInfo)) {
            throw new BizException("查询失败，岗位人员信息表不存在");
        }

        PostPersonInfoVO postPersonInfoVO = new PostPersonInfoVO();
        BeanUtils.copyProperties(postPersonInfo, postPersonInfoVO);
        return postPersonInfoVO;
    }
}
