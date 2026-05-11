package com.jiumai.base.listener;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiumai.base.common.core.utils.PropertiesUtils;
import com.jiumai.base.sm.CoreConstant;
import com.jiumai.base.sm.entity.SmEnumConf;
import com.jiumai.base.sm.enums.EnumTypeEnum;
import com.jiumai.base.sm.service.EnumService;
import io.swagger.annotations.ApiModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.lang.reflect.Method;
import java.util.*;

@Slf4j
@Component
public class EnumListener{

    @Autowired
    private EnumService enumService;

    public void init() {

        String initEnums = PropertiesUtils.getProperty("init.system.enums", "false");
        if ("false".equals(initEnums)) {
            return;
        }
        log.info("==========初始化系统Enums===========");
        // 初始化删除所有系统枚举值
        enumService.remove(new QueryWrapper<SmEnumConf>().lambda().eq(SmEnumConf::getEnumType, "SYS"));
        ClassPathCandidateComponentScanner ss = new ClassPathCandidateComponentScanner();
        try {
            Set<Class<?>> set = ss.findCandidateComponents("com.jiumai");
            this.initEnums(set);

            // Iterator<Class<?>> it = set.iterator();
            // while(it.hasNext()) {
            // logger.error(it.next());
            // }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void initEnums(Set<Class<?>> packageList) throws Exception {
        Map<String, List<SmEnumConf>> enumMaps = new HashMap<String, List<SmEnumConf>>();
//		 logger.debug(">>>>>>>>>>>ENUM: " + packageList.size());
        for (Class enumName : packageList) {
//			System.out.println(enumName);
            // Class<?> clazz = Class.forName(enumName);
            Class<?> clazz = enumName;
            String key = clazz.getSimpleName();
            if (clazz.isEnum()) {
                // 业务返回结果编码Code枚举以及枚举用例不存入数据库
                if (enumName.getName().equals("com.jiumai.xwjd.common.core.enums.ResultCodeEnum")
                        || enumName.getName().equals("com.jiumai.xwjd.sm.enums.EnumExample")
                        || enumName.getName().equals("com.jiumai.xwjd.common.core.dto.ActionCodeEnum")) {
                    continue;
                }
                String className = clazz.getName();
                if (className.indexOf("DTO") >= 0 || className.indexOf("Service") >= 0
                        || className.indexOf("Controller") >= 0) {
                    continue;
                }

                Method name = null;
                Method value = null;
                Method parent = null;
                Object[] objects = null;
                ApiModel annotation = null;
                try {

                    name = clazz.getMethod("getName");
                    value = clazz.getMethod("getValue");
                    annotation = clazz.getAnnotation(ApiModel.class);
                    objects = clazz.getEnumConstants();
                    try {
                        parent = clazz.getMethod("getParent");

                    } catch (NoSuchMethodException e) {
                        List<SmEnumConf> list = this.getSmEnumConfList(objects, clazz.getName(), name, value, parent,
                                annotation.value());
                        enumMaps.put(key, list);
                        // logger.debug("Enum:"+ clazz.getName()+" 没有getParent方法");
                        continue;
                    }
                    List<SmEnumConf> list = this.getSmEnumConfList(objects, clazz.getName(), name, value, parent,
                            annotation.value());
                    enumMaps.put(key, list);

                } catch (Exception ee) {
                    log.error(ee.getMessage());
                }

            }

        }
        CoreConstant.enumMaps = enumMaps;
        log.debug("============" + enumMaps.size());
        // 插入新的枚举值
        for (List<SmEnumConf> list : enumMaps.values()) {
            enumService.saveBatch(list);
        }
    }

    private List<SmEnumConf> getSmEnumConfList(Object[] objects, String enumName, Method name, Method value,
                                               Method parent, String enumCode) throws Exception {
        String[] split = enumName.split("\\.");
        enumName = split[split.length - 1];
        List<SmEnumConf> list = new ArrayList<>();
        for (Object object : objects) {
            SmEnumConf enumConf = new SmEnumConf();
            String entityName = null;
            String entityValue = null;
            String refEntityValue = null;
            Object object2 = null;
            if (name != null) {
                entityName = (String) name.invoke(object);
            }
            if (value != null) {
                entityValue = (String) value.invoke(object);
            }
            if (parent != null) {
                object2 = (Object) parent.invoke(object);
                if (object2.getClass().isEnum()) {
                    Method method = object2.getClass().getMethod("getValue");
                    refEntityValue = (String) method.invoke(object2);
                }
            }
            enumConf.setEnumName(enumCode);
            enumConf.setEnumCode(enumName);
            enumConf.setEntityName(entityName);
            enumConf.setEntityValue(entityValue);
            enumConf.setRefEntityValue(refEntityValue);
            enumConf.setEnumType(EnumTypeEnum.SYS);
            list.add(enumConf);
        }

        return list;
    }

}
