package com.zhq;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhq.util.JsonUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JsonUtilTest {
    @Test
    public void testJsonUtilTest() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.add("1");
        jsonArray.add("2");
        jsonArray.add("3");
        jsonArray.add("4");
        jsonArray.add("5");

        List<Integer> content = JsonUtil.jArrayToList(jsonArray.toJSONString(), Integer.class);
        System.out.println(content);

        List<Object> content_Json = new ArrayList<Object>();
        content_Json.add("1");
        content_Json.add(new Date());
        content_Json.add(2);
        content_Json.add(content);
        content_Json.add(null);
//        System.out.println(JsonUtil.valueToJString(content_Json));

        Object[] testObject = new Object[4];
        testObject[0] = content_Json;
        testObject[1] = content;
        testObject[2] = "2";
        testObject[3] = 4;
        System.out.println(JsonUtil.arrayToJString(testObject));
    }
}
