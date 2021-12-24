package com.zhq.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;

public class JsonUtil {
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
    public static String jsonRestReturn(String statCode, String attachMessage, String data) {
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
     * 将 JSON 数组字符串转换为对应的类型 List 对象
     * @param jsonStr 传入的 JSON 数组字符串
     * @param convertType 转换类型 ( 注意使用包装类  )
     * @return
     */
    public static List jArrayToList(String jsonStr, Class convertType) {
        JSONArray jArray = JSON.parseArray(jsonStr);
        List retArray = new ArrayList();
        Method valueOfMethod = null;

        try {
            // 反射调用转换类型的 valueOf 对象, 如 Integer.valueOf(...)
            valueOfMethod = convertType.getMethod("valueOf", String.class);

            for (int i = 0; i < jArray.size(); i++) {
                String arrElement = jArray.getString(i);
                Object convertedValue = valueOfMethod.invoke(null, arrElement);
                // 调用后转换
                retArray.add(convertType.cast(convertedValue));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return retArray;
    }


    /**
     * 将数据库查询的 ResultSet 对象转换为对应的 JSONString 对象,
     * 使用 JSONObject 对象获取
     * @param queryResult
     * @return
     */
    public static String resultSetToJString(ResultSet queryResult) {
        try {
            JSONArray jResultSet = new JSONArray();
            ResultSetMetaData metaData = queryResult.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (queryResult.next()) {
                JSONObject jElement = new JSONObject();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    Object columnValue = queryResult.getObject(i);
                    jElement.put(columnName, columnValue);
                }

                jResultSet.add(jElement);
            }

            return jResultSet.toJSONString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

//      根据传入的泛型将 JSONArray 转换为对应的原生数组 ( 暂时无法实现 )
//      由于泛型在 Java 存在泛型擦除问题，即在加载时会将除类上标记的泛型全部擦除，所以无法获得泛型的类
//    public static <T> T[] jArrayToNativeArray(String jsonStr) {}


    /**
     * 将传入的 Map 对象转化为 JSON 格式的字符串
     * @param map
     * @return
     * 调用 : mapToJString(new HashMap<String, Object>("1", 2, "3", 4, "5", new List(2, 3, 4)))
     * 返回 :
     * {
     *     "1" : 2,
     *     "3" : 4,
     *     "5" : [2, 3, 4]
     * }
     */
    public static String mapToJString(Map<String, Object> map) {
        StringBuilder jMapStr = new StringBuilder();
        jMapStr.append("{").append(innerMapToJString(map)).append("}");
        return jMapStr.toString();
    }

    /**
     * 将传入的集合对象转化为对应的 JSON 字符串的值
     * @param collection
     * @return
     * 调用 : collectionToJString(new List(2, '3', 4, null))
     * 返回 : [2, "3", 4, ""]
     */
    public static String collectionToJString(Collection<Object> collection) {
        StringBuilder jCollectionStr = new StringBuilder();
        jCollectionStr.append("[").append(innerValueToJString(collection, false)).append("]");
        return jCollectionStr.toString();
    }

    /**
     * 将传入的键值对转换为对应的 JSON 格式字符串
     *
     * @param key
     * @param value
     * @return
     * 调用 :  keyValueToJString("2", 3)
     * 返回 :  {"2":3}
     *
     * 调用 :  keyValueToJString("4", new List(3, "4", 7, null))
     * 返回 :  {"4":[3, "4", 7, ""}
     */
    public static String keyValueToJString(String key, Object value) {
        StringBuilder jKeyValueStr = new StringBuilder();
        jKeyValueStr.append("{").append(innerKeyValueToJString(key, value)).append("}");
        return jKeyValueStr.toString();
    }

    /**
     * 将传入的数组数据转化为对应的 JSON 格式字符串
     * @param array 传入的用于解析的对象
     * @return
     * 调用 : arrayToJString(['2', 3, '4', new List<Object>(2, '3')])
     * 返回 : ["2", 3, "4", [2, "3"]]
     */
    public static String arrayToJString(Object[] array) {
        StringBuilder jArrayStr = new StringBuilder();
        jArrayStr.append("[").append(innerArrayToJString(array)).append("]");
        return jArrayStr.toString();
    }

    /**
     * 将传入的单个 Object 对象转化为对应的 JSON 字符串值
     * ( 暂不支持 POJO 类型 ) -
     *  |||||||||||||||||||||  后续支持 POJO 类型 ||||||||||||||||||| ( 注解支持 )
     *
     * @param value 传入的用于解析的对象
     * @return
     * 调用 : valueToJString('3')
     * 返回 : "3"
     *
     * 调用 : valueToJString(new List(3, '4', 7, new Set(5, 5, '6'), null))
     * 返回 : 3, "4", 7, [5, 5, "6"], ""
     */
    public static String valueToJString(Object value) {
        return innerValueToJString(value, false);
    }


    // =====================================
    // Object - JSON字符串 核心解析函数，用于底层解析对应的字符串
    // 分别用于 生成  Object[], Object 和 K-V 形式的 JSON 字符串内部的值
    // 外层的函数通过调用方法拼接
    // 用于处理 Map 类型的对象
    private static String innerMapToJString(Map<String, Object> map) {
        if (null != map && !map.isEmpty()) {
            StringBuilder jMapStr = new StringBuilder("");

            jMapStr.append("{");
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                jMapStr.append(innerKeyValueToJString(entry.getKey(), entry.getValue()));
                jMapStr.append(",");
            }
            jMapStr.deleteCharAt(jMapStr.length() - 1);

            jMapStr.append("}");

            return jMapStr.toString();
        }

        return "";
    }

    // 用于处理 key-value 类型的对象
    private static String innerKeyValueToJString(String key, Object value) {
        if (null != key && !key.isEmpty()) {
            StringBuilder kvStr = new StringBuilder("");

            kvStr.append("\"").append(key).append("\"");
            kvStr.append(":");

            if (null == value) {
                kvStr.append("\"\"");
            }
            // 集合 或 数组类型时
            else if (Collection.class.isAssignableFrom(value.getClass())
                    || value.getClass().isArray()) {
                kvStr.append("[").append(innerValueToJString(value, false)).append("]");
            }
            // 对象, 键值对类型添加 {}
            // ...
            // 非集合类型时
            else {
                kvStr.append(innerValueToJString(value, false));
            }

            return kvStr.toString();
        }

        return "";
    }

    // 用于处理 Object[] 类型的对象
    private static String innerArrayToJString(Object[] array) {
        return innerValueToJString(array, false);
    }

    // 核心解析
    // 用于处理 Object 类型的字符串解析
    // 此处 inner 用于在循环递归中使用, 默认传 false
    // !!!!!!!!!!!! 后续支持 Map, Entry 等类型 !!!!!!!!!!!!
    private static String innerValueToJString(Object value, boolean inner) {
        // 传入 null 时返回 " "" "
        if (null == value) {
            return  "\"\"";
        }

        // 数字解析
        if (Integer.class.isAssignableFrom(value.getClass())
                || Long.class.isAssignableFrom(value.getClass())
                || Float.class.isAssignableFrom(value.getClass())
                || Double.class.isAssignableFrom(value.getClass())
                || Boolean.class.isAssignableFrom(value.getClass())) {
            return String.valueOf(value);
        }
        // 集合类型解析
        else if (Collection.class.isAssignableFrom(value.getClass())) {
            if (!((Collection) value).isEmpty()) {
                StringBuilder collectionStr = new StringBuilder("");
                // 处理嵌套情况，在内部时需要添加外侧边框，用于递归时使用
                if (inner) {
                    collectionStr.append("[");
                }

                for (Object colValue : ((Collection) value)) {
                    collectionStr.append(innerValueToJString(colValue, true));
                    collectionStr.append(",");
                }
                collectionStr.deleteCharAt(collectionStr.length() - 1);

                if (inner) {
                    collectionStr.append("]");
                }

                return collectionStr.toString();
            }
        }
        // 数组类型解析
        else if (value.getClass().isArray()) {
            int arrLength = Array.getLength(value);
            if (arrLength != 0) {
                StringBuilder arrayStr = new StringBuilder("");
                if (inner) {
                    arrayStr.append("[");
                }

                for (int i = 0; i < arrLength; i++) {
                    Object arrObject = Array.get(value, i);
                    arrayStr.append(innerValueToJString(arrObject, true));
                    arrayStr.append(",");
                }

                arrayStr.deleteCharAt(arrayStr.length() - 1);

                if (inner) {
                    arrayStr.append("]");
                }

                return arrayStr.toString();
            }
        }
        // POJO 对象支持
//        else if () {
//
//        }
        // 非数字类型解析
        else {
            return new String("\"").concat(String.valueOf(value)).concat("\"");
        }


        // 解析失败时返回 ""
        return "";
    }
}
