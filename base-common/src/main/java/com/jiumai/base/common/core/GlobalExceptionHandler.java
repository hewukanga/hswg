package com.jiumai.base.common.core;

import com.alibaba.fastjson2.JSONObject;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {
    private final SysLog logger = SysLogFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 系统异常处理，比如：404,500
     *
     * @param req
     * @param resp
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public ResultDTO<String> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("系统异常", e);
        ResultDTO<String> resultDTO = new ResultDTO<>();
        resultDTO.setResult(e.getMessage());
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            resultDTO.setErrCode("404");
            resultDTO.setSuccess(false);
        } else {
            resultDTO.setErrCode("500");
            resultDTO.setSuccess(false);
        }
        return resultDTO;
    }

    /**
     * 业务异常处理
     */
    @ExceptionHandler(value = BizException.class)
    public ResultDTO<String> handleBizException(HttpServletRequest request,
                                                BizException bizException) {
        ResultCodeEnum resultCode = bizException.getResultCodeEnum();
        ResultDTO<String> result;
        if (resultCode == null) {
            //未定义结果码
            result = ResultDTO.error(ResultCodeEnum.FAIL);
            result.setErrDesc(bizException.getMessage());
        } else if (StringUtils.isNotBlank(bizException.getMessage())
                && !Objects.equals(resultCode.getMsg(), bizException.getMessage())) {
            //自定义消息内容，与结果码不一致
            result = ResultDTO.error(resultCode.getCode(), bizException.getMessage());
        } else {
            result = ResultDTO.error(resultCode.getCode(), resultCode.getMsg());
        }
        logger.info("全局业务异常捕获:" + JSONObject.toJSONString(result));
        return result;
    }

}
