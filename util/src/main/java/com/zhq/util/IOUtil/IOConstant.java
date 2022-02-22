package com.zhq.util.IOUtil;

import java.io.File;
import java.util.Base64;

/**
 * 文件魔数头的信息定义，使用枚举类型定义
 * @author Eddie Zhang
 */
public class IOConstant {
    // 各种文件格式类型
    // 图片
    public static final String BMP = "bmp";
    public static final String JPG = "jpg";
    public static final String GIF = "gif";
    public static final String PNG = "png";
    // office 文档
    public static final String DOC = "doc";
    public static final String DOCX = "docx";
    public static final String PDF = "pdf";
    public static final String PPT = "ppt";
    public static final String PPTX = "pptx";
    public static final String XLS = "xls";
    public static final String XLSX = "xlsx";
    // 文本文件
    public static final String HTML = "html";
    public static final String XML = "xml";
    // 音频文件
    public static final String WAV = "wav";
    public static final String AVI = "avi";
    public static final String MOV = "mov";
    // 压缩文件
    public static final String ZIP = "zip";
    public static final String RAR = "rar";

    /**
     * 按所属文件大类划分，便于查看
     */
    // 图片
    public static final String TYPE_IMAGE = "image";
    // 纯文本
    public static final String TYPE_TEXT = "text";
    // 音频，视频等

    // office 文档
    public static final String TYPE_OFFICE = "office";


    /**
     * Office 版本类型
     */
    public static final String OFFICE_2010 = "office_2007";
    public static final String OFFICE_2007 = "office_2007";
}

