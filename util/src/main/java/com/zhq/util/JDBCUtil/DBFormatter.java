package com.zhq.util.JDBCUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * 用于处理不同种类 SQL 语言中的 格式 和 转化 问题
 * @author Eddie Zhang
 */
public class DBFormatter {
    /**
     * 对传入的 Object 对象进行字符串的格式化
     * @param value 参数对象
     * @return
     */
    public static String formatObjToStr(Object value) {
        if (null == value) {
            return "null";
        }

        String valueStr = String.valueOf(value);

        // 判断传入的父类 value 是否为 String 类型, 如果是 String 类型，则添加双引号
        if (value.getClass().isAssignableFrom(String.class)) {
            return new String("'").concat(valueStr).concat("'");
        }

        // 判断传入的类型是否为 SqlCondition 对象, 是的话，进行子查询的转换
        if (value.getClass().isAssignableFrom(SqlCondition.class)) {
            return ((SqlCondition) value).generateSql();
        }

        return valueStr;
    }


    /**
     * 将传入的数据库查询结果, 通过返回
     * @param queryResult
     * @return
     */
    public static String formatResultSetToJson(ResultSet queryResult) {
        try {
            JSONArray jResultSet = new JSONArray();
            ResultSetMetaData metaData = queryResult.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (queryResult.next()) {
                for (int i = 0; i < columnCount; i++) {
                    JSONObject jElement = new JSONObject();
                    String columnName = metaData.getColumnLabel(i);
                    String columnValue = DBFormatter.formatObjToStr(queryResult.getObject(i));
                    jElement.put(columnName, columnValue);
                    jResultSet.add(jElement);
                }
            }

            return jResultSet.toJSONString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
