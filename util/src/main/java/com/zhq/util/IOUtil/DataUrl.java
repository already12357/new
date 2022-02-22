package com.zhq.util.IOUtil;

import java.util.Base64;

/**
 * 用于拼接生成 dataUrl 的一些常量
 * data:①[<mime type>]②[;charset=<charset>]③[;<encoding>]④,<encoded data>⑤
 * @author Eddie Zhang
 */
public enum DataUrl {
    URL_NULL("", "", ""),
    URL_GIF("image", IOConstant.GIF, "base64"),
    URL_PNG("image", IOConstant.PNG, "base64"),
    URL_JPG("image", IOConstant.JPG, "base64");

    /**
     * 构造函数
     * @param mimeType MIME 文件类型
     * @param subType MIME 子文件类型
     * @param encode 数据编码方式
     *
     */
    private DataUrl(String mimeType, String subType, String encode) {
        this.mimeType = mimeType;
        this.subType = subType;
        this.encode = encode;
    }
    // mime 文件格式
    private String mimeType;
    // mime 子文件格式
    private String subType;
    // 字符集编码方式 ( 默认 ascii )
    // 后续添加
    private String charset;
    // 数据编码方式 ( base64 或 ascii )
    private String encode;


    /**
     * 根据文件格式获取对应的 DataUrl 对象
     * @param format 文件格式
     * @return
     */
    public static DataUrl getDataUrlByFormat(String format) {
        for (DataUrl dUrl : DataUrl.values()) {
            if (dUrl.subType.equals(format)) {
                return dUrl;
            }
        }

        return DataUrl.URL_NULL;
    }

    /**
     * 获取该 URL 的对象
     * @return
     */
    public String DATA_URL(byte[] data) {
        StringBuilder dataUrlStr = new StringBuilder();
        String base64Str = Base64.getEncoder().encodeToString(data);

        // 拼接返回对应的 base64 字符串
        dataUrlStr.append("data:")
                .append(mimeType)
                .append("/")
                .append(subType)
                .append(";")
                .append(encode)
                .append(",")
                .append(base64Str);

        return dataUrlStr.toString();
    }

    /**
     * 根据文件格式和文件数据获取对应的 DataUrl
     * @param format 文件格式
     * @param data 传入的打包的数据
     */
    public static String DATA_URL(String format, byte[] data) {
        DataUrl dUrl = getDataUrlByFormat(format);

        // 当匹配到对应的对象时
        if (dUrl != DataUrl.URL_NULL) {
            StringBuilder dataUrlStr = new StringBuilder();
            String base64Str = Base64.getEncoder().encodeToString(data);

            // 拼接返回对应的 base64 字符串
            dataUrlStr.append("data:").append(dUrl.mimeType)
                    .append("/")
                    .append(dUrl.subType)
                    .append(";")
                    .append(dUrl.encode)
                    .append(",")
                    .append(base64Str);

            return dataUrlStr.toString();
        }
        else {
            return "";
        }
    }
}
