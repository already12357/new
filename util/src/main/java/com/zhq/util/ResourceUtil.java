package com.zhq.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

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
     * 从类路径文件夹下读取对应的文件数据
     * @param filePath 从 src 开始的文件路径
     * @return
     */
    public static InputStream loadFromClassPath(String filePath) {
        InputStream in = ResourceUtil.class.getClassLoader().getResourceAsStream(filePath);
        return in;
    }
}
