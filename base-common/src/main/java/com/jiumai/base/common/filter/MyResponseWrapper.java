package com.jiumai.base.common.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MyResponseWrapper extends HttpServletResponseWrapper {

    //我们获取响应数据的流
    private final ByteArrayOutputStream byteArrayOutputStream;

    public MyResponseWrapper(HttpServletResponse response) {
        super(response);
        byteArrayOutputStream = new ByteArrayOutputStream();
    }
    @Override
    public PrintWriter getWriter() {
        return new PrintWriter(new OutputStreamWriter(byteArrayOutputStream));
    }
    @Override
    public ServletOutputStream getOutputStream() {
        return new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {

            }

            //这个最重要，是在这里将响应数据写到这个类的byteArrayOutputStream中的，
            //这样我们后续才能拿到响应数据
            //也正是因为这样，实际的HttpResponse对象中是没有被写入数据的
            @Override
            public void write(int b)  {
                byteArrayOutputStream.write(b);
            }
            @Override
            public void write(byte[] b) throws IOException {
                byteArrayOutputStream.write(b);
            }
        };
    }

    public byte[] toByteArray(){
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return bytes;
    }
}
