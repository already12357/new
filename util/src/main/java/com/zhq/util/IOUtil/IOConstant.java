package com.zhq.util.IOUtil;

import java.io.File;

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

// 用于拼接生成 dataUrl 的一些常量
// data:①[<mime type>]②[;charset=<charset>]③[;<encoding>]④,<encoded data>⑤
enum DataUrl {
    URL_IMAGE;
    // 构造函数
    private DataUrl(String mimeType, String encode, ) {

    }
    // mime 文件格式
    private String mimeType;
    // 编码方式

}

// 枚举类对象，存储 文件格式 和 对应的魔数字节
enum MagicBytes {
    // 图片
    JPG("jpg", new byte[]{(byte) 0xff, (byte) 0xd8, (byte) 0xff}),
    BMP("bmp", new byte[]{0x42, 0x4d}),
    GIF("gif", new byte[]{0x47, 0x49, 0x46, 0x38}),
    PNG("png", new byte[]{(byte) 0x89, 0x50, 0x4e, 0x47 }),
    // 文本文档
    HTML("html", new byte[]{0x68, 0x74, 0x6D, 0x6C, 0x3E}),
    XML("xml", new byte[]{0x3C, 0x3F, 0x78, 0x6D, 0x6C}),
    // office 文档
    PDF("pdf", new byte[]{0x25, 0x50, 0x44, 0x46, 0x2D, 0x31, 0x2E}),
    // 视屏文件
    WAV("wav", new byte[]{0x57, 0x41, 0x56, 0x45}),
    AVI("avi", new byte[]{0x41, 0x56, 0x49, 0x20}),
    MOV("mov", new byte[]{0x6D, 0x6F, 0x6F, 0x76}),
    // 压缩文件
    ZIP("zip", new byte[]{0x50, 0x4b, 0x03, 0x04}),
    RAR("rar", new byte[]{0x52, 0x61, 0x72, 0x21});

    /**
     * 文件格式 - 文件类型 的 枚举类组成
     */
    private String fileFormat;
    private byte[] magicBytes;

    MagicBytes(String fileFormat, byte[] magicBytes) {
        this.fileFormat = fileFormat;
        this.magicBytes = magicBytes;
    }

    public String getFileFormat() {
        return fileFormat;
    }
    public byte[] getMagicBytes() {
        return magicBytes;
    }

    /**
     * 将传入的二进制数据域当前的魔数字节比对，查看当前传入的 fileBytes 是否为魔数字节
     */
    public boolean match(byte[] fileBytes) {
        if (null == fileBytes || fileBytes.length < this.magicBytes.length) {
            return false;
        }

        for (int i = 0; i < magicBytes.length; i++) {
            if (magicBytes[i] != fileBytes[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * 根据魔数字节获取对应的文件类型字符串
     */
    public static String format(byte[] matchBytes) {
        if (null == matchBytes) {
            return null;
        }

        for (MagicBytes magicBytes : MagicBytes.values()) {
            if (magicBytes.match(matchBytes)) {
                return magicBytes.fileFormat;
            }
        }

        return null;
    }


    /**
     * 从二进制数据中, 提取对应的魔数前缀, 当不满足要求时, 返回空
     */
    public static byte[] extractMBytes(byte[] bytes) {
        if (null == bytes) {
            return null;
        }

        for (MagicBytes magicByte : MagicBytes.values()) {
            if (magicByte.match(bytes)) {
                return magicByte.getMagicBytes();
            }
        }

        return null;
    }
}
