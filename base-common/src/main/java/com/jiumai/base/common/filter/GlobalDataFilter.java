package com.jiumai.base.common.filter;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SM4;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Author     ：cz
 * @ Date       ：Created in 2022-12-1220:43
 * @ Description：
 * @ Modified By：
 */
@Slf4j
@Configuration
@WebFilter(urlPatterns = "/*", filterName = "globalDataFilter")
public class GlobalDataFilter implements Filter {

    @Resource
    private ObjectMapper objectMapper;

    @Value("${api.encrypt.key}")
    public String key;
    @Value("${api.encrypt.enable}")
    public boolean enable;
    @Value("#{'${api.encrypt.whiteList}'.split(',')}")
    public List<String> whiteList;

    private SM4 sm4 = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化加密工具
        sm4 = SmUtil.sm4(key.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        Map<String, String[]> myParameters = new HashMap<>(req.getParameterMap());
        MyRequestWrapper requestWrapper = new MyRequestWrapper((HttpServletRequest) req, myParameters, sm4);
        MyResponseWrapper responseWrapper = new MyResponseWrapper((HttpServletResponse) resp);
        boolean isWhite = this.isWhiteList(requestWrapper);

        if (enable && !isWhite) {
            // 请求数据解密
            this.decrypt(requestWrapper);
        }
        // 继续后续请求处理，执行controller
        chain.doFilter(requestWrapper, responseWrapper);

        byte[] rewriteResult;
        if (enable && !isWhite) {
            // 返回参数加密
            rewriteResult = this.encryptResponse(requestWrapper, responseWrapper);
        } else {
            // 原始数据
            rewriteResult = responseWrapper.toByteArray();
        }

        //往原始的HttpResponse对象中写入数据
        resp.setContentLength(rewriteResult.length);
        try (OutputStream out = resp.getOutputStream()) {
            out.write(rewriteResult);
            out.flush();
        }

    }

    @Override
    public void destroy() {
    }


    /**
     * 请求参数解密，仅支持body加密
     * query解密在这里： {@link MyRequestWrapper}
     *
     * @param req
     * @return
     */
    private MyRequestWrapper decrypt(MyRequestWrapper req) throws UnsupportedEncodingException {

        // http body传参
        if (req.getBytes() != null && req.getBytes().length >0) {
            byte[] requestByte = req.getBytes();
            byte[] newBodyByte = sm4.decrypt(Base64.decode(requestByte));
            req.setBytes(newBodyByte);
        }
        // http query传参
//        String query = req.getParameter("query");
//        if (query != null) {
//            String decode = null;
//            try {
//                decode = URLDecoder.decode(query, "UTF-8");
//            } catch (UnsupportedEncodingException e) {
//                log.error("URL解码失败", e);
//            }
//            byte[] newBodyByte = sm4.decrypt(decode);
//            req.setBytes(newBodyByte);
//        }


        return req;
    }

    /**
     * 加密响应数据的result，仅支持 ResultDTO.result
     *
     * @param req
     * @param responseWrapper
     * @return
     */
    private byte[] encryptResponse(MyRequestWrapper req, MyResponseWrapper responseWrapper) {

        //获取响应数据
        String response = new String(responseWrapper.toByteArray());
        byte[] rewriteResult = null;
        if (JSONUtil.isTypeJSON(response) && this.canParseResultDTO(response)) {
            log.debug("返回值：{}   ",response);
            ResultDTO resultDTO = JSON.parseObject(response, ResultDTO.class);
            Object result = resultDTO.getResult();
            try {
                if (result != null) {

                    String encrypt;
                    if(result instanceof String){
                        encrypt= sm4.encryptBase64((String)result,StandardCharsets.UTF_8);
                    }else{
                        encrypt = sm4.encryptBase64(objectMapper.writeValueAsBytes(result));
                    }
                    resultDTO.setResult(encrypt);
                }
                rewriteResult = objectMapper.writeValueAsBytes(resultDTO);
            } catch (IOException e) {
                log.error("", e);
            }
        } else {
            rewriteResult = responseWrapper.toByteArray();
        }
        return rewriteResult;
    }

    /**
     * 判断是否为白名单
     *
     * @param req
     * @return
     */
    private boolean isWhiteList(MyRequestWrapper req) {
        String requestOrigion = req.getHeader("request-origion");
        String referer = req.getHeader("referer");
        String userAgent = req.getHeader("user-agent");

        if (
                (requestOrigion != null && requestOrigion.contains("Knife4j"))
                        || (referer != null && referer.contains("doc.html"))
                        || (userAgent != null && userAgent.contains("PostmanRuntime"))
        ) {
            String realIP = HttpUtils.getRealIP(req);
            if ("0:0:0:0:0:0:0:1".equals(realIP) || "127.0.0.1".equals(realIP)) {
                // localhost无需加密，方便本地测试
                return true;
            }
        }
        String requestURI = req.getRequestURI();
        // 白名单配置
        for (String whiteUrl : whiteList) {
            if (requestURI.contains(whiteUrl)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断json是否为ResultDTO
     *
     * @param jsonStr
     * @return
     */
    private static boolean canParseResultDTO(String jsonStr) {
        try {
            ResultDTO resultDTO = JSON.parseObject(jsonStr, ResultDTO.class);
            return resultDTO.getErrCode() != null;
        } catch (Exception e) {
            return false;
        }
    }

}
