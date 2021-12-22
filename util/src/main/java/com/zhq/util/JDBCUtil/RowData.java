package com.zhq.util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于存储数据库查询下来的数据, 核心通过 Map 存储
 * 注意，存储单条数据, 或对应表示多条扩展的数据
 */
public class RowData {
    private Map<String, Object> data;

    public RowData(Map<String, Object> data) {
        this.data = data;
    }

    public RowData() {
        this(new HashMap<String, Object>());
    }

    /**
     * 将原生数据库查询的结果转化为 RowData 类
     * @param queryResult
     * @return
     */
    public static List<RowData> valueOf(ResultSet queryResult) {
        if (null == queryResult) {
            return null;
        }

        List<RowData> rowData = new ArrayList<RowData>();

        try {
            ResultSetMetaData metaData = queryResult.getMetaData();

            queryResult.first();
            // 遍历行
            while (queryResult.next()) {
                RowData row = new RowData();
                // 遍历每行中的每列
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnLabel(i);
                    Object columnValue = queryResult.getObject(i);
                    row.set(columnName, columnValue);
                }

                rowData.add(row);
            }

            return rowData;
        }
        catch (Exception e) {
            e.printStackTrace();
            return rowData;
        }

    }


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
