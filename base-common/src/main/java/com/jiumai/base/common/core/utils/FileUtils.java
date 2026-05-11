package com.jiumai.base.common.core.utils;

import com.jiumai.base.common.core.SysLog;
import com.jiumai.base.common.core.SysLogFactory;
import com.jiumai.base.common.core.dto.FileInfoDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;

/**
 * 文件处理工具类
 *
 * @version 1.0
 */
public class FileUtils {

    private static SysLog log = SysLogFactory.getLogger(FileUtils.class);

    private static String filePath; // 文件保存的物理路径
    private static String filePrefix; // 文件的虚拟映射前缀

    static {
        loadProps();
    }

    synchronized static private void loadProps() {
        filePath = PropertiesUtils.getProperty("file.path");
        filePrefix = PropertiesUtils.getProperty("file.prefix");
    }

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @author liqg @creationDate. 2015-5-30 下午3:21:59
     */
    public static FileInfoDTO uploadFile(MultipartFile file) {
        if (file != null) {
            InputStream in = null;
            OutputStream out = null;
            try {
                int BUFFER_SIZE = 16 * 1024;

                String fileName = file.getOriginalFilename();
                String newFilePath = genFilePath(fileName);
                File dstFile = new File(newFilePath);

                in = new BufferedInputStream(file.getInputStream(), BUFFER_SIZE);
                out = new BufferedOutputStream(new FileOutputStream(dstFile), BUFFER_SIZE);
                byte[] buffer = new byte[BUFFER_SIZE];
                int len = 0;
                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                // 文件信息
                FileInfoDTO fileInfo = new FileInfoDTO();
                fileInfo.setFileName(fileName);
                fileInfo.setPath(newFilePath);
                fileInfo.setSize(file.getSize());
                fileInfo.setUrl(filePrefix + newFilePath.replaceFirst(filePath, ""));
                return fileInfo;
            } catch (Exception e) {
                log.error("文件上传异常", e);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        /* 异常处理 */
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        /* 异常处理 */
                    }
                }
            }
        }
        return null;
    }

    /**
     * 上传网络文件
     *
     * @param fileUrl 文件地址
     * @return
     * @throws Exception
     */
    public static String uploadFile(String fileUrl) throws Exception {
        URL url = new URL(fileUrl);
        //打开url连接
        URLConnection connection = url.openConnection();
        //请求超时时间
        connection.setConnectTimeout(5000);
        //输入流
        InputStream in = connection.getInputStream();
        OutputStream out = null;
        try {
            int BUFFER_SIZE = 16 * 1024;
            String newFilePath = genFilePath(fileUrl);
            File dstFile = new File(newFilePath);
            out = new BufferedOutputStream(new FileOutputStream(dstFile), BUFFER_SIZE);
            byte[] buffer = new byte[BUFFER_SIZE];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }

            return filePrefix + newFilePath.replaceFirst(filePath, "");
        } catch (Exception e) {
            log.error("文件上传异常", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    /* 异常处理 */
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    /* 异常处理 */
                }
            }
        }
        return null;

    }


    /**
     * 上传文件，指定路径，指定文件名
     *
     * @param file
     * @param directory   路径
     * @param newFileName 新的文件名
     * @return
     */
    public static FileInfoDTO uploadFile(MultipartFile file, String directory, String newFileName) {
        if (file != null) {
            InputStream in = null;
            OutputStream out = null;
            try {
                int BUFFER_SIZE = 16 * 1024;
                String fileName = file.getOriginalFilename();
                // 拼接完整路径
                if (!directory.startsWith("/") && !filePath.endsWith("/")) {
                    directory = "/" + directory;
                }
                if (!directory.endsWith("/")) {
                    directory += "/";
                }
                String path = filePath + directory; // 文件保存的物理路径
                File dstFile = new File(path);
                if (!dstFile.exists()) {
                    dstFile.mkdirs();
                }
                // 文件名
                newFileName = newFileName + fileName.substring(fileName.lastIndexOf("."));
                dstFile = new File(path + newFileName);

                in = new BufferedInputStream(file.getInputStream(), BUFFER_SIZE);
                out = new BufferedOutputStream(new FileOutputStream(dstFile), BUFFER_SIZE);
                byte[] buffer = new byte[BUFFER_SIZE];
                int len = 0;
                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                // 文件信息
                FileInfoDTO fileInfo = new FileInfoDTO();
                fileInfo.setFileName(fileName);
                fileInfo.setPath(path);
                fileInfo.setSize(file.getSize());
                if (!directory.startsWith("/") && !filePrefix.endsWith("/")) {
                    directory = "/" + directory;
                }
                fileInfo.setUrl(filePrefix + directory + newFileName);

                return fileInfo;
            } catch (Exception e) {
                log.error("文件上传异常", e);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        /* 异常处理 */
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        /* 异常处理 */
                    }
                }
            }
        }
        return null;
    }

    /**
     * 保存base64
     *
     * @param base64Code
     * @param fileName   结尾是文件拓展名的字符串
     * @return 文件相对路径
     */
    public static String saveBase64File(String base64Code, String fileName) {
        if (StringUtils.isBlank(base64Code)) {
            return null;
        }
        base64Code = base64Code.substring(base64Code.indexOf(",") + 1);
        String newFilePath = null;
        try {
            byte[] buffer = DatatypeConverter.parseBase64Binary(base64Code);
            newFilePath = genFilePath(fileName);
            FileOutputStream out = new FileOutputStream(newFilePath);
            out.write(buffer);
            out.close();
        } catch (IOException e) {
            log.error("base64文件保存失败：", e);
        }

        return filePrefix + newFilePath.replaceFirst(filePath, "");
    }

    /**
     * @param fileName 结尾是文件拓展名的字符串
     * @return
     */
    public static String genFilePath(String fileName) {
        // 拼接完整路径
        Calendar c = Calendar.getInstance();
        String directory = c.get(Calendar.YEAR) + "/" + (c.get(Calendar.MONTH) + 1) + "/"
                + c.get(Calendar.DATE) + "/";
        if (!filePath.endsWith("/")) {
            directory = "/" + directory;
        }
        String path = filePath + directory;
        File dstFile = new File(path);
        if (!dstFile.exists()) {
            dstFile.mkdirs();
        }
        // 文件名
        String fileSuffix = fileName.substring(fileName.lastIndexOf(".")); // 文件名后缀
        String newFileName = c.getTimeInMillis() + "_" + RandomUtils.generateNumString(3) + fileSuffix;

        return path + newFileName;
    }

    /**
     * 文件删除
     *
     * @param url 相对路径（数据库保存的路径）
     * @author liqg @creationDate. 2015-5-30 下午3:28:59
     */
    public static void deleteFile(String url) {
        if (!url.startsWith("/") && !filePath.endsWith("/")) {
            url = "/" + url;
        }
        String path = filePath + url.replaceFirst(filePrefix, "");

        File file = new File(path);
        if (file.isFile() && file.exists()) {
            file.delete();
        }
    }

    public static String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        FileUtils.filePath = filePath;
    }

    public static String getFilePrefix() {
        return filePrefix;
    }

    public void setFilePrefix(String filePrefix) {
        FileUtils.filePrefix = filePrefix;
    }
}
