package com.jiumai.base.sm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiumai.base.sm.entity.SmRelOpArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 操作员可访问区域 Mapper 接口
 * </p>
 *
 * @author mysql gen
 * @since 2023-01-09
 */
public interface SmRelOpAreaMapper extends BaseMapper<SmRelOpArea> {


    /**
     * 批量修改
     * @param smRelOpAreas
     */
    void batchUpdateExpireDate(@Param("list") List<SmRelOpArea> smRelOpAreas);
}
