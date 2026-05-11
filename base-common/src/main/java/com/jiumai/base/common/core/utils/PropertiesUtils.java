package com.jiumai.base.common.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author ：cz
 * @date ：Created in 2020/3/4 17:28
 */
@Slf4j
@Component
public class PropertiesUtils {
    private static Properties props = null;

    static {
        loadProperty();
    }

    /**
     * 初始化加载配置文件
     */
    synchronized private static void loadProperty() {
        InputStream in = null;
        props = new Properties();
        try {

            in = PropertiesUtils.class.getClassLoader().getResourceAsStream("application.properties");
            props.load(new InputStreamReader(in, StandardCharsets.UTF_8));

            String active = getProperty("spring.profiles.active", "dev").trim();

            in = PropertiesUtils.class.getClassLoader().getResourceAsStream("application-" + active + ".properties");
            props.load(new InputStreamReader(in, StandardCharsets.UTF_8));

            if (props == null || props.isEmpty()) {
                log.error("properties文件不存在！");
            }
        } catch (FileNotFoundException e) {
            log.error("properties not found!", e);
        } catch (IOException e) {
            log.error("IOException", e);
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                log.error("properties close Exception!");
            }
        }
        log.info("load properties over...........");

    }

    /**
     * 获取配置文件属性值，可添加默认值
     *
     * @param key
     * @return
     */
    public static String getProperty(String key, String defaultValue) {
        if (null == props) {
            loadProperty();
        }
        if (props.containsKey(key)) {
            String value = props.getProperty(key);
            if (StringUtils.isBlank(value)) {
                value = value.trim();
            }

            return value;
        } else {
            return defaultValue;
        }
    }

    /**
     * 获取配置文件属性值
     *
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        return getProperty(key, null);
    }


}
