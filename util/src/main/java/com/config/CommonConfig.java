package com.config;

import javax.swing.filechooser.FileSystemView;

/**
 * 定义整个辅助类中的有关路径的配置
 * @author Eddie Zhang
 */
public class CommonConfig {
    // 文件相关路径位置
    // 实例文件路径
    public static final String DEMO_FILE_LOCATION = "C:/Users/Administrator/Desktop/gitRepository/util/src/main/resources/file/demo/";
    // 模板文件路径
    public static final String INTERFACE_TMP_LOCATION = "C:/Users/Administrator/Desktop/gitRepository/util/src/main/resources/file/doc/interface/";
    // 桌面路径
    public static final String DESKTOP_LOCATION = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
}
