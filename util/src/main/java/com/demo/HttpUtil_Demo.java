package com.demo;

import com.zhq.util.HttpUtil.HttpUtil;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileInputStream;

public class HttpUtil_Demo {
    public static void main(String[] args) {
        HttpUtil_Demo instance = new HttpUtil_Demo();

//        instance.demo1();
        instance.demo2();
    }

    public void demo1() {
        try {
            File imgFile = new File("src/main/resources/file/Picture3.png");
            System.out.println(HttpUtil.imgDataUrl(new FileInputStream(imgFile), true));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void demo2() {
        try {
            String url = "http://localhost:8090/epoint-web-zwfwznsb/rest/selfservicetestrequest/gettestinfo?&hell&owhello=a&paramsq=1&welove=2&valentimedaty=true&nullPname=&";
            System.out.println(HttpUtil.getUrlParam(url, "hell"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
