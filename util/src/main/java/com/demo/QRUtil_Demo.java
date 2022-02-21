package com.demo;

import com.zhq.util.IOUtil.IOUtil;
import com.zhq.util.QRUtil.QRUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 二维码帮助类的说明 demo
 * @author Eddie Zhang
 */
public class QRUtil_Demo {
    public static void main(String[] args) {
        QRUtil_Demo instance = new QRUtil_Demo();

        instance.demo1();
    }


    public void demo1() {
        File qrCodeFile = new File("./src/main/resources/file/qrcode.png");
        // 使用 qrCode 生成对应的二进制
        // qrCode 中主要包含 文字 或 url 连接
        byte[] qrBytes = QRUtil.qrCode("http://www.baidu.com", 100, 100, "png");
        IOUtil.writeBytesToFile(qrCodeFile, qrBytes);
    }
}
