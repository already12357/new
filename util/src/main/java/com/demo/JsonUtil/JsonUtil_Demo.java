package com.demo.JsonUtil;

import com.zhq.util.JDBCUtil.DBConstant;
import com.zhq.util.JDBCUtil.DBUtil;
import com.zhq.util.JDBCUtil.SqlCondition;
import com.zhq.util.JsonUtil.JsonUtil;

import java.sql.ResultSet;
import java.util.*;

/**
 * JsonUtil 类的 Demo
 * @author Eddie Zhang
 */
public class JsonUtil_Demo {
    public static void main(String[] args) {
        JsonUtil_Demo instance = new JsonUtil_Demo();

        /**
         * 1. 初始化 DBUtil 类
         */
        instance.init_DBUtil();

        // 调用测试实例
        instance.demo1();
        instance.demo2();
        instance.demo3();
        instance.demo4();
        instance.demo5();
        instance.demo6();
        instance.demo7();
    }

    public void init_DBUtil() {
        DBUtil.setUrl(DBConstant.URL_MYSQL("sys"));
        DBUtil.setUsername("root");
        DBUtil.setPassword("Gepoint");
    }

    /**
     * demo : resultSetToJString
     */
    public void demo1() {
        SqlCondition selectSql = new SqlCondition();
        selectSql.select_col("*").from("course_1").where().page(2, 3);
        ResultSet resultSet = (ResultSet) selectSql.executedBy(DBUtil.getInnerDS());

        // 使用 mapToJString 将 ResultSet 对象转化为 json 字符串
        System.out.println(JsonUtil.resultSetToJString(resultSet));

        selectSql.release();
    }

    /**
     * demo : mapToJString
     */
    public void demo2() {
        Object[] testObject = new Object[4];
        testObject[2] = "2";
        testObject[3] = 4;

        Map<String, Object> mapObject = new HashMap<>();
        mapObject.put("sValue", "hello");
        mapObject.put("iValue", 3);
        mapObject.put("oaValue", testObject);
        mapObject.put("null", null);

        // 使用 mapToJString 将 Map 对象转化为 json 字符串
        System.out.println(JsonUtil.mapToJString(mapObject));
    }

    /**
     * demo : collectionToJString
     */
    public void demo3() {
        List list = new ArrayList();
        list.add(1);
        list.add("1");
        list.add(null);

        Object[] testObject = new Object[4];
        testObject[2] = "2";
        testObject[3] = 4;
        testObject[1] = list;

        Collection collection = new ArrayList();
        collection.add(testObject);
        collection.add("1");
        collection.add(null);
        collection.add(1);
        collection.add(list);

        // 使用 collectionToJString 将 Collection 集合转化为 json 字符串
        System.out.println(JsonUtil.collectionToJString(collection));
    }

    /**
     * demo : keyValueToJString
     */
    public void demo4() {
        Map<String, Object> mapObject = new HashMap<>();
        mapObject.put("sValue", "hello");
        mapObject.put("iValue", 3);
        mapObject.put("null", null);

        Object[] testObject = new Object[4];
        testObject[2] = "2";
        testObject[3] = 4;

        String key = "keyMap";
        List valueList = new ArrayList();

        valueList.add("3");
        valueList.add(3);
        valueList.add(testObject);
        valueList.add(mapObject);

        // 使用 keyValueToJString 将 key-value 键值对转化为 json 字符串
        System.out.println(JsonUtil.keyValueToJString(key, valueList));
    }

    /**
     * demo : arrayToJString
     */
    public void demo5() {
        Map<String, Object> mapObject = new HashMap<>();
        mapObject.put("sValue", "hello");
        mapObject.put("iValue", 3);
        mapObject.put("null", null);

        Object[] testObject = new Object[4];
        testObject[1] = mapObject;
        testObject[2] = "2";
        testObject[3] = 4;

        // 使用 arrayToJString 将 Object[] 对转化为 json 字符串
        System.out.println(JsonUtil.arrayToJString(testObject));
    }

    /**
     * demo : objectToJString, collectionToJString
     */
    public void demo6() {
        Map<String, Object> mapObject = new HashMap<>();
        mapObject.put("sValue", "hello");
        mapObject.put("iValue", 3);
        mapObject.put("null", null);
        
        // 使用 objectToJString 将 Object 对象对转化为 json 字符串
        System.out.println(JsonUtil.objectToJString(mapObject));


        List list = new ArrayList();
        list.add(1);
        list.add("1");
        list.add(null);
        System.out.println(JsonUtil.collectionToJString(list));
    }

    /**
     * demo : propertiesToJString
     */
    public void demo7() {
        Properties props = new Properties();

        props.setProperty("hello1", "1");
        props.setProperty("hello2", new Date().toString());
        props.setProperty("hello3", String.valueOf(true));

        // 使用 propertyToJString 将 Properties 对象对转化为 json 字符串
        System.out.println(JsonUtil.propertiesToJString(props));
    }
}
