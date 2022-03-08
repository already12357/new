package com.demo.util;

import com.config.CommonConfig;
import com.zhq.util.IOUtil.IOConstant;
import com.zhq.util.IOUtil.IOUtil;
import com.zhq.util.QRUtil.QRUtil;

import java.io.File;

/**
 * 二维码帮助类的说明 demo
 * @author Eddie Zhang
 */
public class QRUtil_Demo {
    public static void main(String[] args) {
        QRUtil_Demo instance = new QRUtil_Demo();

        instance.demo1();
    }


    /**
     * demo : qrCode
     */
    public void demo1() {
        File qrCodeFile = new File(CommonConfig.DEMO_FILE_LOCATION + "qr/out/qr_demo_1_out.png");
        byte[] qrBytes = QRUtil.qrCode("http://www.baidu.com", 100, 100, IOConstant.PNG);
        IOUtil.writeBytesToFile(qrCodeFile, qrBytes);
    }
}
