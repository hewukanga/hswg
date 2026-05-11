package com.jiumai.base.common.core.quartz.init;

import com.jiumai.base.common.core.annotation.Quartz;
import com.jiumai.base.common.core.entity.SmQuartz;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.enums.TriggerStatusEnum;
import com.jiumai.base.common.core.enums.TriggerTypeEnum;
import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.mapper.OpLogMapper;
import com.jiumai.base.common.core.mapper.QuartzMapper;
import com.jiumai.base.common.core.quartz.dto.HandlerTriggerDTO;
import com.jiumai.base.common.core.quartz.dto.SaveTriggerDTO;
import com.jiumai.base.common.core.quartz.service.TriggerService;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.common.core.utils.SpringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

@Component
public class QuartzInit implements CommandLineRunner {

    @javax.annotation.Resource
    private TriggerService triggerService;

    @javax.annotation.Resource
    private QuartzMapper quartzMapper;

    //包下的类(这里扫描了所有)
    private final  static  String CLASS="/**/*.class";

    //要扫描的包
    private final  static  String PACKAGE="com.jiumai.";

    @Override
    public void run(String... args) {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        //指定包路径
        String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                ClassUtils.convertClassNameToResourcePath(PACKAGE) + CLASS;
        try {
            //获取包路径下所有资源
            Resource[] resources = resourcePatternResolver.getResources(pattern);
            MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
            for (Resource resource : resources) {
                MetadataReader reader = readerFactory.getMetadataReader(resource);
                //获取到类的包+类名路径
                String classname = reader.getClassMetadata().getClassName();
                if(CommonFuntions.isNotEmptyObject(classname)) {
                    //获取到类class
                    Class<?> clazz = Class.forName(classname);
                    //获取类上Quartz注解
                    Quartz quartz = clazz.getAnnotation(Quartz.class);
                    if (quartz != null) {
                        Quartz annotation = clazz.getAnnotation(Quartz.class);
                        //校验定时任务注解参数
                        checkAnnotationParams(annotation, classname);
                        //不存在 执行新增操作
                        if(!triggerService.checkTriggerExist(annotation.triggerName(), annotation.triggerGroup())){
                            SmQuartz smQuartz = wrapDbQuartz(annotation, classname);
                            quartzMapper.insert(smQuartz);
                            SaveTriggerDTO saveTriggerDTO = wrapSaveTriggerDTO(annotation);
                            triggerService.createTrigger(saveTriggerDTO);
                        }
//                        HandlerTriggerDTO handlerTriggerDTO = wrapHandlerTriggerDTO(annotation);
//                        triggerService.rescheduleTrigger(handlerTriggerDTO);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 封装定时任务实体类
     * @param annotation
     * @param classname
     * @return
     */
    private SmQuartz wrapDbQuartz(Quartz annotation, String classname){
        SmQuartz smQuartz = new SmQuartz();
        smQuartz.setJobGroup(annotation.jobGroup());
        smQuartz.setJobName(annotation.jobName());
        smQuartz.setTriggerGroup(annotation.triggerGroup());
        smQuartz.setTriggerName(annotation.triggerName());
        smQuartz.setTriggerType(annotation.triggerType());
        smQuartz.setDescription(annotation.description());
        smQuartz.setJobClassNamePath(classname);
        smQuartz.setStatus(TriggerStatusEnum.START_UP);
        if(annotation.triggerType() == TriggerTypeEnum.CRON){
            smQuartz.setCronExpression(annotation.cronExpression());
        }
        if(annotation.triggerType() == TriggerTypeEnum.SIMPLE){
            smQuartz.setRepeatCount(annotation.repeatCount());
            smQuartz.setRepeatInterval(annotation.repeatInterval());
        }
        return smQuartz;
    }

    /**
     * 封装定时任务修改入参
     * @param annotation
     * @return
     */
    private HandlerTriggerDTO wrapHandlerTriggerDTO(Quartz annotation){
        HandlerTriggerDTO handlerTriggerDTO = new HandlerTriggerDTO();
        handlerTriggerDTO.setTriggerGroup(annotation.triggerGroup());
        handlerTriggerDTO.setTriggerName(annotation.triggerName());
        handlerTriggerDTO.setCronExpression(annotation.cronExpression());
        return handlerTriggerDTO;
    }

    /**
     * 封装定时任务保存入参
     * @param annotation
     * @return
     */
    private SaveTriggerDTO wrapSaveTriggerDTO(Quartz annotation){
        SaveTriggerDTO saveTriggerDTO = new SaveTriggerDTO();
        saveTriggerDTO.setJobGroup(annotation.jobGroup());
        saveTriggerDTO.setJobName(annotation.jobName());
        saveTriggerDTO.setTriggerGroup(annotation.triggerGroup());
        saveTriggerDTO.setTriggerName(annotation.triggerName());
        saveTriggerDTO.setDescription(annotation.description());
        saveTriggerDTO.setCronExpression(annotation.cronExpression());
        saveTriggerDTO.setRepeatCount(annotation.repeatCount());
        saveTriggerDTO.setRepeatInterval(annotation.repeatInterval());
        saveTriggerDTO.setJobClassName(annotation.jobClassName());
        saveTriggerDTO.setTriggerType(annotation.triggerType().getValue());
        return saveTriggerDTO;
    }
    /**
     * 校验定时任务注解参数
     * @param annotation
     */
    private void checkAnnotationParams(Quartz annotation, String classname){
        if(CommonFuntions.isEmptyObject(annotation.jobGroup())){
            throw new BizException(ResultCodeEnum.FAIL, "定时任务类【"+classname+"】-【jobGroup】字段不允许为空");
        }
        if(CommonFuntions.isEmptyObject(annotation.jobName())){
            throw new BizException(ResultCodeEnum.FAIL, "定时任务类【"+classname+"】-【jobName】字段不允许为空");
        }
        if(CommonFuntions.isEmptyObject(annotation.triggerGroup())){
            throw new BizException(ResultCodeEnum.FAIL, "定时任务类【"+classname+"】-【triggerGroup】字段不允许为空");
        }
        if(CommonFuntions.isEmptyObject(annotation.triggerName())){
            throw new BizException(ResultCodeEnum.FAIL, "定时任务类【"+classname+"】-【triggerName】字段不允许为空");
        }
        if(CommonFuntions.isEmptyObject(annotation.jobClassName())){
            throw new BizException(ResultCodeEnum.FAIL, "定时任务类【"+classname+"】-【jobClassName】字段不允许为空");
        }
        if(CommonFuntions.isEmptyObject(annotation.triggerType())){
            throw new BizException(ResultCodeEnum.FAIL, "定时任务类【"+classname+"】-【triggerType】字段不允许为空");
        }
        if(annotation.triggerType() == TriggerTypeEnum.CRON){
            if(CommonFuntions.isEmptyObject(annotation.cronExpression())){
                throw new BizException(ResultCodeEnum.FAIL, "定时任务类【"+classname+"】在cron模式下【cronExpression】字段不允许为空");
            }
        }
        if(annotation.triggerType() == TriggerTypeEnum.SIMPLE){
            if(CommonFuntions.isEmptyObject(annotation.repeatInterval())){
                throw new BizException(ResultCodeEnum.FAIL, "定时任务类【"+classname+"】在simple模式下【repeatInterval】字段不允许为空");
            }
        }
    }
}
