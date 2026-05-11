package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.OilCardDTO;
import com.jiumai.base.biz.entity.OilCard;
import com.jiumai.base.biz.mapper.OilCardMapper;
import com.jiumai.base.biz.query.OilCardQuery;
import com.jiumai.base.biz.service.OilCardService;
import com.jiumai.base.biz.vo.OilCardVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 油卡表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class OilCardServiceImpl extends ServiceImpl<OilCardMapper, OilCard> implements OilCardService {

    @Resource
    private OilCardMapper oilCardMapper;

    @Override
    public Page<OilCardVO> findOilCardPage(OilCardQuery query) {
        Page<OilCardVO> page = new Page<>(query.getPage(), query.getSize());
        List<OilCardVO> list = oilCardMapper.findOilCardPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateOilCard(OilCardDTO oilCardDTO) {
        OilCard oilCard = new OilCard();

        BeanUtils.copyProperties(oilCardDTO, oilCard);

        this.saveOrUpdate(oilCard);

        return oilCard.getId();
    }

    @Override
    public OilCardVO getOilCardById(Long id) {
        OilCard oilCard = this.getById(id);
        if (CommonFuntions.isEmptyObject(oilCard)) {
            throw new BizException("查询失败，油卡表不存在");
        }

        OilCardVO oilCardVO = new OilCardVO();
        BeanUtils.copyProperties(oilCard, oilCardVO);
        return oilCardVO;
    }
}
