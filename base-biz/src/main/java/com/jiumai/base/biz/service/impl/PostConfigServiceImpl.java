package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PostConfigDTO;
import com.jiumai.base.biz.entity.PostConfig;
import com.jiumai.base.biz.mapper.PostConfigMapper;
import com.jiumai.base.biz.query.PostConfigQuery;
import com.jiumai.base.biz.service.PostConfigService;
import com.jiumai.base.biz.vo.PostConfigVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 岗位配置信息表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PostConfigServiceImpl extends ServiceImpl<PostConfigMapper, PostConfig> implements PostConfigService {

    @Resource
    private PostConfigMapper postConfigMapper;

    @Override
    public Page<PostConfigVO> findPostConfigPage(PostConfigQuery query) {
        Page<PostConfigVO> page = new Page<>(query.getPage(), query.getSize());
        List<PostConfigVO> list = postConfigMapper.findPostConfigPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePostConfig(PostConfigDTO postConfigDTO) {
        PostConfig postConfig = new PostConfig();

        BeanUtils.copyProperties(postConfigDTO, postConfig);

        this.saveOrUpdate(postConfig);

        return postConfig.getId();
    }

    @Override
    public PostConfigVO getPostConfigById(Long id) {
        PostConfig postConfig = this.getById(id);
        if (CommonFuntions.isEmptyObject(postConfig)) {
            throw new BizException("查询失败，岗位配置信息表不存在");
        }

        PostConfigVO postConfigVO = new PostConfigVO();
        BeanUtils.copyProperties(postConfig, postConfigVO);
        return postConfigVO;
    }
}
