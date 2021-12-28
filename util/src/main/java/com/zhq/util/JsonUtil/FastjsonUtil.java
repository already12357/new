package com.zhq.util.JsonUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 用于在引入了 fastjson 依赖下，解析对应的对象
 * @author Eddie Zhang
 */
public class FastjsonUtil {
    /**
     * 将传入的 JSONArray 或 json 字符串转化为对应的 List 对象
     * @param jStr 传入的 JSON 数组字符串
     * @return
     */
    public static List jStrToList(String jStr) {
        if (null == jStr) {
            return null;
        }

        JSONArray jArray = JSON.parseArray(jStr);
        return jArrayToList(jArray);
    }

    public static List jArrayToList(JSONArray jArray) {
        if (null == jArray) {
            return null;
        }

        List retArray = new ArrayList();

        for (int i = 0; i < jArray.size(); i++) {
            retArray.add(jArray.get(i));
        }

        return retArray;
    }

    public static Set jArrayToSet(JSONArray jArray) {
        if (null == jArray) {
            return null;
        }

        Set retSet = new HashSet();

        for (int i = 0; i < jArray.size(); i++) {
            retSet.add(jArray.get(i));
        }

        return retSet;
    }

    /**
     * 将传入的 JSONArray 或 json 字符串转为原生的 Object 数组
     * @param jArray
     * @return
     */
    public static Object[] jArrayToArray(JSONArray jArray) {
        if (null == jArray) {
            return null;
        }

        Object[] retObject = new Object[jArray.size()];
        for (int i = 0; i < jArray.size(); i++) {
            retObject[i] = jArray.get(i);
        }
        
        return retObject;
    }

    public static Object[] jStrToArray(String jStr) {
        if (null == jStr) {
            return null;
        }

        JSONArray retJArray = JSON.parseArray(jStr);
        return jArrayToArray(retJArray);
    }


    /**
     * 将传入的 Collection, List, Set 或 Object[] 对象转为 JSONArray 对象
     * @param array, collection, list, set
     * @return
     */
    public static JSONArray arrayToJArray(Object[] array) {
        if (null == array) {
            return null;
        }

        JSONArray retJArray = new JSONArray();
        for (Object element : array) {
            retJArray.add(element);
        }
        return retJArray;
    }

    public static JSONArray collectionToJArray(Collection collection) {
        if (null == collection) {
            return null;
        }

        JSONArray retJArray = new JSONArray();
        for (Object element : collection) {
            retJArray.add(element);
        }

        return retJArray;
    }

    public static JSONArray listToJArray(List list) {
        if (null == list) {
            return null;
        }

        return collectionToJArray(list);
    }

    public static JSONArray setToJArray(Set set) {
        if (null == set) {
            return null;
        }

        return collectionToJArray(set);
    }

    /**
     * 将 Map 转化为对应的 JSONObject 对象
     * @param map
     * @return
     */
    public static JSONObject mapToJObject(Map<String, Object> map) {
        if (null == map) {
            return null;
        }

        String mapJStr = JSON.toJSONString(map);
        JSONObject retJObject = JSON.parseObject(mapJStr);
        return retJObject;
    }

    /**
     * Rest 接口返回带有状态码的信息
     * @param statCode 请求状态码, 通常使用网页的请求状态码 ( 默认为 200 )
     * @param attachMessage 附加的额外信息
     * @param data 返回的数据信息
     * @return json格式的数据
     * 如下格式 :
     * {
     *     # message 包含 返回状态码 和 返回的附加信息
     *     "message" : {
     *         “status”:"200",
     *         "attachment":"请求成功"
     *     },
     *     # data 返回的 json 数据字符串
     *     “data” : "....."
     * }
     */
    public static String jsonRestReturn(String statCode, String attachMessage, Object data) {
        JSONObject retObject = new JSONObject();
        JSONObject messageObject = new JSONObject();
        String inputData = "200";

        if (null != statCode && !statCode.equals("")) {
            inputData = statCode;
        }

        try {
            messageObject.put("status", inputData);
            messageObject.put("attachment", attachMessage);
            retObject.put("message", messageObject);
            retObject.put("data", data);
        }
        catch (Exception e) {
            e.printStackTrace();
            messageObject.put("status", "500");
            messageObject.put("attachment", "请求返回异常");
            retObject.put("message", messageObject);
            retObject.put("data", data);
        }

        return retObject.toJSONString();
    }
}
