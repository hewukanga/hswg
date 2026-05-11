package com.jiumai.base.sm.aspect;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jiumai.base.common.LocalDateTimeSerializerConfig;
import com.jiumai.base.common.PageModel;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.sm.annotation.Dict;
import com.jiumai.base.sm.entity.Dictionary;
import com.jiumai.base.sm.service.DictionaryService;
import com.jiumai.base.sm.utils.ObjConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用于翻译字典的切面类
 *
 * @author wang jian
 * @Date 2022/01/06
 * @Time 9:55
 */
@Aspect
@Component
@Slf4j
public class DictAspect {
    private static final String DICT_TEXT_SUFFIX = "Str";

    @Autowired
    private DictionaryService dictionaryService;

    //定义切点Pointcut拦截所有对服务器的请求
    // @Pointcut("execution( * com.jiumai.base.*.controller.*.*(..))")
    @Pointcut("@annotation(com.jiumai.base.sm.annotation.DictClass)")
    public void excudeService() {

    }

    /**
     * 这是触发excudeService的时候会执行的，在环绕通知中目标对象方法被调用后的结果进行再处理
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        ResultDTO resultDTO = new ResultDTO();
        //这是定义开始事件
        long time1 = System.currentTimeMillis();
        //这是方法并获取返回结果
        Object result = pjp.proceed();
        if (result instanceof ResultDTO) {
            resultDTO = (ResultDTO) result;
        }
        //这是获取到结束时间
        long time2 = System.currentTimeMillis();
        log.debug("获取JSON数据耗时：" + (time2 - time1) + "ms");
        //解析开始时间
        long start = System.currentTimeMillis();
        //开始解析（翻译字段内部的值凡是打了@Dict这玩意的都会被翻译）
        //处理分页
        if (resultDTO.getResult() instanceof PageModel) {
            resultDTO.setResult(this.translatePage(resultDTO.getResult()));
            //解析结束时间
            long end = System.currentTimeMillis();
            log.debug("解析注入JSON数据耗时：" + (end - start) + "ms");
            return resultDTO;
        }
        //处理lsit
        if(resultDTO.getResult() instanceof List){
            resultDTO.setResult(this.translateList(resultDTO.getResult()));
            //解析结束时间
            long end = System.currentTimeMillis();
            log.debug("解析注入JSON数据耗时：" + (end - start) + "ms");
            return resultDTO;
        }else{
            //处理普通对象
            resultDTO.setResult(this.translateObject(resultDTO.getResult()));
            long end = System.currentTimeMillis();
            log.debug("解析注入JSON数据耗时：" + (end - start) + "ms");
            return resultDTO;
        }

    }

    /**
     * 本方法针对返回对象为Result的PageModel的分页列表数据进行动态字典注入
     * 字典注入实现 通过对实体类添加注解@Dict来标识需要的字典内容,字典分为单字典dataSource即可
     * 目前vue是这么进行字典渲染到table上的多了就很麻烦了 这个直接在服务端渲染完成前端可以直接用
     *
     * @param result
     */
    private PageModel translatePage(Object result) {
        List items = new ArrayList<>();
        PageModel pageUtils = (PageModel) result;
        //循环查找出来的数据
        for (Object record : pageUtils.getList()) {
            items.add(translate(record));
        }
        pageUtils.setList(items);
        return pageUtils;
    }

    /**
     * 本方法针对返回对象为Result的List列表数据进行动态字典注入
     * 字典注入实现 通过对实体类添加注解@Dict来标识需要的字典内容,字典分为单字典dataSource即可
     * 目前vue是这么进行字典渲染到table上的多了就很麻烦了 这个直接在服务端渲染完成前端可以直接用
     *
     * @param result
     */
    private List translateList(Object result) {
        List items = new ArrayList<>();
        List list = (List) result;
        //循环查找出来的数据
        for (Object record : list) {
            items.add(translate(record));
        }
        list.clear();
        list.addAll(items);
        return items;

    }

    /**
     * 本方法针对返回对象为Result的普通对象列表数据进行动态字典注入
     * 字典注入实现 通过对实体类添加注解@Dict来标识需要的字典内容,字典分为单字典dataSource即可
     * 目前vue是这么进行字典渲染到table上的多了就很麻烦了 这个直接在服务端渲染完成前端可以直接用
     *
     * @param result
     */
    private JSONObject translateObject(Object result) {
        JSONObject translate = translate(result);
        return translate;
    }

    private JSONObject translate(Object record) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{}";
        try {
            JavaTimeModule timeModule = new JavaTimeModule();
            timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializerConfig.LocalDateTimeSerializer());
            mapper.registerModule(timeModule);
            json = mapper.writeValueAsString(record);
        } catch (JsonProcessingException e) {
            log.error("Json解析失败：" + e);
        }
        JSONObject item = JSONObject.parseObject(json);
        //解决继承实体字段无法翻译问题
        for (Field field : ObjConvertUtils.getAllFields(record)) {
            //解决继承实体字段无法翻译问题
            //如果该属性上面有@Dict注解，则进行翻译
            if (field.getAnnotation(Dict.class) != null) {
                //拿到注解的dictDataSource属性的值
                String datasource = field.getAnnotation(Dict.class).dictDataSource();
                //拿到注解的dictText属性的值
                String text = field.getAnnotation(Dict.class).dictText();
                //获取当前带翻译的值
                String key = String.valueOf(item.get(field.getName()));
                //翻译字典值对应的text值
                String textValue = translateDictValue(datasource, key);
                //DICT_TEXT_SUFFIX的值为，是默认值：
                //public static final String DICT_TEXT_SUFFIX = "Str";
                log.debug("字典Val: " + textValue);
                log.debug("翻译字典字段：" + field.getName() + DICT_TEXT_SUFFIX + "： " + textValue);
                //如果给了文本名
                if (CommonFuntions.isNotEmptyObject(text)) {
                    item.put(text, textValue);
                } else {
                    //走默认策略
                    item.put(field.getName() + DICT_TEXT_SUFFIX, textValue);
                }
            }
        }
        return item;
    }


    /**
     * 翻译字典文本
     *
     * @param datasource
     * @param key
     * @return
     */
    private String translateDictValue(String datasource, String key) {
        //如果key为空直接返回就好了
        if (ObjConvertUtils.isEmpty(key)) {
            return null;
        }
        StringBuffer textValue = new StringBuffer();
        //分割key值
        String[] keys = key.split(",");
        //循环keys中的所有值
        for (String k : keys) {
            String tmpValue = null;
            log.debug("字典key：" + k);
            if (k.trim().length() == 0) {
                continue;//跳过循环
            }
            Dictionary dictionary = dictionaryService.getDic(datasource, k.trim());
            tmpValue = dictionary.getDicName();

            if (tmpValue != null) {
                if (!"".equals(textValue.toString())) {
                    textValue.append(",");
                }
                textValue.append(tmpValue);
            }
        }
        //返回翻译的值
        return textValue.toString();
    }
}