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

    /**
     * 在 List<JSONObject> 中，选中存在 key-value 为特定值的 JSONObject 对象，
     * 然后设置其中特定的 key-value 对，当 key-value 不存在时，新增对应的 key-value
     * 并设置其值
     * @param list 查找的 List<JSONObject> 对象
     * @param selectKey 作为选中 JSONObject 对象的键名称
     * @param selectValue 作为选中 JSONObject 对象的值
     * @param setKey 需要设置的值的名称
     * @param newValue 设置 List 对象中 key 为 keyName 的 JSONObject
     */
    public static boolean setJKeyValueInListJ(List<JSONObject> list, Object selectKey, Object selectValue, String setKey, Object newValue) {
        try {
            for (JSONObject jObject : list) {
                if (jObject.containsKey(selectKey) &&
                        jObject.get(selectKey).equals(selectValue)) {
                    jObject.put(setKey, newValue);
                    return true;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /* .................添加后续支持................. */
    /**
     * 在 JSONArray 对象中，寻找有对应 Key-Value 对的 JSONObject 对象, 并以 JSONArray 形式将其返回
     * 该方法类只支持单层的 JSONArray 对象查询 ( 并不递归查询 )
     * 如 : [
     *        {
     *            "name"    : "Jam",
     *            "age"     : 25,
     *            "address" : "221B BakeStreet",
     *            "sex"     : "male"
     *        },
     *        {
     *            "name"    : "Paul",
     *            "age"     : 45,
     *            "address" : "12S North Ambulance Tree Town",
     *            "sex"     : "female",
     *        },
     *        {
     *            "name"    : "Anne",
     *            "age"     :  45,
     *            "address" : "Sunny Street 5th Lemon Town",
     *            "sex"     : "female",
     *        },
     *        {
     *            "name"    : "Thomas",
     *            "age"     :  30,
     *            "address" : "Sunny Street 5th Lemon Town",
     *            "sex"     : "male",
     *        }
     *      ]
     * @param jArray 传入的 JSONArray 对象
     * @param key 键名称
     * @param value 键对应的值 ( 支持 String, Integer, boolean 三种 )
     *
     */
    public static JSONArray findJObjectsInJArray(JSONArray jArray, String key, Object value) {
        if (null == jArray || jArray.size() == 0) {
            return new JSONArray();
        }

        JSONArray retArray = new JSONArray();

        for (int i = 0; i < jArray.size(); i++) {
            Object jObject = jArray.get(i);

            // 当为 JSONArray 的子类时
            if (jObject.getClass().isAssignableFrom(JSONArray.class)) {
                // 递归查询, 将查询到的结果添加到 JArray 中
                retArray.addAll(findJObjectsInJArray(jArray, key, value));
            }

            // 当为 JSONObject 的子类时
            if (jObject.getClass().isAssignableFrom(JSONObject.class)) {
                for (Map.Entry<String, Object> jEntry : ((JSONObject)jObject).entrySet()) {
                    if (jEntry.getKey().equals(key) && jEntry.getValue().equals(value)) {
                        retArray.add(jObject);
                    }
                }
            }
        }

        return retArray;
    }
}
