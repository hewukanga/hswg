package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.TemporaryParkingDTO;
import com.jiumai.base.biz.entity.TemporaryParking;
import com.jiumai.base.biz.mapper.TemporaryParkingMapper;
import com.jiumai.base.biz.query.TemporaryParkingQuery;
import com.jiumai.base.biz.service.TemporaryParkingService;
import com.jiumai.base.biz.vo.TemporaryParkingVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 临停车(数泊推送) 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class TemporaryParkingServiceImpl extends ServiceImpl<TemporaryParkingMapper, TemporaryParking> implements TemporaryParkingService {

    @Resource
    private TemporaryParkingMapper temporaryParkingMapper;

    @Override
    public Page<TemporaryParkingVO> findTemporaryParkingPage(TemporaryParkingQuery query) {
        Page<TemporaryParkingVO> page = new Page<>(query.getPage(), query.getSize());
        List<TemporaryParkingVO> list = temporaryParkingMapper.findTemporaryParkingPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateTemporaryParking(TemporaryParkingDTO temporaryParkingDTO) {
        TemporaryParking temporaryParking = new TemporaryParking();

        BeanUtils.copyProperties(temporaryParkingDTO, temporaryParking);

        this.saveOrUpdate(temporaryParking);

        return temporaryParking.getId();
    }

    @Override
    public TemporaryParkingVO getTemporaryParkingById(Long id) {
        TemporaryParking temporaryParking = this.getById(id);
        if (CommonFuntions.isEmptyObject(temporaryParking)) {
            throw new BizException("查询失败，临停车(数泊推送)不存在");
        }

        TemporaryParkingVO temporaryParkingVO = new TemporaryParkingVO();
        BeanUtils.copyProperties(temporaryParking, temporaryParkingVO);
        return temporaryParkingVO;
    }
}
