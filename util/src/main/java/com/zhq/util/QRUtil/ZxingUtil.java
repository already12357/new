package com.zhq.util.QRUtil;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * 二维码辅助类, 使用 Zxing 包
 * @author Eddie Zhang
 */
public class ZxingUtil {
    // 下载路径
    public static final String DEFAULT_LOADPATH = "C:/Users/Administrator/Desktop/gitRepository/util/src/main/resources/file";

    /**
     * 根据传入的 codeContent 生成对应的二维码
     * @param codeContent 二维码内容生成依据
     * @param width 二维码宽度
     * @param height 二维码高度
     */
    public static void qrCode(String codeContent, int width, int height, String filePath) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(codeContent, BarcodeFormat.QR_CODE, width, height);
            Path path = FileSystems.getDefault().getPath(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        } catch (IOException | WriterException e) {
            e.printStackTrace();
        }
    }
}
