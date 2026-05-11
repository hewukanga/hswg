package com.jiumai.base.sm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiumai.base.sm.entity.Dictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author wangjian
 * @since 2020-08-04
 */
public interface DictionaryMapper extends BaseMapper<Dictionary> {

    /**
     * 根据字典分类获取字典数据
     * @param dicClassify
     * @return
     */
    List<Dictionary> findDicByClassify(Integer dicClassify);

    /**
     * 根据编码类型获取字典数据
     * @param dicTypeCode
     * @return
     */
    List<Dictionary> findDicByTypeCode(String dicTypeCode);

    /**
     * 获取字典名称
     * @param dicTypeCode
     * @param dicCode
     * @return
     */
    Dictionary getDic(@Param("dicTypeCode") String dicTypeCode,@Param("dicCode")  String dicCode);

    /**
     * 根据字典名称获取字典信息
     * @param dicName
     * @return
     */
    Dictionary getDicByName(String dicName);

    /**
     * 判断字典是否重复（同一种字典类型的字典名称和编码都不能重复）
     * @param dictionary
     * @return
     */
    List<Dictionary> isRepeatDic(@Param("dictionary") Dictionary dictionary);
}
