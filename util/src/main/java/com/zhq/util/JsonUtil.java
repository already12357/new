package com.zhq.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public static String jResultSetToJson(ResultSet queryResult) {
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
     * 将 Map 对象转化为 JSON 格式的字符串 ( 注意不同格式的字符串显示 )
     * {.....}
     * @param jMap
     * @return
     */
    public static String mapToJString(Map<String, Object> jMap) {
        StringBuilder jMapStr = new StringBuilder();



        return jMapStr.toString();
    }

    /**
     * 将存储的集合类型转换为 JSON 字符串
     * [",,", "fdf", 12, 32, 22]
     * @param array
     * @return
     */
    public static String arrayToJString(Object[] array) {
        StringBuilder jArrayStr = new StringBuilder();

        if (null != array && array.length != 0) {
            jArrayStr.append("[");
            for (Object jsonObject : array) {

            }
            jArrayStr.append("]");
        }

        return jArrayStr.toString();
    }

    public static String listToJString(List<Object> list) {
        StringBuilder jListStr = new StringBuilder();

        if (null != list && !list.isEmpty()) {
            jListStr.append("[");
            for (Object jsonObject : list) {

            }
            jListStr.append("]");
        }

        return jListStr.toString();
    }

    public static String setToJString(Set<Object> set) {
        StringBuilder jSetStr = new StringBuilder();

        if (null != set && !set.isEmpty()) {
            jSetStr.append("[");
            for (Object jsonObject : set) {

            }
            jSetStr.append("]");
        }

        return jSetStr.toString();
    }



    /**
     * 将传入的 Pojo  对象转化为 JSON 格式的字符串 ( 反射方法调用  )
     * @param jObject
     * @return
     */
    public static String jObjectToString(Object jObject) {
        StringBuilder jObjectStr = new StringBuilder();



        return jObjectStr.toString();
    }

    public static String keyValueToJString(String key, Object value) {

    }

    public static String valueToJString(Object value) {

    }
}
