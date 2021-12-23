package com.zhq.util.JDBCUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Locale;

/**
 * 用于处理不同种类 SQL 语言中的 格式 和 转化 问题
 * @author Eddie Zhang
 */
public class DBFormatter {
    /**
     * 对传入的 Object 对象进行字符串的格式化
     * @param value 参数对象
     * @param nullStr 为空时是否显示 null 字符串
     * @param strQuot 传入对象为字符串时, 返回内容是否要添加引号
     * @return
     */
    public static String formatObjToStr(Object value, boolean nullStr, boolean strQuot) {
        if (null == value) {
            if (nullStr) {
                return "null";
            }
            else {
                return "";
            }
        }

        String valueStr = String.valueOf(value);

        // 判断传入的父类 value 是否为 String 类型, 如果是 String 类型，则添加双引号
        if (value.getClass().isAssignableFrom(String.class)) {
            if (strQuot) {
                return new String("'").concat(valueStr).concat("'");
            }
            else {
                return new String(valueStr);
            }
        }

        // 判断传入的类型是否为 SqlCondition 对象, 是的话，进行子查询的转换
        if (value.getClass().isAssignableFrom(SqlCondition.class)) {
            return ((SqlCondition) value).generateSql();
        }

        return valueStr;
    }

    public static String formatObjToStr(Object value) {
        return formatObjToStr(value, true, true);
    }


    /**
     * 对传入的 Str 字符串左右添加空格隔开
     * @param value 传入用于分隔的字符串对象
     * @param nullStr 为空时是否返回 null 字符串
     * @return
     */
    public static String gapStrWithBlank(String value, boolean nullStr) {
        if (null == value) {
            if (nullStr) {
                return "null";
            }
            else {
                return "";
            }
        }

        return new String(" ").concat(value).concat(" ");
    }

    public static String gapStrWithBlank(String value) {
        return gapStrWithBlank(value, false);
    }


    /**
     * 将传入的 JDBC 查询结果, 返回为 JSON 字符串
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


    /**
     * 根据分页信息和查询主 SQL 生成对应的分页查询 SQL
     * @param pageIndex 当前页码
     * @param pageSize 当前页大小
     * @param dbType 数据库类型
     * @param sqlContent 查询的内容集合
     */
    public static String pageQuerySql(int pageIndex, int pageSize, String dbType, String sqlContent) {
        StringBuilder frameSql = new StringBuilder("");
        String dbTypeCase = dbType.toLowerCase(Locale.ENGLISH);

        // 根据不同的操作类型，生成不同的子查询外部 SQL
        switch (dbTypeCase) {
            case DBConstant.DB_ORACLE:
                break;

            case DBConstant.DB_SQLSERVER:
                break;

            case DBConstant.DB_DB2:
                break;


            case DBConstant.DB_MYSQL:
                frameSql.append("SELECT")
                        .append(" * ")
                        .append("FROM")
                        .append(" ( ")
                        .append(sqlContent)
                        .append(" ) ")
                        .append("AS PAGE_CONTENTS")
                        .append(" LIMIT ")
                        .append(String.valueOf(pageIndex * pageSize))
                        .append(",")
                        .append(String.valueOf(pageSize));
                break;
        }

        return frameSql.toString();
    }
}
