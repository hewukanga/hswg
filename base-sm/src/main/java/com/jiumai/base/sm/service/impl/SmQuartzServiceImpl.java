package com.jiumai.base.sm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.enums.TriggerTypeEnum;
import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.quartz.dto.HandlerTriggerDTO;
import com.jiumai.base.common.core.quartz.dto.SaveTriggerDTO;
import com.jiumai.base.common.core.quartz.service.TriggerService;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.common.core.utils.PageUtils;
import com.jiumai.base.sm.dto.SmQuartzDTO;
import com.jiumai.base.sm.entity.SmQuartz;
import com.jiumai.base.sm.mapper.SmQuartzMapper;
import com.jiumai.base.sm.query.QuartzQuery;
import com.jiumai.base.sm.service.SmQuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mysql gen
 * @since 2023-02-02
 */
@Service
@Slf4j
public class SmQuartzServiceImpl extends ServiceImpl<SmQuartzMapper, SmQuartz> implements SmQuartzService {

    @Resource
    private SmQuartzMapper smQuartzMapper;

    @Resource
    private TriggerService triggerService;

    @Override
    public IPage<SmQuartzDTO> quartzPaging(QuartzQuery quartzQuery) {
        //分页查询
        Page<SmQuartz> smQuartzPage = this.pageQuartzByQuery(quartzQuery);

        //转换
        PageUtils<SmQuartzDTO> pageUtils = new PageUtils<SmQuartzDTO>();
        Page<SmQuartzDTO> resultPage = pageUtils.getNewPage(smQuartzPage);
        if (CommonFuntions.isNotEmptyObject(smQuartzPage.getRecords())) {
            resultPage.setRecords(BeanUtil.copyToList(smQuartzPage.getRecords(), SmQuartzDTO.class));
        }
        return resultPage;
    }

    @Override
    public Page<SmQuartz> pageQuartzByQuery(QuartzQuery query) {
        PageUtils<SmQuartz> pageUtils = new PageUtils<>();
        Page<SmQuartz> page = pageUtils.getPageByPageParam(query);
        return this.page(page, getLambdaQueryWrapper(query));
    }

    @Override
    public void saveQuartz(SmQuartzDTO smQuartzDTO) throws ClassNotFoundException, SchedulerException {
        checkSaveQuartzParams(smQuartzDTO);
        //判断是否重复添加
        Long count = smQuartzMapper.selectCount(new QueryWrapper<SmQuartz>()
                .lambda()
                .eq(SmQuartz::getJobGroup, smQuartzDTO.getJobGroup())
                .or()
                .eq(SmQuartz::getJobName, smQuartzDTO.getJobName())
                .or()
                .eq(SmQuartz::getTriggerGroup, smQuartzDTO.getTriggerGroup())
                .or()
                .eq(SmQuartz::getTriggerName, smQuartzDTO.getTriggerName())
        );
        if(CommonFuntions.isNotEmptyObject(count) && count > 0){
            throw new BizException(ResultCodeEnum.FAIL, "定时器已存在请先删除或修改名称！");
        }
        //入库
        SmQuartz smQuartz = BeanUtil.copyProperties(smQuartzDTO, SmQuartz.class);
        smQuartzMapper.insert(smQuartz);
        //封装并创建定时任务
        SaveTriggerDTO saveTriggerDTO = wrapSaveTriggerDTO(smQuartzDTO);
        triggerService.createTrigger(saveTriggerDTO);
    }

    @Override
    public Boolean checkTriggerExist(String triggerName, String triggerGroup) throws SchedulerException {
        return triggerService.checkTriggerExist(triggerName, triggerGroup);
    }

    /**
     * 封装SaveTriggerDTO
     * @param smQuartzDTO
     * @return
     * @throws ClassNotFoundException
     */
    private SaveTriggerDTO wrapSaveTriggerDTO(SmQuartzDTO smQuartzDTO) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(smQuartzDTO.getJobClassNamePath());
        SaveTriggerDTO saveTriggerDTO = BeanUtil.copyProperties(smQuartzDTO, SaveTriggerDTO.class);
        saveTriggerDTO.setJobClassName(clazz);
        if(CommonFuntions.isNotEmptyObject(smQuartzDTO.getStartTime())){
            saveTriggerDTO.setStartTime(smQuartzDTO.getStartTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        }
        if(CommonFuntions.isNotEmptyObject(smQuartzDTO.getEndTime())){
            saveTriggerDTO.setEndTime(smQuartzDTO.getEndTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        }
        if(CommonFuntions.isNotEmptyObject(smQuartzDTO.getJobData())){
            saveTriggerDTO.setJobDataMap(JSONObject.parseObject(smQuartzDTO.getJobData(), JobDataMap.class));
        }
        saveTriggerDTO.setTriggerType(smQuartzDTO.getTriggerType().getValue());
        return saveTriggerDTO;
    }

    @Override
    public void pauseQuartz(SmQuartzDTO smQuartzDTO) throws SchedulerException {
        //校验请求入参
        checkPauseOrResumeQuartzParams(smQuartzDTO);
        SmQuartz smQuartz = BeanUtil.copyProperties(smQuartzDTO, SmQuartz.class);
        smQuartzMapper.updateById(smQuartz);

        //封装并暂停定时任务
        HandlerTriggerDTO handlerTriggerDTO = BeanUtil.copyProperties(smQuartzDTO, HandlerTriggerDTO.class);
        triggerService.pauseTrigger(handlerTriggerDTO);
    }

    @Override
    public void resumeQuartz(SmQuartzDTO smQuartzDTO) throws SchedulerException {
        //校验请求入参
        checkPauseOrResumeQuartzParams(smQuartzDTO);
        SmQuartz smQuartz = BeanUtil.copyProperties(smQuartzDTO, SmQuartz.class);
        smQuartzMapper.updateById(smQuartz);

        //封装并恢复定时任务
        HandlerTriggerDTO handlerTriggerDTO = BeanUtil.copyProperties(smQuartzDTO, HandlerTriggerDTO.class);
        triggerService.resumeTrigger(handlerTriggerDTO);
    }

    @Override
    public void updateQuartz(SmQuartzDTO smQuartzDTO) throws SchedulerException, ClassNotFoundException {
        checkUpdateQuartzParams(smQuartzDTO);
        //校验是否还存在 因为可能之前是简单任务 已经结束了 或者定时任务已经到了结束时间
        Boolean exist = triggerService.checkTriggerExist(smQuartzDTO.getTriggerName(), smQuartzDTO.getTriggerGroup());
        //返回false 说明不存在 则无法修改
        if(!exist){
            throw new BizException(ResultCodeEnum.FAIL, "定时任务【" + smQuartzDTO.getTriggerName() + "】已结束，无法修改，请删除重新创建");
        }
        SmQuartz smQuartz = BeanUtil.copyProperties(smQuartzDTO, SmQuartz.class);
        smQuartzMapper.updateById(smQuartz);

        //封装并修改定时任务
        SaveTriggerDTO saveTriggerDTO = wrapSaveTriggerDTO(smQuartzDTO);
        triggerService.modifyTrigger(saveTriggerDTO);
    }

    @Override
    public void deleteQuartz(SmQuartzDTO smQuartzDTO) throws SchedulerException {
        checkDeleteQuartzParams(smQuartzDTO);
        smQuartzMapper.deleteById(smQuartzDTO.getId());
        HandlerTriggerDTO handlerTriggerDTO = BeanUtil.copyProperties(smQuartzDTO, HandlerTriggerDTO.class);
        triggerService.deleteTrigger(handlerTriggerDTO);
    }

    /**
     * 校验删除定时任务
     * @param smQuartzDTO
     */
    private void checkDeleteQuartzParams(SmQuartzDTO smQuartzDTO){
        checkCommonQuartzParams(smQuartzDTO);
        if(CommonFuntions.isEmptyObject(smQuartzDTO.getTriggerGroup())){
            throw new BizException(ResultCodeEnum.FAIL, "【triggerGroup】字段不允许为空");
        }
        if(CommonFuntions.isEmptyObject(smQuartzDTO.getTriggerName())){
            throw new BizException(ResultCodeEnum.FAIL, "【triggerName】字段不允许为空");
        }
    }

    /**
     * 校验暂停/恢复定时任务请求参数
     * @param smQuartzDTO
     */
    private void checkPauseOrResumeQuartzParams(SmQuartzDTO smQuartzDTO){
        checkCommonQuartzParams(smQuartzDTO);
        if(CommonFuntions.isEmptyObject(smQuartzDTO.getStatus())){
            throw new BizException(ResultCodeEnum.FAIL, "【status】字段不允许为空");
        }
    }

    /**
     * 公共校验
     * @param smQuartzDTO
     */
    private void checkCommonQuartzParams(SmQuartzDTO smQuartzDTO){
        if(CommonFuntions.isEmptyObject(smQuartzDTO.getId())){
            throw new BizException(ResultCodeEnum.FAIL, "【id】字段不允许为空");
        }
        if(CommonFuntions.isEmptyObject(smQuartzDTO.getJobGroup())){
            throw new BizException(ResultCodeEnum.FAIL, "【jobGroup】字段不允许为空");
        }
        if(CommonFuntions.isEmptyObject(smQuartzDTO.getJobName())){
            throw new BizException(ResultCodeEnum.FAIL, "【jobName】字段不允许为空");
        }
    }

    /**
     * 校验修改定时任务请求参数
     * @param smQuartzDTO
     */
    private void checkUpdateQuartzParams(SmQuartzDTO smQuartzDTO){
        if(CommonFuntions.isEmptyObject(smQuartzDTO.getId())){
            throw new BizException(ResultCodeEnum.FAIL, "【id】字段不允许为空");
        }
        checkSaveQuartzParams(smQuartzDTO);
    }
    /**
     * 校验保存定时任务请求参数
     * @param smQuartzDTO
     */
    private void checkSaveQuartzParams(SmQuartzDTO smQuartzDTO){
        if(CommonFuntions.isEmptyObject(smQuartzDTO.getJobGroup())){
            throw new BizException(ResultCodeEnum.FAIL, "【jobGroup】字段不允许为空");
        }
        if(CommonFuntions.isEmptyObject(smQuartzDTO.getJobName())){
            throw new BizException(ResultCodeEnum.FAIL, "【jobName】字段不允许为空");
        }
        if(CommonFuntions.isEmptyObject(smQuartzDTO.getTriggerGroup())){
            throw new BizException(ResultCodeEnum.FAIL, "【triggerGroup】字段不允许为空");
        }
        if(CommonFuntions.isEmptyObject(smQuartzDTO.getTriggerName())){
            throw new BizException(ResultCodeEnum.FAIL, "【triggerName】字段不允许为空");
        }
        if(CommonFuntions.isEmptyObject(smQuartzDTO.getJobClassNamePath())){
            throw new BizException(ResultCodeEnum.FAIL, "【jobClassNamePath】字段不允许为空");
        }
        if(CommonFuntions.isEmptyObject(smQuartzDTO.getTriggerType())){
            throw new BizException(ResultCodeEnum.FAIL, "【triggerType】字段不允许为空");
        }
        if(CommonFuntions.isEmptyObject(smQuartzDTO.getStatus())){
            throw new BizException(ResultCodeEnum.FAIL, "【status】字段不允许为空");
        }
        if(smQuartzDTO.getTriggerType() == TriggerTypeEnum.CRON){
            if(CommonFuntions.isEmptyObject(smQuartzDTO.getCronExpression())){
                throw new BizException(ResultCodeEnum.FAIL, "cron模式下【cronExpression】字段不允许为空");
            }
        }
        if(smQuartzDTO.getTriggerType() == TriggerTypeEnum.SIMPLE){
            if(CommonFuntions.isEmptyObject(smQuartzDTO.getRepeatCount())){
                throw new BizException(ResultCodeEnum.FAIL, "simple模式下【repeatCount】字段不允许为空，允许为0");
            }
            if(CommonFuntions.isEmptyObject(smQuartzDTO.getRepeatInterval())){
                throw new BizException(ResultCodeEnum.FAIL, "simple模式下【repeatInterval】字段不允许为空");
            }
        }
    }

    /**
     * 构建wrapper
     *
     * @param query 查询参数
     * @return wrapper
     */
    private LambdaQueryWrapper<SmQuartz> getLambdaQueryWrapper(QuartzQuery query) {
        LambdaQueryWrapper<SmQuartz> lambdaQueryWrapper = new QueryWrapper<SmQuartz>().lambda();
        //触发器名称
        lambdaQueryWrapper.like(CommonFuntions.isNotEmptyObject(query.getTriggerName()), SmQuartz::getTriggerName, query.getTriggerName());
        //状态
        lambdaQueryWrapper.eq(CommonFuntions.isNotEmptyObject(query.getStatus()), SmQuartz::getStatus, query.getStatus());
        //定时任务类型
        lambdaQueryWrapper.eq(CommonFuntions.isNotEmptyObject(query.getTriggerType()), SmQuartz::getTriggerType, query.getTriggerType());
        lambdaQueryWrapper.orderByDesc(SmQuartz::getCreateDate);
        return lambdaQueryWrapper;
    }
}
