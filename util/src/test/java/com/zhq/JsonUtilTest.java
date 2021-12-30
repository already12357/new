package com.zhq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.zhq.util.JsonUtil.FastjsonUtil;
import com.zhq.util.JsonUtil.JsonUtil;
import org.junit.Test;

import java.util.*;

public class JsonUtilTest {
    @Test
    public void testJsonReturn() {
        System.out.println(FastjsonUtil.jsonRestReturn("200", "成功了", "hello"));
    }


    @Test
    public void testJsonUtilTest() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.add("1");
        jsonArray.add("2");
        jsonArray.add("3");
        jsonArray.add("4");
        jsonArray.add("5");

        List<Integer> content = FastjsonUtil.jArrayToList(jsonArray);
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

        Set<Object> setObject = new HashSet<Object>();
        setObject.add("1");
        setObject.add(testObject);
        setObject.add(false);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sValue", "hello");
        map.put("iValue", 3);
        map.put("oaValue", testObject);
        map.put("null", null);
        System.out.println(JsonUtil.mapToJString(map));

        Map<String, Object> map1 = new HashMap<>();
        map1.put("ss", 1);
        map1.put("ss1", 3);
        map1.put("s34", null);
        map1.put("fdaf", content);
        map1.put("null", "3");
        map1.put("fa", 'c');


        Object[] testObject_empty = new Object[0];
        System.out.println(JsonUtil.arrayToJString(testObject_empty));

        List<Object> test = new ArrayList<>();
        System.out.println(JsonUtil.collectionToJString(test));

        System.out.println(JsonUtil.collectionToJString(setObject));

        System.out.println(JsonUtil.arrayToJString(testObject));


        List<Object> mapList = new ArrayList<>();
        mapList.add(map);
        mapList.add(map1);
        System.out.println(JsonUtil.collectionToJString(mapList));

        String testJson = JsonUtil.collectionToJString(mapList);
        System.out.println(testJson);
        JSONArray jsonObject = JSONArray.parseArray(testJson);

        List<Integer> integersList = new ArrayList<Integer>();
        integersList.add(2);
        integersList.add(5);
        integersList.add(6);
        integersList.add(3);
        integersList.add(8);
        System.out.println(JsonUtil.collectionToJString(integersList));

        JSONArray jArray = new JSONArray();
        jArray.add(1);
        jArray.add("3");
        JSONObject addElement = new JSONObject();
        addElement.put("hello", 1);
        jArray.add(addElement);
        List list_2 = FastjsonUtil.jArrayToList(jArray);
        System.out.println(list_2);


        Properties prop = new Properties();
        prop.setProperty("hello", "hello");
        prop.setProperty("hello1", "hello1");
        prop.setProperty("hello2", "hello2");
        prop.setProperty("hello3", "hello3");
        System.out.println(JsonUtil.propertiesToJString(prop));
    }
}
