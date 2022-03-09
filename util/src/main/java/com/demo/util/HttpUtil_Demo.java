package com.demo.util;

import com.zhq.util.HttpUtil.HttpUtil;

public class HttpUtil_Demo {
    public static void main(String[] args) {
        HttpUtil_Demo instance = new HttpUtil_Demo();

        instance.demo1();
    }

    /**
     * demo : getUrlParam
     */
    public void demo1() {
        try {
            String url = "http://localhost:8090/epoint-web-zwfwznsb/rest/selfservicetestrequest/gettestinfo?&hell&owhello=a&paramsq=1&welove=2&valentimedaty=true&nullPname=&";
            System.out.println(HttpUtil.getUrlParam(url, "hell"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
