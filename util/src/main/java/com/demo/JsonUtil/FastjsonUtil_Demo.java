package com.demo.JsonUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhq.util.JsonUtil.FastjsonUtil;

import java.util.*;

/**
 * FastjsonUtil 类的 Demo
 * @author Eddie Zhang
 */
public class FastjsonUtil_Demo {
    public static void main(String[] args) {
        FastjsonUtil_Demo instance = new FastjsonUtil_Demo();

        instance.demo1();
        instance.demo2();
        instance.demo3();
        instance.demo4();
        instance.demo5();
        instance.demo6();
        instance.demo7();
        instance.demo8();
        instance.demo9();
        instance.demo10();
        instance.demo11();
    }

    /**
     * demo : jStrToList
     */
    public void demo1() {
        String listJStr = "[1, 2, \"3\", \"4\", 5]";

        // 使用 jStrToList 来实现 json 字符串 到 List 之间的转换
        List jList = FastjsonUtil.jStrToList(listJStr);
        System.out.println(jList);
    }

    /**
     * demo : jArrayToList
     */
    public void demo2() {
        JSONArray jArray = new JSONArray();
        for (int i = 0; i < 20; i++) {
            JSONObject jObject = new JSONObject();
            jObject.put("index" + i, i + 2);
            jArray.add(jObject);
        }

        // 使用 jArrayToList 来实现 JSONArray 到 List 之间的转换
        List jList = FastjsonUtil.jArrayToList(jArray);
        System.out.println(jList);
    }

    /**
     * demo : jArrayToSet
     */
    public void demo3() {
        JSONArray jArray = new JSONArray();
        for (int i = 0; i < 20; i++) {
            JSONObject jObject = new JSONObject();
            jObject.put("index" + i, i + 2);
            jArray.add(jObject);
        }

        // 使用 jArrayToSet 来实现 JSONArray 到 Set 之间的转换
        Set jSet = FastjsonUtil.jArrayToSet(jArray);
        System.out.println(jSet);
    }

    /**
     * demo : jArrayToArray
     */
    public void demo4() {
        JSONArray jArray = new JSONArray();
        for (int i = 0; i < 20; i++) {
            JSONObject jObject = new JSONObject();
            jObject.put("index" + i, i + 2);
            jArray.add(jObject);
        }

        // 使用 jArrayToArray 来实现 JSONArray 到 Object[] 之间的转换
        Object[] array = FastjsonUtil.jArrayToArray(jArray);
        System.out.println(array);
    }

    /**
     * demo : jStrToArray
     */
    public void demo5() {
        String arrJStr = "[1, 2, \"3\", \"4\", 5, \"\", 2]";

        // 使用 jStrToArray 来实现 json 字符串 到 Object[] 之间的转换
        Object[] array = FastjsonUtil.jStrToArray(arrJStr);
        System.out.println(array);
    }

    /**
     * demo : arrayToJArray
     */
    public void demo6() {
        Object[] array = new Object[5];

        array[0] = 4;
        array[1] = "10";
        array[2] = new Date();
        array[3] = null;
        array[4] = "11";

        // 使用 arrayToJArray 来实现 Object[] 到 JSONArray 之间的转换
        JSONArray jArray = FastjsonUtil.arrayToJArray(array);
        System.out.println(jArray.toJSONString());
    }

    /**
     * demo : listToJArray
     */
    public void demo7() {
        List list = new ArrayList();
        list.add("1");
        list.add(1);
        list.add(new Date());
        list.add(null);

        // 使用 listToJArray 来实现 List 到 JSONArray 之间的转换
        JSONArray jArray = FastjsonUtil.listToJArray(list);
        System.out.println(jArray.toJSONString());
    }

    /**
     * demo : setToJArray
     */
    public void demo8() {
        Set set = new HashSet();
        set.add("1");
        set.add(1);
        set.add(new Date());
        set.add(null);

        // 使用 setToJArray 来实现 Set 到 JSONArray 之间的转换
        JSONArray jArray = FastjsonUtil.setToJArray(set);
        System.out.println(jArray.toJSONString());
    }

    /**
     * demo : mapToJObject
     */
    public void demo9() {
        List list = new ArrayList();
        list.add("1");
        list.add(1);
        list.add(new Date());
        list.add(null);

        Map<String, Object> map = new HashMap<>();
        map.put("mapList", list);
        map.put("hello", 2);
        map.put("mydate", new Date());


        // 使用 setToJArray 来实现 Map 到 JSONArray 之间的转换
        JSONObject jObject = FastjsonUtil.mapToJObject(map);
        System.out.println(jObject.toJSONString());
    }

    /**
     * demo : jsonRestReturn
     */
    public void demo10() {
        JSONObject jObject = new JSONObject();
        jObject.put("key1", "Str1");
        jObject.put("key2", 2);

        // 使用 jsonRestReturn 来返回带有格式的 json 字符串信息
        System.out.println(FastjsonUtil.jsonRestReturn("200", "附加返回信息", jObject.toJSONString()));
    }

    /**
     * demo : setJKeyValueInListJ
     */
    public void demo11() {
        List<JSONObject> listJ = new ArrayList<JSONObject>();
        for (int i = 0; i < 20; i++) {
            JSONObject jObject = new JSONObject();
            String key = "jObject".concat(String.valueOf(i));
            Integer value = i;
            jObject.put(key,value);
            listJ.add(jObject);
        }
        System.out.println("Before setJKeyValueInListJ");
        System.out.println(listJ.get(10).toJSONString());

        // 使用 setJKeyValueInListJ 来在 List 中找到对应 key-value 键值的 JSONObject 对象，设置对应 JSONObject 的 key-value 键值对
        FastjsonUtil.setJKeyValueInListJ(listJ, "jObject10", 10, "jObject10", 20);

        System.out.println("After setJKeyValueInListJ");
        System.out.println(listJ.get(10).toJSONString());
    }
}

