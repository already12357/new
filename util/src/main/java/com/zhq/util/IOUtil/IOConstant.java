package com.zhq.util.IOUtil;

/**
 * 文件魔数头的信息定义，使用枚举类型定义
 * @author Eddie Zhang
 */
public class IOConstant {
    // 各种文件格式类型
    public static final String BMP = "bmp";
    public static final String DOC = "doc";
    public static final String DOCX = "docx";
    public static final String GIF = "gif";
    public static final String JPG = "jpg";
    public static final String PDF = "pdf";
    public static final String PNG = "png";
    public static final String PPT = "ppt";
    public static final String PPTX = "pptx";
    public static final String XLS = "xls";
    public static final String XLSX = "xlsx";
    public static final String ZIP = "zip";
    public static final String RAR = "rar";


    // 各种文件大类
    // 图片
    public static final String TYPE_IMAGE = "image";
    // 文本
    public static final String TYPE_TEXT = "text";
    // 音频，视频等


    /**
     * 将文件格式 和 魔数信息，按所属文件大类划分，便于查看
     */

}

enum MagicBytes {
    JPG("bmp", new byte[]{0x42, 0x4d}),
    GIF("gif", new byte[]{0x47, 0x49, 0x46, 0x38}),
    PNG("png", new byte[]{(byte) 0x89, 0x50, 0x4e, 0x47 }),
    ZIP("zip", new byte[]{0x50, 0x4b, 0x03, 0x04});


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
}
