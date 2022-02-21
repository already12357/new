package com.zhq.util.QRUtil;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Hashtable;

/**
 * 二维码辅助类, 使用 Zxing 包
 * @author Eddie Zhang
 */
public class QRUtil {
    /**
     * 根据传入的 text 生成对应的二维码, 输出其二进制数据
     * @param text 二维码内容生成依据
     * @param width 二维码宽度
     * @param height 二维码高度
     * @param format 生成图片数据的格式 (png, jpg, gif)
     */
    public static byte[] qrCode(String text, int width, int height, String format) {
        try {
            // 传入对应的设置
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

            // 生成对应的二维码
            BitMatrix bitMatrix = (new MultiFormatWriter()).encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            bitMatrix = clearQrMargin(bitMatrix);
            BufferedImage image = toBufferedImage(bitMatrix);

            // 将数据先写入到 ByteArrayOutputStream 中, 然后取出为 byte[]
            ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
            ImageIO.write(image, format, imageStream);
            byte[] imageBytes = imageStream.toByteArray();
            return imageBytes;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 去除二维码的外部的白色边框
     * 通过将二维码中的数据写入到一个新的二维码中来实现去掉边框的效果
     */
    public static BitMatrix clearQrMargin(BitMatrix matrix) {
        int[] encRect = matrix.getEnclosingRectangle();
        int resetWidth = encRect[2] + 1;
        int resetHeight = encRect[3] + 1;
        BitMatrix newBitMatrix = new BitMatrix(resetWidth, resetHeight);

        for (int i = 0; i < resetWidth; i++) {
            for (int j = 0; j < resetHeight; j++) {
                if (matrix.get(i + encRect[0], j + encRect[1])) {
                    newBitMatrix.set(i, j);
                }
            }
        }

        return newBitMatrix;
    }


    /**
     * 将 BitMatrix 对象转化为 BufferedImage 对象, 便于输出
     * @param  bitMatrix 传入的二维码对象
     * @return
     */
    public static BufferedImage toBufferedImage(BitMatrix bitMatrix) {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                image.setRGB(i, j, bitMatrix.get(i, j) ? -16777216 : -1);
            }
        }

        return image;
    }
}
