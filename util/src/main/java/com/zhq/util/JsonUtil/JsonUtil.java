package com.zhq.util.JsonUtil;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;

public class JsonUtil {
    /**
     * 将数据库查询的 ResultSet 对象转换为对应的 JSON 格式字符串
     * @param queryResult 查询到的 JSON 结果集
     * @return
     */
    public static String resultSetToJString(ResultSet queryResult) {
        try {
            StringBuilder resultSetStr = new StringBuilder();
            ResultSetMetaData metaData = queryResult.getMetaData();
            int columnCount = metaData.getColumnCount();

            resultSetStr.append("[");
            while (queryResult.next()) {
                resultSetStr.append("{");
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    Object columnValue = queryResult.getObject(i);
                    resultSetStr.append(innerKeyValueToJString(columnName, columnValue, false));
                    resultSetStr.append(",");
                }
                resultSetStr.deleteCharAt(resultSetStr.length() - 1);
                resultSetStr.append("}");
                resultSetStr.append(",");
            }
            resultSetStr.deleteCharAt(resultSetStr.length() - 1);
            resultSetStr.append("]");

            return resultSetStr.toString();
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
        return innerMapToJString(map, true);
    }

    /**
     * 将传入的集合对象转化为对应的 JSON 字符串的值
     * @param collection
     * @return
     * 调用 : collectionToJString(new List(2, '3', 4, null))
     * 返回 : [2, "3", 4, ""]
     */
    public static String collectionToJString(Collection collection) {
        return innerCollectionToJString(collection, true);
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
        return innerKeyValueToJString(key, value, true);
    }

    /**
     * 将传入的数组数据转化为对应的 JSON 格式字符串
     * @param array 传入的用于解析的对象
     * @return
     * 调用 : arrayToJString(['2', 3, '4', new List<Object>(2, '3')])
     * 返回 : ["2", 3, "4", [2, "3"]]
     */
    public static String arrayToJString(Object[] array) {
        return innerArrayToJString(array, true);
    }

    /**
     * 将传入的单个 Object 对象转化为对应的 JSON 字符串值
     * ( 暂不支持 POJO 类型 ) -
     *  |||||||||||||||||||||  后续支持 POJO 类型 ||||||||||||||||||| ( 注解支持 )
     *
     * @param obj 传入的用于解析的对象
     * @return
     * 调用 : valueToJString('3')
     * 返回 : "3"
     *
     * 调用 : valueToJString(new List(3, '4', 7, new Set(5, 5, '6'), null))
     * 返回 : 3, "4", 7, [5, 5, "6"], ""
     */
    public static String objectToJString(Object obj) {
        return innerObjectToJString(obj, true);
    }



    /**
     * 1. 解析对象的实际处理逻辑，包含了对 Map, Object[], Object, Value(基本类型) 者几种类型的基本解析
     *
     * 2. 通过在内部 直接 或 间接 调用 innerObjectToJString 来递归的解析，生成 json 字符串
     *
     * 3. 最后 innerObjectToJString 通过 innerValueToJString 作为递归的出口
     *
     * 4. 其中的 brackets 变量用于控制是否在调用时，显示该值对应的外部括号 ( 即 {} 或 [] )
     *
     * @param brackets
     * @return
     */
    private static String innerMapToJString(Map<String, Object> map, boolean brackets) {
        StringBuilder jMapStr = new StringBuilder("");
        if (brackets) {
            jMapStr.append("{");
        }

        if (null != map && !map.isEmpty()) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                jMapStr.append(innerKeyValueToJString(entry.getKey(), entry.getValue(), false));
                jMapStr.append(",");
            }
            jMapStr.deleteCharAt(jMapStr.length() - 1);
        }

        if (brackets) {
            jMapStr.append("}");
        }

        return jMapStr.toString();
    }

    private static String innerKeyValueToJString(String key, Object value, boolean brackets) {
        StringBuilder kvStr = new StringBuilder("");
        if (brackets) {
            kvStr.append("{");
        }

        if (null != key && !key.isEmpty()) {
            kvStr.append("\"").append(key).append("\"");
            kvStr.append(":");

            if (null == value) {
                kvStr.append("\"\"");
            }
            else {
                kvStr.append(innerObjectToJString(value, true));
            }
        }

        if (brackets) {
            kvStr.append("}");
        }
        return kvStr.toString();
    }

    private static String innerArrayObjectToJString(Object arrObject, boolean brackets) {
        StringBuilder arrayStr = new StringBuilder("");
        if (brackets) {
            arrayStr.append("[");
        }

        if (null != arrObject) {
            int arrLength = Array.getLength(arrObject);
            if (arrLength != 0) {
                for (int i = 0; i < arrLength; i++) {
                    Object arrElement = Array.get(arrObject, i);
                    arrayStr.append(innerObjectToJString(arrElement, true));
                    arrayStr.append(",");
                }

                arrayStr.deleteCharAt(arrayStr.length() - 1);
            }
        }

        if (brackets) {
            arrayStr.append("]");
        }
        return arrayStr.toString();
    }

    private static String innerArrayToJString(Object[] array, boolean brackets) {
        StringBuilder arrStr = new StringBuilder("");
        if (brackets) {
            arrStr.append("[");
        }

        if (null != array) {
            int arrLength = array.length;
            if (arrLength > 0) {
                for (int i = 0; i < arrLength; i++) {
                    arrStr.append(innerObjectToJString(array[i], true));
                    arrStr.append(",");
                }

                arrStr.deleteCharAt(arrStr.length() - 1);
            }
        }

        if (brackets) {
            arrStr.append("]");
        }
        return arrStr.toString();
    }

    private static String innerValueToJString(Object value) {
        if (null == value) {
            return new String("\"\"");
        }

        // 数字解析
        if (Integer.class.isAssignableFrom(value.getClass())
                || Long.class.isAssignableFrom(value.getClass())
                || Float.class.isAssignableFrom(value.getClass())
                || Double.class.isAssignableFrom(value.getClass())
                || Boolean.class.isAssignableFrom(value.getClass())) {
            return String.valueOf(value);
        }
        else {
            return new String("\"").concat(String.valueOf(value)).concat("\"");
        }
    }

    private static String innerCollectionToJString(Collection obj, boolean brackets) {
        StringBuilder collectionStr = new StringBuilder("");
        if (brackets) {
            collectionStr.append("[");
        }

        if (null != obj && !((Collection) obj).isEmpty()) {
            for (Object colValue : ((Collection) obj)) {
                collectionStr.append(innerObjectToJString(colValue, true));
                collectionStr.append(",");
            }
            collectionStr.deleteCharAt(collectionStr.length() - 1);
        }

        if (brackets) {
            collectionStr.append("]");
        }
        return collectionStr.toString();
    }

    // 作为解析调用的控制函数，调用其他的函数递归解析
    private static String innerObjectToJString(Object obj, boolean brackets) {
        if (null == obj) {
            return "\"\"";
        }

        // Map 类型
        if (Map.class.isAssignableFrom(obj.getClass())) {
            return innerMapToJString((Map<String, Object>) obj, brackets);
        }
        // 集合类型解析
        else if (Collection.class.isAssignableFrom(obj.getClass())) {
            return innerCollectionToJString((Collection) obj, brackets);
        }
        // 数组类型解析
        else if (obj.getClass().isArray()) {
            return innerArrayObjectToJString(obj, brackets);
        }
        // 其他类型
        else {
            return innerValueToJString(obj);
        }
    }
}
