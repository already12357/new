package com.zhq.util.HttpUtil;

import javax.print.attribute.standard.Media;
import java.util.HashMap;
import java.util.Map;

/**
 * 在 Http 中使用的一些常量
 */
public class HttpConstant {
    /**
     * 请求方式
     */
    public static final String METHOD_POST = "post";
    public static final String METHOD_GET = "get";
    public static final String METHOD_PUT = "put";
    public static final String METHOD_DELETE = "delete";
}


/**
 * dataUrl 中存储 文件格式 和 媒体类型 的键值关系
 */
enum MediaMap {
    JPG("jpg", "image/jpeg"),
    BMP("bmp", "image/bmp"),
    PNG("png", "image/png"),
    GIF("gif", "image/gif"),
    TXT("txt", "text/plain"),
    DOC("doc", "application/msword"),
    PDF("pdf", "pplication/pdf"),
    XLSX("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;");


    // 文件格式, 可以与 IOConstant 中的常量对应
    public String fileFormat;
    // dataUrl 中对应的媒体类型
    public String mediaType;

    MediaMap(String fileFormat, String mediaType) {
        this.fileFormat = fileFormat;
        this.mediaType = mediaType;
    }
}
