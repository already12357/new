package com.zhq.util;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceUtil {
    /**
     * 关闭资源 (流, 连接 )
     * @param resources 用于关闭的资源数组
     */
    public static void closeResources(Closeable...resources) {
        try {
            for (Closeable resource : resources) {
                if (resource != null) {
                    resource.close();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从资源文件夹下读取对应的文件数据
     * @param filePath 从 resources 开始的文件路径
     * @return
     */
    public static InputStream loadFromResources(String filePath) {
        InputStream in = ResourceUtil.class.getResourceAsStream(filePath);
        return in;
    }

    /**
     * 从资源文件夹 ( resources ) 下读取对应的 .properties 文件
     * @param propPath 从 resources 下开始的路径
     * @return
     */
    public static Properties loadPropertiesFromResources(String propPath) {
        InputStream in = null;
        Properties props = new Properties();
        try {
            in = ResourceUtil.loadFromResources(propPath);
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            closeResources(in);
        }

        return props;
    }

    /**
     * 从目标类路径文件夹 ( target/classes/ ) 下读取对应的文件数据
     * @param filePath 从 src 开始的文件路径
     * @return
     */
    public static InputStream loadFromClassPath(String filePath) {
        InputStream in = ResourceUtil.class.getClassLoader().getResourceAsStream(filePath);
        return in;
    }

    public static Properties loadPropertiesFromFile(File propFile) {
        Properties prop = new Properties();

        try {

        }
        catch (Exception e) {

        }
        finally {

        }

        return prop;
    }
}
