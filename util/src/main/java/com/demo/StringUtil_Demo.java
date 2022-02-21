package com.demo;

import com.zhq.util.JsonUtil.JsonUtil;
import com.zhq.util.StringUtil;

import java.util.Map;

/**
 * StringUtil 类的 Demo
 * @author Eddie Zhang
 */
public class StringUtil_Demo {
    public static void main(String[] args) {
        StringUtil_Demo instance = new StringUtil_Demo();

        instance.demo1();
    }

    public void demo1() {
        String url = "http://localhost:8090/epoint-web-zwfwznsb/rest/selfservicetestrequest/gettestinfo?&hell&owhello=a&paramsq=1&welove=2&valentimedaty=true&nullPname=&";
        Map<String, Object> urlParamsMap = StringUtil.urlParamsToMap(url);
        System.out.println(JsonUtil.mapToJString(urlParamsMap));
    }
}
