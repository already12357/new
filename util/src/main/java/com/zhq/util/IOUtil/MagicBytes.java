package com.zhq.util.IOUtil;

/**
 * 枚举类对象，存储 文件格式 和 对应的魔数字节
 * @author Eddie Zhang
 */
public enum MagicBytes {
    // 图片
    JPG(IOConstant.JPG, new byte[]{(byte) 0xff, (byte) 0xd8, (byte) 0xff}),
    BMP(IOConstant.BMP, new byte[]{0x42, 0x4d}),
    GIF(IOConstant.GIF, new byte[]{0x47, 0x49, 0x46, 0x38}),
    PNG(IOConstant.PNG, new byte[]{(byte) 0x89, 0x50, 0x4e, 0x47 }),
    // 文本文档
    HTML(IOConstant.HTML, new byte[]{0x68, 0x74, 0x6D, 0x6C, 0x3E}),
    XML(IOConstant.XML, new byte[]{0x3C, 0x3F, 0x78, 0x6D, 0x6C}),
    // office 文档
    PDF(IOConstant.PDF, new byte[]{0x25, 0x50, 0x44, 0x46, 0x2D, 0x31, 0x2E}),
    // 视屏文件
    WAV(IOConstant.WAV, new byte[]{0x57, 0x41, 0x56, 0x45}),
    AVI(IOConstant.AVI, new byte[]{0x41, 0x56, 0x49, 0x20}),
    MOV(IOConstant.MOV, new byte[]{0x6D, 0x6F, 0x6F, 0x76}),
    // 压缩文件
    ZIP(IOConstant.ZIP, new byte[]{0x50, 0x4b, 0x03, 0x04}),
    RAR(IOConstant.RAR, new byte[]{0x52, 0x61, 0x72, 0x21});

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
