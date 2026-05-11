package com.jiumai.base.sm.utils;

import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.common.core.utils.StringUtils;
import com.jiumai.base.sm.entity.Dictionary;
import com.jiumai.base.sm.mapper.DictionaryMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class DictionaryUtils {

    @Resource
    DictionaryMapper dictionaryMapper;

    public static DictionaryUtils dictionaryUtils;
    @PostConstruct
    public void init() {
        dictionaryUtils = this;
        dictionaryUtils.dictionaryMapper = this.dictionaryMapper;
    }

    /**
     * 获取字典名称
     * @param dicTypeCode 字典编码类型
     * @param dicCode 字典编码
     * @return
     */
    public static String getDicName(String dicTypeCode,String dicCode) {
        if(CommonFuntions.isEmptyObject(dicCode)) {
            return null;
        }else {
            Dictionary dictionary = null;
            try {
                dictionary = dictionaryUtils.dictionaryMapper.getDic(dicTypeCode,dicCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(dictionary == null) {
                return null;
            }else {
                return dictionary.getDicName();
            }

        }

    }

    /**
     * 判断字典类型编码是否重复 （添加同级节点时判断是否重复）
     * @param dicTypeCode
     * @return
     * @throws Exception
     */
    public static boolean isDicTypeCodeRepeat(String dicTypeCode) throws Exception {
        if(StringUtils.isEmpty(dicTypeCode)) {
            return false;
        }else {
            Dictionary dictionary = dictionaryUtils.dictionaryMapper.getDic(dicTypeCode,null);
            return dictionary != null;
        }
    }

    /**
     * 判断字典名称是否重复 （添加同级节点时判断是否重复）
     * @param dicName
     * @return
     * @throws Exception
     */
    public static boolean isDicNameRepeat(String dicName) throws Exception {
        if(StringUtils.isEmpty(dicName)) {
            return false;
        }else {
            Dictionary dictionary = dictionaryUtils.dictionaryMapper.getDicByName(dicName);
            return dictionary != null;
        }
    }
}
