package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.RepairTypeDTO;
import com.jiumai.base.biz.entity.RepairType;
import com.jiumai.base.biz.mapper.RepairTypeMapper;
import com.jiumai.base.biz.query.RepairTypeQuery;
import com.jiumai.base.biz.service.RepairTypeService;
import com.jiumai.base.biz.vo.RepairTypeVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 维修项目配置表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class RepairTypeServiceImpl extends ServiceImpl<RepairTypeMapper, RepairType> implements RepairTypeService {

    @Resource
    private RepairTypeMapper repairTypeMapper;

    @Override
    public Page<RepairTypeVO> findRepairTypePage(RepairTypeQuery query) {
        Page<RepairTypeVO> page = new Page<>(query.getPage(), query.getSize());
        List<RepairTypeVO> list = repairTypeMapper.findRepairTypePage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateRepairType(RepairTypeDTO repairTypeDTO) {
        RepairType repairType = new RepairType();

        BeanUtils.copyProperties(repairTypeDTO, repairType);

        this.saveOrUpdate(repairType);

        return repairType.getId();
    }

    @Override
    public RepairTypeVO getRepairTypeById(Long id) {
        RepairType repairType = this.getById(id);
        if (CommonFuntions.isEmptyObject(repairType)) {
            throw new BizException("查询失败，维修项目配置表不存在");
        }

        RepairTypeVO repairTypeVO = new RepairTypeVO();
        BeanUtils.copyProperties(repairType, repairTypeVO);
        return repairTypeVO;
    }
}
