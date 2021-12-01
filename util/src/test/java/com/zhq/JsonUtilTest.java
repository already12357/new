package com.zhq;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhq.util.JsonUtil;
import org.junit.Test;

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
    }
}
