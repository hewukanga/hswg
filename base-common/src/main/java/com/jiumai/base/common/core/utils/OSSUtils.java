package com.jiumai.base.common.core.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.UUID;

/**
 * @ Author     ：cz
 * @ Date       ：Created in 2021-01-0621:51
 * @ Description：阿里云oss
 * @ Modified By：
 */
@Slf4j
public class OSSUtils {

    /**
     * 阿里云的配置参数
     */
    private static final String accessKeyId;
    private static final String accessKeySecret;
    private static final String endpoint;
    private static final String bucketName;
    /**
     * 存储在OSS中的前缀名
     */
    private static String file_prefix;

    /**
     * 静态块
     */
    static {
        //初始化AccessKey
        accessKeyId = PropertiesUtils.getProperty("aliyun.oss.AccessKeyID");
        //初始化AccessKeySecret
        accessKeySecret = PropertiesUtils.getProperty("aliyun.oss.AccessKeySecret");
        //初始化Endpoint
        endpoint = PropertiesUtils.getProperty("aliyun.oss.EndPoint");
        //初始化bucketName
        bucketName = PropertiesUtils.getProperty("aliyun.oss.Buckets");
        //初始化前缀
        file_prefix = PropertiesUtils.getProperty("aliyun.oss.prefix");
    }

    /**
     * 上传文件流
     *
     * @param oranFileName 上传到服务器上的文件路径和名称
     * @param file         来自本地的文件或者文件流
     */
    public static String uploadFileInputSteam(MultipartFile file, String oranFileName) {

        // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg
        String objectName = null;
        if (StringUtils.isEmpty(oranFileName)) {
            objectName = getRandomFileName(file.getOriginalFilename());
        } else {
            objectName = getRandomFileName(oranFileName);
        }

        // 创建OSSClient实例。

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传文件流
        try (InputStream inputStream = new FileInputStream(transferToFile(file))) {
            // 设置header
            ObjectMetadata header = getHeader(objectName);
            //上传到OSS
            ossClient.putObject(bucketName, objectName, inputStream,header);
            // 关闭OSSClient。
            ossClient.shutdown();
        } catch (Exception ex) {
            // 关闭OSSClient。
            ossClient.shutdown();
            log.error("文件上传失败", ex);
            return null;
        }

        //返回文件在服务器上的全路径+名称
//        return getRealName(objectName);
        return objectName;
    }


    /**
     * 上传文件流
     *
     * @param oranFileName 上传到服务器上的文件路径和名称
     * @param file         来自本地的文件或者文件流
     */
    public static String uploadFileInputSteam(String oranFileName, File file) {

        // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg
        String objectName = null;
        if (StringUtils.isEmpty(oranFileName)) {
            objectName = getRandomFileName(file.getName());
        } else {
            objectName = getRandomFileName(oranFileName);
        }
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        try (InputStream inputStream = new FileInputStream(file)) {
            //上传到OSS
            ossClient.putObject(bucketName, objectName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
        } catch (Exception ex) {
            // 关闭OSSClient。
            ossClient.shutdown();
            log.error("文件上传失败", ex);
            return null;
        }

        //返回文件在服务器上的全路径+名称
        return objectName;
    }

    /**
     * 设置文件的请求头
     * @param fileName
     * @return
     */
    private static ObjectMetadata getHeader(String fileName){
        //创建上传Object的Metadata
        ObjectMetadata metadata = new ObjectMetadata();
        //上传的文件的长度
//        metadata.setContentLength(is.available());
        //指定该Object被下载时的网页的缓存行为
        metadata.setCacheControl("public");
        //指定该Object下设置Header
//        metadata.setHeader("Pragma", "no-cache");
        //指定该Object被下载时的内容编码格式
//        metadata.setContentEncoding("utf-8");
        //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
        //如果没有扩展名则填默认值application/octet-stream
        String contentType = getContentType(fileName);
        if (contentType != null) {
            metadata.setContentType(contentType);
        }
        //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
//        metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
        metadata.setContentDisposition("inline");
        return metadata;
    }
    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     *
     * @param fileName 文件名
     * @return 文件的contentType
     */
    private static String getContentType(String fileName) {
        //文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        String lowerCaseFileExt = fileExtension.toLowerCase();
        switch (lowerCaseFileExt){
            case ".jpg" : return "application/x-jpg";
            case ".jpeg" : return "image/jpeg";
            case ".png" : return "application/x-png";
            case ".bmp" : return "application/x-bmp";
            case ".gif" : return "image/gif";
            case ".pdf" : return "application/pdf";
        }
        //默认返回类型
        return null;
    }
    /**
     * 获取图片的URL头信息
     *
     * @return 返回url头信息
     */
    private static String getURLHead() {
        //从哪个位置截取
        int cutPoint = endpoint.lastIndexOf('/') + 1;
        //http头
        String head = endpoint.substring(0, cutPoint);
        //服务器地址信息
        String tail = endpoint.substring(cutPoint);
        //返回结果
        return head + bucketName + "." + tail + "/";
    }

    /**
     * 获取存储在服务器上的地址
     *
     * @param oranName 文件名
     * @return 文件URL
     */
    private static String getRealName(String oranName) {
        return getURLHead() + oranName;
    }

    /**
     * 获取一个随机的文件名
     *
     * @param fileName 初始的文件名
     * @return 返回加uuid后的文件名
     */
    private static String getRandomFileName(String fileName) {
        //获取一个uuid 去掉-
        String uuid = UUID.randomUUID().toString().replace("-", "");

        Calendar c = Calendar.getInstance();
        String directory = c.get(Calendar.YEAR) + "/" + (c.get(Calendar.MONTH) + 1) + "/"
                + c.get(Calendar.DATE) + "/";
        if (StringUtils.isEmpty(fileName)) {
            return file_prefix + directory + uuid;
        }
        //查一下是否带路径
        int cutPoint = fileName.lastIndexOf("/") + 1;
        //如果指定了目录
        if (cutPoint != 0) {
            //掐头 如果开头是/ 则去掉
            String head = fileName.indexOf("/") == 0 ? fileName.substring(1, cutPoint) : fileName.substring(0, cutPoint);
            //去尾
            String tail = fileName.substring(cutPoint);
            //返回正确的带路径的图片名称
            return file_prefix + head + uuid + tail;
        }
        //未指定目录 返回默认目录：2020/12/1/uuid.jpg
        return file_prefix + directory + uuid + fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * MultipartFile2File
     *
     * @param multipartFile
     * @return
     */
    private static File transferToFile(MultipartFile multipartFile) {
        //选择用缓冲区来实现这个转换即使用java 创建的临时文件 使用 MultipartFile.transferto()方法 。
        File file = null;
        try {
            //获取文件名
            String originalFilename = multipartFile.getOriginalFilename();
            if(CommonFuntions.isNotEmptyObject(originalFilename)){
                //获取最后一个"."的位置
                int cutPoint = originalFilename.lastIndexOf(".");
                //获取文件名
                String prefix = originalFilename.substring(0, cutPoint);
                //获取后缀名
                String suffix = originalFilename.substring(cutPoint + 1);
                //创建临时文件
                file = File.createTempFile(prefix, suffix);
                //multipartFile2file
                multipartFile.transferTo(file);
                //删除临时文件
                file.deleteOnExit();
            }
        } catch (IOException e) {
            log.error("文件上传失败", e);
        }
        return file;
    }


}