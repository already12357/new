package com.zhq.util.JDBCUtil;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// SQL 语句中相关的查询条件
public class SqlCondition {
    // 操作类型 ( 增删查改 )
    private String opType;
    private HashMap<String, List<String>> eqConditionMap;

    public SqlCondition() {
        eqConditionMap = new HashMap<String, List<String>>();
    }

    /**
     * 添加对应的等于条件到对象中
     * @param columnName 等于条件的列
     * @param columnValue 等于条件的值
     * @param or 是否使用 or 拼接, 否则使用 and ( 通过前缀 * / # 区分 )
     */
    public void eq(String columnName, String columnValue, boolean or) {
        // 解析传入的内容，转换为表达式
        String eqStr = parseEq(columnName, columnValue, or);

        if (null == eqConditionMap.get(columnName)) {
            List<String> eqList = new ArrayList<>();
            eqConditionMap.put(columnName, eqList);
        }
        else {
            eqConditionMap.get(columnName).add(eqStr);
        }
    }

    /**
     * 获取查询列名称的等式判断条件
     * @param columnName 查询等式的列名称
     * @return
     */
    public List<String> getEqList(String columnName) {
        return eqConditionMap.get(columnName);
    }


    /**
     * 获取对应列的等式表达式
     * @param columnName 列名
     * @return
     */
    public String getEqStr(String columnName) {
        List<String> eqList = eqConditionMap.get(columnName);

        if (null != eqList && !eqList.isEmpty()) {

        }
    }


    /**
     * 将传入的条件解析为对应的表达式
     * @param columnName
     * @param columnValue
     * @param or 表示是采取 or 拼接, 还是采取 and 拼接 ( 前缀区分 '*'/'#' )
     * @return
     */
    private String parseEq(String columnName, String columnValue, boolean or) {
        StringBuilder parsedEq = new StringBuilder("");

        if (or) {
            parsedEq.append("*");
        }
        else {
            parsedEq.append("#");
        }

        parsedEq.append("(" + columnName);
        parsedEq.append("=");
        parsedEq.append("'" + columnValue + "')");

        return parsedEq.toString();
    }


    /**
     * 根据当前的条件生成对应的 Sql 语句
     * @return
     */
    public String generateSql() {
        String sqlReturn = "";

        if (null != eqConditionMap) {

        }

    }
}
