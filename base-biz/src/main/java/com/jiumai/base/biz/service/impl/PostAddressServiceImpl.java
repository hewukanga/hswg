package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PostAddressDTO;
import com.jiumai.base.biz.entity.PostAddress;
import com.jiumai.base.biz.mapper.PostAddressMapper;
import com.jiumai.base.biz.query.PostAddressQuery;
import com.jiumai.base.biz.service.PostAddressService;
import com.jiumai.base.biz.vo.PostAddressVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 岗位地址信息表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PostAddressServiceImpl extends ServiceImpl<PostAddressMapper, PostAddress> implements PostAddressService {

    @Resource
    private PostAddressMapper postAddressMapper;

    @Override
    public Page<PostAddressVO> findPostAddressPage(PostAddressQuery query) {
        Page<PostAddressVO> page = new Page<>(query.getPage(), query.getSize());
        List<PostAddressVO> list = postAddressMapper.findPostAddressPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePostAddress(PostAddressDTO postAddressDTO) {
        PostAddress postAddress = new PostAddress();

        BeanUtils.copyProperties(postAddressDTO, postAddress);

        this.saveOrUpdate(postAddress);

        return postAddress.getId();
    }

    @Override
    public PostAddressVO getPostAddressById(Long id) {
        PostAddress postAddress = this.getById(id);
        if (CommonFuntions.isEmptyObject(postAddress)) {
            throw new BizException("查询失败，岗位地址信息表不存在");
        }

        PostAddressVO postAddressVO = new PostAddressVO();
        BeanUtils.copyProperties(postAddress, postAddressVO);
        return postAddressVO;
    }
}
