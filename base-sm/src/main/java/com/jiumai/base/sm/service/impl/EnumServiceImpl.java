package com.jiumai.base.sm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiumai.base.common.core.utils.PageUtils;
import com.jiumai.base.sm.enums.EnumTypeEnum;
import com.jiumai.base.sm.mapper.SmEnumConfMapper;
import org.springframework.stereotype.Service;

import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.sm.dto.EnumConfDTO;
import com.jiumai.base.sm.entity.SmEnumConf;
import com.jiumai.base.sm.query.EnumQuery;
import com.jiumai.base.sm.service.EnumService;

@Service
public class EnumServiceImpl extends ServiceImpl<SmEnumConfMapper, SmEnumConf> implements EnumService {
    @Resource
    private SmEnumConfMapper smEnumConfMapper;

    @Override
    public void batchUpdateSysEnums(List<SmEnumConf> list) {
        smEnumConfMapper.delete(new QueryWrapper<SmEnumConf>().lambda().eq(SmEnumConf::getEnumType, EnumTypeEnum.SYS.getValue()));
        saveBatch(list);
    }

    @Override
    public void saveOrUpdateUserEnums(List<SmEnumConf> configs) throws Exception {

        List<SmEnumConf> enumConfs = smEnumConfMapper.selectList(new QueryWrapper<SmEnumConf>()
                .lambda()
                .eq(CommonFuntions.isNotEmptyObject(configs.get(0).getEnumCode()), SmEnumConf::getEnumCode, configs.get(0).getEnumCode())
                .select(SmEnumConf::getEnumId)
        );
        List<Long> enumIds = new ArrayList<>();
        if (CommonFuntions.isNotEmptyObject(enumConfs)) {
            enumIds = enumConfs.stream().map(SmEnumConf::getEnumId).collect(Collectors.toList());
        }

        for (SmEnumConf conf : configs) {
            if (CommonFuntions.isNotEmptyObject(conf.getEnumId())) {
                if (enumIds.contains(conf.getEnumId())) {
                    enumIds.remove(conf.getEnumId());
                }
                smEnumConfMapper.updateById(conf);
            } else {
                smEnumConfMapper.insert(conf);
            }
        }

        // 删除枚举值
        if (CommonFuntions.isNotEmptyObject(enumIds)) {
            smEnumConfMapper.delete(new QueryWrapper<SmEnumConf>().lambda().in(SmEnumConf::getEnumId, enumIds));
        }
    }


    @Override
    public void deleteBatchEnums(List<Long> enumIds) {
        if (CommonFuntions.isNotEmptyObject(enumIds)) {
            smEnumConfMapper.delete(new QueryWrapper<SmEnumConf>().lambda().in(SmEnumConf::getEnumId, enumIds));
        }
    }


    @Override
    public IPage<EnumConfDTO> findEnumPaging(EnumQuery enumQuery) {
        PageUtils<EnumConfDTO> pageUtils = new PageUtils<>();
        Page<EnumConfDTO> page = pageUtils.getPageByPageParam(enumQuery);
        smEnumConfMapper.findEnumPaging(page, enumQuery);
        List<SmEnumConf> smEnumConfs = smEnumConfMapper.selectList(new QueryWrapper<>());
        Map<String, List<SmEnumConf>> map = smEnumConfs.stream().collect(Collectors.groupingBy(SmEnumConf::getEnumCode));

        for (EnumConfDTO enm : page.getRecords()) {
            enm.setEntitys(map.get(enm.getEnumCode()));
        }
        return page;
    }

    @Override
    public List<SmEnumConf> findEnumConfByEnumCode(String enumCode) {
        List<SmEnumConf> list = smEnumConfMapper.selectList(new QueryWrapper<SmEnumConf>().lambda().eq(SmEnumConf::getEnumCode, enumCode).select());
        return list;
    }

    @Override
    public List<SmEnumConf> findEnumConfByParent(String enumCode, String parentValue) {

        List<SmEnumConf> list = smEnumConfMapper.selectList(new QueryWrapper<SmEnumConf>()
                .lambda()
                .eq(SmEnumConf::getEnumCode, enumCode)
                .eq(SmEnumConf::getRefEntityValue, parentValue)
                .select()
        );
        return list;
    }

    @Override
    public Boolean checkEnumCodeIsUniquen(String enumCode) {
        List<SmEnumConf> smEnumConfs = smEnumConfMapper.selectList(new QueryWrapper<SmEnumConf>().lambda().eq(SmEnumConf::getEnumCode, enumCode).select());
        return smEnumConfs.size() > 0 ? true : false;
    }


}
