package com.zhq.util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于存储数据库查询下来的数据, 核心通过 Map 存储
 * 注意，存储单条数据
 */
public class RowData {
    private Map<String, Object> data;

    public RowData() {
        data = new HashMap<String, Object>();
    }



//    /**
//     * 将外部的结果转化为对应的内部类
//     */
//    public static RowData valueOf(ResultSet queryResult) {
//        if (null == queryResult) {
//            return null;
//        }
//
//        try {
//            RowData dataRow = new RowData();
//            ResultSetMetaData metaData = queryResult.getMetaData();
//
//            // 1. 取出对应的查询列
//
//            // 2. 取出查询列对应的信息
//
//            // 3. 以键值对的形式存储到哈希表中
//
//
//            return dataRow;
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }



    public void set(String column, Object value) {
        data.put(column, value);
    }

    public void setString(String column, String value) {
        data.put(column, value);
    }

    public void setInteger(String column, Integer value) {
        data.put(column, value);
    }

    public void setLong(String column, Long value) {
        data.put(column, value);
    }


    public Object get(String column) {
        return data.get(column);
    }

    public String getString(String column) {
        return (String) data.get(column);
    }

    public Integer getInteger(String column) {
        return (Integer) data.get(column);
    }

    public Long getLong(String column) {
        return (Long) data.get(column);
    }
}
