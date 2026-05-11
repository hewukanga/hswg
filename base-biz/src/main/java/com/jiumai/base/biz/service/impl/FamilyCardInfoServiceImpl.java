package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.FamilyCardInfoDTO;
import com.jiumai.base.biz.entity.FamilyCardInfo;
import com.jiumai.base.biz.mapper.FamilyCardInfoMapper;
import com.jiumai.base.biz.query.FamilyCardInfoQuery;
import com.jiumai.base.biz.service.FamilyCardInfoService;
import com.jiumai.base.biz.vo.FamilyCardInfoVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 亲情卡数据 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class FamilyCardInfoServiceImpl extends ServiceImpl<FamilyCardInfoMapper, FamilyCardInfo> implements FamilyCardInfoService {

    @Resource
    private FamilyCardInfoMapper familyCardInfoMapper;

    @Override
    public Page<FamilyCardInfoVO> findFamilyCardInfoPage(FamilyCardInfoQuery query) {
        Page<FamilyCardInfoVO> page = new Page<>(query.getPage(), query.getSize());
        List<FamilyCardInfoVO> list = familyCardInfoMapper.findFamilyCardInfoPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateFamilyCardInfo(FamilyCardInfoDTO familyCardInfoDTO) {
        FamilyCardInfo familyCardInfo = new FamilyCardInfo();

        BeanUtils.copyProperties(familyCardInfoDTO, familyCardInfo);

        this.saveOrUpdate(familyCardInfo);

        return familyCardInfo.getId();
    }

    @Override
    public FamilyCardInfoVO getFamilyCardInfoById(Long id) {
        FamilyCardInfo familyCardInfo = this.getById(id);
        if (CommonFuntions.isEmptyObject(familyCardInfo)) {
            throw new BizException("查询失败，亲情卡数据不存在");
        }

        FamilyCardInfoVO familyCardInfoVO = new FamilyCardInfoVO();
        BeanUtils.copyProperties(familyCardInfo, familyCardInfoVO);
        return familyCardInfoVO;
    }
}
