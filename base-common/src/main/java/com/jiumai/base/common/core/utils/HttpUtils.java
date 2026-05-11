/*
 * @(#)HttpUtil.java 2015-11-27下午1:49:55
 * Copyright (c)  2018 Hangzhou JiuMai Network Technology Co., Ltd. All rights reserved.
 */
package com.jiumai.base.common.core.utils;

import com.alibaba.fastjson2.JSONObject;
import com.jiumai.base.common.core.SysLog;
import com.jiumai.base.common.core.SysLogFactory;
import com.jiumai.base.common.core.dto.ConcurrencyControlDTO;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.*;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HTTP请求工具类
 *
 * @author louis<8 3 0 3 0 2 4 6 @ qq.com>
 * @version 1.0
 * @date 2018-02-12
 */
public class HttpUtils {

    private static CloseableHttpClient httpClient;

    private static final String ENCODEING = "UTF-8"; // 编码

    private static SysLog log = SysLogFactory.getLogger(HttpUtils.class);

    static {
        // 设置请求参数
        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectTimeout(30000)
                // 连接超时
                .setSocketTimeout(50000)
                // socket超时时间
                .setConnectionRequestTimeout(50000)
                // 超时时间
                .setAuthenticationEnabled(true).setExpectContinueEnabled(true)
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", createSSLConnSocketFactory()).build();

        // 设置连接池
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        connManager.setMaxTotal(50); // 设置连接池大小
        connManager.setDefaultMaxPerRoute(50);

        httpClient = HttpClients.custom().setConnectionManager(connManager).setDefaultRequestConfig(requestConfig)
                .build();
    }

    // 并发控制信息存储对象
    private static Map<String, ConcurrencyControlDTO> concurrencyMap = new ConcurrentHashMap<String, ConcurrencyControlDTO>();

    public static Map<String, ConcurrencyControlDTO> getConcurrencyMap() {
        return concurrencyMap;
    }

    public static void setConcurrencyMap(Map<String, ConcurrencyControlDTO> concurrencyMap) {
        HttpUtils.concurrencyMap = concurrencyMap;
    }

    /**
     * 初始化并发参数
     *
     * @param url    域名
     * @param number 最大并发数
     */
    public static void initConcurrency(String url, long number) {
        if (StringUtils.isNotEmpty(url) && number >= 0) {
            ConcurrencyControlDTO hostConcurrency = concurrencyMap.get(url);
            if (hostConcurrency != null) {
                hostConcurrency.getMaxConcurrencyNum().set(number);
            } else {
                hostConcurrency = new ConcurrencyControlDTO();
                hostConcurrency.getMaxConcurrencyNum().set(number);
                concurrencyMap.put(url, hostConcurrency);
            }
        }
    }

    /**
     * 发送Get
     *
     * @param url     请求地址
     * @param headers 请求头
     * @param params  请求参数
     * @return
     */
    public static String doGet(String url, Map<String, Object> headers, Map<String, Object> params) {
        String result = null;
        HttpGet httpGet = null;

        try {
            if (params != null) { // 封装get参数
                StringBuffer param = new StringBuffer();
                for (String key : params.keySet()) {
                    if (param.length() == 0)
                        param.append("?");
                    else
                        param.append("&");
                    param.append(key).append("=").append(URLEncoder.encode(params.get(key).toString(), ENCODEING));
                }
                url += param;
            }

            httpGet = new HttpGet(url);

            if (headers != null) { // 封装header
                for (String key : headers.keySet()) {
                    httpGet.setHeader(key, headers.get(key).toString());
                }
            }

            HttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, ENCODEING);
                }
            }
        } catch (IOException e) {
            log.error("HttpClient: doGet error.url=" + url + ",headers=" + headers + ",params=" + params, e);
        } finally {
            if (httpGet != null) {
                httpGet.releaseConnection();
            }
        }

        return result;
    }

    /**
     * 发送get请求 返回byte[]
     *
     * @param url
     * @param headers
     * @param params
     * @return
     */
    public static byte[] doGetForBytes(String url, Map<String, Object> headers, Map<String, Object> params) {
        byte[] result = null;
        HttpGet httpGet = null;
        try {
            if (params != null) { // 封装get参数
                StringBuffer param = new StringBuffer();
                for (String key : params.keySet()) {
                    if (param.length() == 0)
                        param.append("?");
                    else
                        param.append("&");
                    param.append(key).append("=").append(URLEncoder.encode(params.get(key).toString(), ENCODEING));
                }
                url += param;
            }

            httpGet = new HttpGet(url);

            if (headers != null) { // 封装header
                for (String key : headers.keySet()) {
                    httpGet.setHeader(key, headers.get(key).toString());
                }
            }

            HttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toByteArray(entity);
                }
            }
        } catch (IOException e) {
            log.error("HttpClient: doGet error.url=" + url + ",headers=" + headers + ",params=" + params, e);
        } finally {
            if (httpGet != null) {
                httpGet.releaseConnection();
            }
        }

        return result;
    }

    /**
     * 发送Post请求
     *
     * @param url     请求URL
     * @param headers 请求头
     * @param params  请求参数
     * @return
     */
    public static String doPostFormData(String url, Map<String, Object> headers, Map<String, Object> params) {
        try {
            HttpEntity entity = null;
            if (params != null) { // 封装请求参数
                List<NameValuePair> pairList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    String value = "";
                    if (CommonFuntions.isNotEmptyObject(entry.getValue())) {
                        value = entry.getValue() + "";
                    }
                    NameValuePair pair = new BasicNameValuePair(entry.getKey(), value);
                    pairList.add(pair);
                }
                entity = new UrlEncodedFormEntity(pairList, ENCODEING);
            }
            return doPost(url, headers, entity);
        } catch (Exception e) {
            log.error("HttpClient: doPost error.url=" + url + ",headers=" + headers + ",params=" + params, e);
        }
        return null;
    }

    /**
     * 发送POST请求
     *
     * @param url     请求URL
     * @param headers 请求头
     * @param str     请求内容
     * @return
     */
    public static String doPost(String url, Map<String, Object> headers, String str) {
        if (headers == null) {
            headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
        }
        Object obj = headers.get("Content-Type");
        if (CommonFuntions.isEmptyObject(obj)) {
            headers.put("Content-Type", "application/json");
        }
        try {
            HttpEntity entity = null;
            if (StringUtils.isNotBlank(str)) { // 封装请求参数
                entity = new StringEntity(str.toString(), ENCODEING);
            }
            return doPost(url, headers, entity);
        } catch (Exception e) {
            log.error("HttpClient: doPost error.url=" + url + ",headers=" + headers + ",str=" + str, e);
        }
        return null;
    }

    /**
     * 发送post
     *
     * @param url           请求URL
     * @param headers       请求头
     * @param requestEntity 请求信息对象
     * @return
     */
    private static String doPost(String url, Map<String, Object> headers, HttpEntity requestEntity) throws Exception {
        HttpPost httpPost = new HttpPost(url);

        try {
            if (requestEntity != null) {
                httpPost.setEntity(requestEntity);
            }

            if (headers != null) { // 封装header
                for (String key : headers.keySet()) {
                    httpPost.setHeader(key, headers.get(key).toString());
                }
            }

            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity, ENCODEING);
                }
            } else {
                log.error("HttpClient: doPost error.statusCode=" + statusCode + "url=" + url + ",headers=" + headers
                        + ",requestEntity=" + requestEntity);
                throw new Exception();
            }
        } finally {
            httpPost.releaseConnection();
        }

        return null;
    }

    /**
     * 创建私有SSL连接工厂，忽略证书
     *
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            // Implementation of a trust manager for X509 certificates
            X509TrustManager tm = new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };

            ctx.init(null, new TrustManager[]{tm}, null);
            sslsf = new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);
        } catch (Exception e) {
            log.error("HttpClient: create SSL socket factory error.", e);
        }

        return sslsf;
    }

    /**
     * 发送Get，控制并发
     *
     * @param url     请求地址
     * @param headers 请求头
     * @param params  请求参数
     * @return
     * @throws Exception
     */
    public static ResultDTO<String> doGetControl(String url, Map<String, Object> headers, Map<String, Object> params) {
        ResultDTO<String> result = new ResultDTO<String>();

        // 请求前并发数处理
        ResultDTO<String> checkResult = reqConcurrencyHandle(url);
        if (!checkResult.isSuccess()) {
            return result.set(checkResult.getErrCode(), checkResult.getErrDesc());
        }
        String httpResult = null;
        long resBeginTime = System.currentTimeMillis();
        try {
            // 发送请求
            httpResult = doGet(url, headers, params);
        } catch (Exception e) {
            log.error(e);
        } finally {
            // 请求后并发数处理
            RespConcurrencyHandle(url, httpResult, resBeginTime);
        }

        return result.set(ResultCodeEnum.SUCCESS, "", httpResult);
    }

    /**
     * 发送Post，控制并发
     *
     * @param url     请求地址
     * @param headers 请求头
     *                请求参数
     * @return
     * @throws Exception
     */
    public static ResultDTO<String> doPostControl(String url, Map<String, Object> headers, String str) {
        ResultDTO<String> result = new ResultDTO<String>();

        // 请求前并发数处理
        ResultDTO<String> checkResult = reqConcurrencyHandle(url);
        if (!checkResult.isSuccess()) {
            return result.set(checkResult.getErrCode(), checkResult.getErrDesc());
        }

        String httpResult = null;
        long resBeginTime = System.currentTimeMillis();
        try {
            // 发送请求
            httpResult = doPost(url, headers, str);
        } catch (Exception e) {
            log.error(e);
        } finally {
            // 请求后并发数处理
            RespConcurrencyHandle(url, httpResult, resBeginTime);
        }

        return result.set(ResultCodeEnum.SUCCESS, "", httpResult);
    }

    /**
     * 请求前并发数处理
     *
     * @param url 请求url
     * @return
     */
    private static ResultDTO<String> reqConcurrencyHandle(String url) {
        ResultDTO<String> result = new ResultDTO<String>();

        // 获取去除参数的url
        String urlPrefix = getUrlPrefix(url, 2);

        ConcurrencyControlDTO hostConcurrency = null;
        if (concurrencyMap != null) {
            Iterator<Entry<String, ConcurrencyControlDTO>> iter = concurrencyMap.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<String, ConcurrencyControlDTO> entry = iter.next();
                if (urlPrefix.startsWith(entry.getKey())) {
                    hostConcurrency = entry.getValue();
                    break;
                }
            }
        }
        if (hostConcurrency == null) {
            // 获取去除路径和参数的url
            String urlHost = getUrlPrefix(url, 1);
            // 初始化最大并发数量
            initConcurrency(urlHost, 0);
            hostConcurrency = concurrencyMap.get(urlHost);

        }

        long concurrencyNum = hostConcurrency.getConcurrencyNum().get();// 并发数
        long maxConcurrencyNum = hostConcurrency.getMaxConcurrencyNum().get();// 最大并发数
        // 并发数达到最大并发数，直接返回
        if (concurrencyNum >= maxConcurrencyNum && maxConcurrencyNum > 0) {
            log.debug("请求并发数达到最大值，url：" + url + "，并发数：" + concurrencyNum + "，最大并发数：" + maxConcurrencyNum);
            return result.set(ResultCodeEnum.ERROR_HANDLE, "系统频繁，请稍后重试！");
        }
        ConcurrencyControlDTO reqConcurrency = null;
        if (hostConcurrency.getReqMap() != null) {
            reqConcurrency = hostConcurrency.getReqMap().get(urlPrefix);
        }
        if (reqConcurrency == null) {
            reqConcurrency = new ConcurrencyControlDTO();
            if (hostConcurrency.getReqMap() != null) {
                hostConcurrency.getReqMap().put(urlPrefix, reqConcurrency);
            } else {
                Map<String, ConcurrencyControlDTO> reqMap = new ConcurrentHashMap<String, ConcurrencyControlDTO>();
                reqMap.put(urlPrefix, reqConcurrency);
                hostConcurrency.setReqMap(reqMap);
            }
        }
        // 总并发数加1
        hostConcurrency.getConcurrencyNum().incrementAndGet();
        // 请求并发数加1
        reqConcurrency.getConcurrencyNum().incrementAndGet();
        // 域名请求数加1
        hostConcurrency.getTotalReqNum().incrementAndGet();
        // URL请求数加1
        reqConcurrency.getTotalReqNum().incrementAndGet();

        // 域名并发数
        long conNum = hostConcurrency.getConcurrencyNum().get();
        // 域名并发数峰值
        long conPeakNum = hostConcurrency.getConcurrencyPeak().get();
        if (conNum > conPeakNum) {
            hostConcurrency.getConcurrencyPeak().set(conNum);
        }
        // URL并发数
        long reqNum = reqConcurrency.getConcurrencyNum().get();
        // URL并发数峰值
        long reqPeakNum = reqConcurrency.getConcurrencyPeak().get();
        if (reqNum > reqPeakNum) {
            reqConcurrency.getConcurrencyPeak().set(reqNum);
        }

        return result.set(ResultCodeEnum.SUCCESS, "");
    }

    /**
     * 请求完成后并发数处理
     * <p>
     * 域名并发对象
     * 请求并发对象
     *
     * @param httpResult   请求响应信息
     * @param resBeginTime 请求开始时间
     */
    private static void RespConcurrencyHandle(String url, String httpResult, long resBeginTime) {
        long endTime = System.currentTimeMillis();

        String urlHost = getUrlPrefix(url, 1);
        String urlPath = getUrlPrefix(url, 2);
        ConcurrencyControlDTO hostConcurrency = concurrencyMap.get(urlHost);
        ConcurrencyControlDTO reqConcurrency = hostConcurrency.getReqMap().get(urlPath);

        // 域名总耗时增加
        hostConcurrency.getReqTime().addAndGet(endTime - resBeginTime);
        // 请求总耗时增加
        reqConcurrency.getReqTime().addAndGet(endTime - resBeginTime);
        // 请求并发数减1
        reqConcurrency.getConcurrencyNum().decrementAndGet();
        // 总并发数减1
        hostConcurrency.getConcurrencyNum().decrementAndGet();
        if (httpResult != null) {
            // 域名请求成功数加1
            hostConcurrency.getSuccessReqNum().incrementAndGet();
            // 请求成功数加1
            reqConcurrency.getSuccessReqNum().incrementAndGet();
        } else {
            // 域名请求数减1
            hostConcurrency.getFailReqNum().incrementAndGet();
            // 请求失败数加1
            reqConcurrency.getFailReqNum().incrementAndGet();
        }
    }

    /**
     * 获取url的前缀
     *
     * @param length 1:获取到域名止；2：获取到路径止
     * @return
     */
    public static String getUrlPrefix(String urlStr, long length) {

        try {
            URL url = new URL(urlStr);
            if (length == 1) {
                return url.getProtocol() + "://" + url.getHost();
            } else if (length == 2) {
                return url.getProtocol() + "://" + url.getHost() + url.getPath();
            }

        } catch (MalformedURLException e) {
            log.error("http:url error,url=" + urlStr + "  Exception：" + e);
        }
        return urlStr;
    }


    /**
     * 获取应用服务路径url(格式http://xxx.cc.com 或者 http://www.xx.com/iwifi)
     *
     * @param request
     * @return
     */
    public static String getCtx(HttpServletRequest request) {
        String ctx = "";
        int port = request.getServerPort();
        String strport = "";
        if (port != 80) {
            strport = ":" + port;
        }
        ctx = request.getScheme() + "://" + request.getServerName() + strport + request.getContextPath();
        return ctx;
    }

    /**
     * 判断是否为ajax请求
     *
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return (request.getHeader("x-requested-with") == null) ? false : true;
    }

    /**
     * 获取cookie路径
     *
     * @param request
     * @return
     */
    public static String getCookiePath(HttpServletRequest request) {
        String path = request.getContextPath();
        if (StringUtils.isBlank(path)) {
            path = "/";
        }
        return path;
    }

    /**
     * 获取客户端的真实IP地址(可能通过代理)
     *
     * @param request
     * @return
     */
    public static String getRealIP(HttpServletRequest request) {
        String ip = "";
        if (request != null) {
            ip = request.getHeader("cdn-src-ip");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("x-forwarded-for");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("x-real-ip");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("proxy-client-ip");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("wl-proxy-client-ip");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            if (ip != null) {
                ip = ip.replaceAll(";", ",");
                String[] ips = ip.split(",");
                if (ips.length < 1) {
                    ip = "";
                } else if (ips.length == 1) {
                    ip = ips[0];
                } else if (!ips[0].equalsIgnoreCase("127.0.0.1")) {
                    ip = ips[0];
                } else {
                    ip = ips[1];
                }
            }
        }
        if (ip != null && ip.length() > 0) {
            ip = ip.trim();
        }
        return ip;
    }

    /**
     * 获得本地服务器Ip地址（局域网IP）
     *
     * @return
     * @throws UnknownHostException
     */
    public static String getLocalIp() throws UnknownHostException {
        InetAddress localHostAddress = InetAddress.getLocalHost();
        return localHostAddress.getHostAddress();
    }

    /**
     * 获取request请求的参数
     *
     * @param request
     * @return
     */
    public static String getRequestParams(HttpServletRequest request) {
        StringBuffer params = new StringBuffer();
        if (request != null) {
            Enumeration<String> names = request.getParameterNames();
            if (names != null) {
                while (names.hasMoreElements()) {
                    String name = names.nextElement();
                    try {
                        params.append("&" + name + "=" + URLEncoder.encode(request.getParameter(name), "utf-8"));
                        // params.append("&"+name+"="+request.getParameter(name))
                        // ;
                    } catch (Exception e) {
                    }
                }
            }
        }
        return params.length() > 0 ? params.substring(1) : "";
    }

    /**
     * 获取request所有参数，转换为JSONObject
     *
     * @param request
     * @return
     */
    public static JSONObject getRequestParamsJson(HttpServletRequest request) {
        Map<String, String[]> paramsMap = request.getParameterMap();

        JSONObject jsonObject = new JSONObject();
        // 循环
        Iterator<Entry<String, String[]>> iter = paramsMap.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, String[]> entry = iter.next();

            String key = entry.getKey();
            String value = entry.getValue()[0];

            jsonObject.put(key, value);
        }
        return jsonObject;
    }

    public static String getToken(HttpServletRequest request) {
        final String token = request.getHeader("token") == null ? request.getParameter("token") : request.getHeader("token");
        return token;
    }

    public static void main(String[] args) throws Exception {
        String str = "http://119.3.15.112:8661/actTem?actId=ACTID&stbid=STBID";

        URL url = new URL(str);
        System.out.println(url);
        System.out.println(url.getProtocol());
        System.out.println(url.getHost());
        System.out.println(url.getPath());
        System.out.println(url.getProtocol() + "://" + url.getHost() + url.getPath());
    }
}
