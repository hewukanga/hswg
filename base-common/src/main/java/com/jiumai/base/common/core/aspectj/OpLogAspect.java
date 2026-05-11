package com.jiumai.base.common.core.aspectj;


import com.alibaba.fastjson2.JSON;
import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.LoginOperator;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.entity.SmOpLog;
import com.jiumai.base.common.core.enums.BusinessStatusEnum;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.manager.AsyncFactory;
import com.jiumai.base.common.core.manager.AsyncManager;
import com.jiumai.base.common.core.session.OperatorUtil;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.common.core.utils.HttpUtils;
import com.jiumai.base.common.core.utils.ServletUtils;
import com.jiumai.base.common.core.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 操作日志记录处理
 *
 * @author ruoyi
 */
@Aspect
@Component
@Slf4j
public class OpLogAspect {
    // 配置织入点
    @Pointcut("@annotation(com.jiumai.base.common.core.annotation.OpLog)")
    public void logPointCut() {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        try {
            // 获得注解
            OpLog controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) {
                return;
            }
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();

            // *========数据库日志=========*//
            SmOpLog operLog = new SmOpLog();
            if (jsonResult != null) {
                ResultDTO resultDTO = (ResultDTO) jsonResult;
                operLog.setStatus(resultDTO.isSuccess() ? BusinessStatusEnum.SUCCESS : BusinessStatusEnum.FAIL);
            } else {
                operLog.setStatus(BusinessStatusEnum.SUCCESS);
            }
            // 请求的地址
            String ip = HttpUtils.getRealIP(request);
            operLog.setIp(ip);
            // 返回参数
            operLog.setResult(JSON.toJSONString(jsonResult));
            //请求参数(HttpServletRequest或者HttpServletResponse对象会导致序列化字符串异常,要排除。)
            if(controllerLog.isSaveRequestData()){
                Object[] args = joinPoint.getArgs();
                Stream<?> stream = ArrayUtils.isEmpty(args) ? Stream.empty() : Arrays.asList(args).stream();
                List<Object> logArgs = stream.filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse))).collect(Collectors.toList());
                operLog.setParams(JSON.toJSONString(logArgs));
            }else{
                operLog.setParams("{\"msg\":\"未保存参数\"}");
            }
            operLog.setUrl(ServletUtils.getRequest().getRequestURI());

            if(!(controllerLog.businessType().equals(BusinessTypeEnum.LOGIN) ||
                controllerLog.businessType().equals(BusinessTypeEnum.LOGOUT)
            )){
                // 获取当前的用户
                LoginOperator operator = OperatorUtil.getOperator(request);
                if (operator != null) {
                    operLog.setOpId(operator.getOpId());
                    operLog.setOpName(operator.getOpName());
                    if (CommonFuntions.isNotEmptyObject(operator.getOrgId())
                            && StringUtils.isNotEmpty(operator.getOrgName())) {
                        operLog.setDeptName(operator.getOrgName());
                    }
                }
            }

            if (e != null) {
                operLog.setStatus(BusinessStatusEnum.FAIL);
                operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            operLog.setOpTime(new Date());
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethod(className + "." + methodName + "()");
            // 设置请求方式
            operLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            // 处理设置注解上的参数
            getControllerMethodDescription(controllerLog, operLog);
            // 保存数据库
            AsyncManager.me().execute(AsyncFactory.recordOper(operLog));
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log     日志
     * @param operLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(OpLog log, SmOpLog operLog) throws Exception {
        // 设置action动作
        operLog.setBusinessType(log.businessType());
        // 设置标题
        operLog.setTitle(log.title());
        // 设置操作人类别
        operLog.setOperateType(log.operateType());
        // 是否需要保存request，参数和值
//        if (log.isSaveRequestData())
//        {
//            // 获取参数的信息，传入到数据库中。
//            setRequestValue(operLog);
//        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operLog 操作日志
     * @throws Exception 异常
     */
    private void setRequestValue(SmOpLog operLog) throws Exception {
        Map<String, String[]> map = ServletUtils.getRequest().getParameterMap();
        String params = JSON.toJSONString(map);
        operLog.setParams(StringUtils.substring(params, 0, 2000));
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private OpLog getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(OpLog.class);
        }
        return null;
    }
}
