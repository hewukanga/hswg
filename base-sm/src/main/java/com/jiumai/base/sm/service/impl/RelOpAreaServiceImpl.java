package com.jiumai.base.sm.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.sm.mapper.SmRelOpAreaMapper;
import org.springframework.stereotype.Service;
import com.jiumai.base.sm.entity.SmRelOpArea;
import com.jiumai.base.sm.service.RelOpAreaService;
import javax.annotation.Resource;

@Service
public class RelOpAreaServiceImpl extends ServiceImpl<SmRelOpAreaMapper, SmRelOpArea> implements RelOpAreaService {

    @Resource
    private SmRelOpAreaMapper smRelOpAreaMapper;

    @Override
    public void batchUpdateExpireDate(List<SmRelOpArea> smRelOpAreas) {
        smRelOpAreaMapper.batchUpdateExpireDate(smRelOpAreas);
    }

    @Override
    public List<Long> findRelOpAreaIdsByOpId(long opId) throws Exception {
        List<SmRelOpArea> smRelOpAreas = smRelOpAreaMapper.selectList(new QueryWrapper<SmRelOpArea>()
                .lambda()
                .isNull(SmRelOpArea::getExpireDate)
                .eq(opId > 0, SmRelOpArea::getOpId, opId)
                .select(SmRelOpArea::getAreaId)
        );
        if(CommonFuntions.isEmptyObject(smRelOpAreas)){
            return new ArrayList<>();
        }
        return smRelOpAreas.stream().map(SmRelOpArea::getAreaId).collect(Collectors.toList());
    }

    @Override
    public Long saveRelOpArea(SmRelOpArea relOpArea) {
        smRelOpAreaMapper.insert(relOpArea);
        return relOpArea.getRelId();

    }

    @Override
    public void deleteRelOpArea(Long opId, Long areaId) {

        update(new UpdateWrapper<SmRelOpArea>()
                .lambda()
                .set(SmRelOpArea::getExpireDate, new Date())
                .eq(SmRelOpArea::getAreaId, areaId)
                .eq(SmRelOpArea::getOpId, opId)
        );
    }


}
