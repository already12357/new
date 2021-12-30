package com.zhq.util.JDBCUtil;

import com.aspose.cad.internal.p.M;
import com.aspose.pdf.Row;
import com.zhq.util.JsonUtil.JsonUtil;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于存储数据库查询下来的数据, 核心通过 HashMap 存储
 * 注意，存储单条数据, 如需扩展使用 List 集合
 * @author Eddie Zhang
 */
public class RowData extends HashMap<String, Object> {
    public String tableName;

    /**
     * 将 ResultSet, List<Map>, Map 等类转化为 RowData 类
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

            queryResult.beforeFirst();
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
            queryResult.beforeFirst();

            return rowData;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<RowData> valueOf(List<Map<String, Object>> mapList) {
        if (null == mapList) {
            return null;
        }

        try {
            List<RowData> rowData = new ArrayList<RowData>();

            for (int i = 0; i < mapList.size(); i++) {
                RowData row = new RowData();
                for (Entry<String, Object> entry : mapList.get(i).entrySet()) {
                    String columnName = entry.getKey();
                    Object columnValue = entry.getValue();
                    row.set(columnName, columnValue);
                }
                rowData.add(row);
            }

            return rowData;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static RowData valueOf(Map<String, Object> map) {
        if (null == map) {
            return null;
        }
        try {
            RowData rowData = new RowData();

            for (Entry<String, Object> paramEntry : map.entrySet()) {
                String columnName = paramEntry.getKey();
                Object columnValue = paramEntry.getValue();
                rowData.set(columnName, columnValue);
            }

            return rowData;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public void set(String column, Object value) {
        super.put(column, value);
    }

    public void setString(String column, String value) {
        super.put(column, value);
    }

    public void setInteger(String column, Integer value) {
        super.put(column, value);
    }

    public void setLong(String column, Long value) {
        super.put(column, value);
    }

    public void setBoolean(String column, Boolean value) {
        super.put(column, value);
    }

    public Object get(String column) {
        return super.get(column);
    }

    public String getString(String column) {
        return (String) super.get(column);
    }

    public Integer getInteger(String column) {
        return (Integer) super.get(column);
    }

    public Long getLong(String column) {
        return (Long) super.get(column);
    }

    public Boolean getBoolean(String column) {
        return (Boolean) super.get(column);
    }


    /**
     * 对表名的处理操作，通常在插入时使用
     */
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    /**
     * 静态方法，实现了将 RowData 插入，删除的静态方法，对外依赖
     */
//    public static void insertInto(RowData data, String tableName);
//    public static void insertBatch(List<RowData> data, String tableName);


    // 以 json 格式字符串化
    @Override
    public String toString() {
        return JsonUtil.mapToJString(this);
    }
}
