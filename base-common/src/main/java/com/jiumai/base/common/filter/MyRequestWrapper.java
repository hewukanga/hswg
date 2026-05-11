package com.jiumai.base.common.filter;

import cn.hutool.crypto.symmetric.SM4;
import com.jiumai.base.common.core.exception.BizException;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

/**
 * @Description: 对HttpServletRequest进行重写，
 * 1、用来接收application/json参数数据类型，即@RequestBody注解标注的参数,解决多次读取问题
 * 2、用来解决注解@RequestParam通过POST/PUT/DELETE/PATCH方法传递参数，解决多次读取问题
 * 首先看一下springboot控制器三个注解：
 * 1、@PathVariable注解是REST风格url获取参数的方式，只能用在GET请求类型，通过getParameter获取参数
 * 2、@RequestParam注解支持GET和POST/PUT/DELETE/PATCH方式，Get方式通过getParameter获取参数和post方式通过getInputStream或getReader获取参数
 * 3、@RequestBody注解支持POST/PUT/DELETE/PATCH，可以通过getInputStream和getReader获取参数
 */
public class MyRequestWrapper extends HttpServletRequestWrapper {

    //请求数据
    private String body;
    //请求二进制数据
    private byte[] bytes;

    private Map<String, String[]> bizParams = null;
    private final HttpServletRequest request;

    public MyRequestWrapper(HttpServletRequest request, Map<String, String[]> parameters, SM4 sm4) throws IOException {
        super(request);
        this.request = request;
        // 解密get请求参数，要求格式：url?query=密文，且不支持数组
        if (parameters != null && parameters.get("query") != null && parameters.get("query").length > 0) {
            String[] queries = parameters.get("query");

            String encryptQuery = queries[0];
            String queryStr;
            try {
                queryStr = sm4.decryptStr(encryptQuery);
            } catch (Exception e) {
                throw new BizException("解密失败");
            }
            if (StringUtils.isNotBlank(queryStr)) {
                bizParams = new Hashtable<>();
                String[] split = queryStr.split("&");
                for (String item : split) {
                    String[] itemArr = item.split("=");
                    if(itemArr.length == 1 ){
                        // 防止这种情况:?name=&phone=123
                        continue;
                    }
                    String key = itemArr[0];
                    String value = URLDecoder.decode(itemArr[1], "UTF-8");
                    String[] valueArr = {value};
                    bizParams.put(key, valueArr);
                }
            }
        }
        if (null == this.body) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IOUtils.copy(request.getInputStream(), baos);
            bytes = baos.toByteArray();
            if(bytes.length>0){
                this.body = new String(bytes);
            }
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        //这一步最关键
        //这一步使得后续获取InputStream都是这个对象
        //而在此时我们也把我们自定义的数据塞进去了
        //也就是说后续处理中获取到的参数就是我们此时塞进去的数据
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ServletInputStream inputStream = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            //这里流的读取，就是从我们自定义流中读取数据
            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
        return inputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    /**
     * 获取所有参数名
     *
     * @return 返回所有参数名
     */
    @Override
    public Enumeration<String> getParameterNames() {
        if (bizParams == null) {
            return request.getParameterNames();
        }
        Vector<String> vector = new Vector<>(bizParams.keySet());
        return vector.elements();
    }

    /**
     * 获取指定参数名的值，如果有重复的参数名，则返回第一个的值 接收一般变量 ，如text类型
     *
     * @param name 指定参数名
     * @return 指定参数名的值
     */
    @Override
    public String getParameter(String name) {
        if (bizParams == null) {
            return request.getParameter(name);
        }
        String[] results = bizParams.get(name);
        if (results == null || results.length <= 0) {
            return null;
        } else {
            System.out.println("修改之前： " + results[0]);
            return results[0];
        }
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        if (bizParams == null) {
            return request.getParameterMap();
        }
        return bizParams;
    }

    /**
     * 获取指定参数名的所有值的数组，如：checkbox的所有数据
     * 接收数组变量 ，如checkobx类型
     */
    @Override
    public String[] getParameterValues(String name) {
        if (bizParams == null) {
            return request.getParameterValues(name);
        }
        String[] results = bizParams.get(name);
        if (results == null || results.length <= 0) {
            return null;
        } else {
            return results;
        }
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
