package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.CleaningSituationDTO;
import com.jiumai.base.biz.entity.CleaningSituation;
import com.jiumai.base.biz.mapper.CleaningSituationMapper;
import com.jiumai.base.biz.query.CleaningSituationQuery;
import com.jiumai.base.biz.service.CleaningSituationService;
import com.jiumai.base.biz.vo.CleaningSituationVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 保洁情况 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class CleaningSituationServiceImpl extends ServiceImpl<CleaningSituationMapper, CleaningSituation> implements CleaningSituationService {

    @Resource
    private CleaningSituationMapper cleaningSituationMapper;

    @Override
    public Page<CleaningSituationVO> findCleaningSituationPage(CleaningSituationQuery query) {
        Page<CleaningSituationVO> page = new Page<>(query.getPage(), query.getSize());
        List<CleaningSituationVO> list = cleaningSituationMapper.findCleaningSituationPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateCleaningSituation(CleaningSituationDTO cleaningSituationDTO) {
        CleaningSituation cleaningSituation = new CleaningSituation();

        BeanUtils.copyProperties(cleaningSituationDTO, cleaningSituation);

        this.saveOrUpdate(cleaningSituation);

        return cleaningSituation.getId();
    }

    @Override
    public CleaningSituationVO getCleaningSituationById(Long id) {
        CleaningSituation cleaningSituation = this.getById(id);
        if (CommonFuntions.isEmptyObject(cleaningSituation)) {
            throw new BizException("查询失败，保洁情况不存在");
        }

        CleaningSituationVO cleaningSituationVO = new CleaningSituationVO();
        BeanUtils.copyProperties(cleaningSituation, cleaningSituationVO);
        return cleaningSituationVO;
    }
}
