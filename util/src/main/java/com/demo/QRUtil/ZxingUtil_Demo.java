package com.demo.QRUtil;

import com.zhq.util.QRUtil.ZxingUtil;

public class ZxingUtil_Demo {
    public static void main(String[] args) {
        ZxingUtil_Demo instance = new ZxingUtil_Demo();

        instance.demo1();
    }

    public void demo1() {
        String qrCodeContent = "This is my content";
        ZxingUtil.qrCode(qrCodeContent, 200, 200, );
    }
}
