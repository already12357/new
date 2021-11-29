package com.zhq.util.JDBCUtil;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// SQL 语句中相关的查询条件
public class SqlCondition {
    // 操作类型 ( 增删查改 )
    private String opType;
    private HashMap<String, List<String>> eqConditionMap;
    private HashMap<String, List<String>> gtConditionMap;
    private HashMap<String, List<String>> ltConditionMap;

    public SqlCondition() {
        ltConditionMap = new HashMap<String, List<String>>();
        gtConditionMap = new HashMap<String, List<String>>();
        eqConditionMap = new HashMap<String, List<String>>();
    }

    /**
     * 将等式条件添加到对象中 ( 大于, 小于, 等于 )
     * @param columnName 条件的列
     * @param columnValue 值
     * @param or 是否使用 or 拼接, 否则使用 and ( 通过前缀 * / # 区分 )
     */
    public void gt(String columnName, String columnValue, boolean or) {
        String sign = ">";
        String gtStr = parseEquation(columnName, columnValue, sign, or);
        addEquation(gtConditionMap, columnName, gtStr);
    }
    public void eq(String columnName, String columnValue, boolean or) {
        String sign = "=";
        // 解析传入的内容，转换为表达式
        String eqStr = parseEquation(columnName, columnValue, sign, or);
        addEquation(eqConditionMap, columnName, eqStr);
    }
    public void lt(String columnName, String columnValue, boolean or) {
        String sign = "<";
        // 解析传入的内容，转换为表达式
        String ltStr = parseEquation(columnName, columnValue, sign, or);
        addEquation(eqConditionMap, columnName, ltStr);
    }


    /**
     * 获取对应的表达式内容 ( 等式, 不等式 )
     * @param columnName 列名称
     * @return
     */
    public String getEqStr(String columnName) {
        return getEquationStr(columnName, eqConditionMap);
    }
    public String getGtStr(String columnName) {
        return getEquationStr(columnName, gtConditionMap);
    }
    public String getLtStr(String columnName) {
        return getEquationStr(columnName, ltConditionMap);
    }
    
    

    /**
     * 将传入的条件解析为对应的表达式 ( 等式，不等式  )
     * @param columnName
     * @param columnValue
     * @param or 表示是采取 or 拼接, 还是采取 and 拼接 ( 前缀区分 '*'/'#' )
     * @return
     */
    private String parseEquation(String columnName, String columnValue, String sign, boolean or) {
        StringBuilder parsedEq = new StringBuilder("");

        if (or) {
            parsedEq.append("*");
        }
        else {
            parsedEq.append("#");
        }

        parsedEq.append("(" + columnName);
        parsedEq.append(sign);
        parsedEq.append("'" + columnValue + "')");

        return parsedEq.toString();
    }

    /**
     * 将条件语句插入到条件集合中
     * @param conditionMap 需要传入的条件集合
     * @param columnName 对应的条件列名
     * @param condStr 条件语句
     */
    private void addEquation(HashMap<String, List<String>> conditionMap, String columnName, String condStr) {
        if (null == conditionMap.get(columnName)) {
            List<String> eqList = new ArrayList<>();
            eqList.add(condStr);
            conditionMap.put(columnName, eqList);
        }
        else {
            conditionMap.get(columnName).add(condStr);
        }
    }

    /**
     * 获取对应列的表达式
     * @param columnName 列名
     * @param conditionMap 用于存放条件的集合
     * @return
     */
    private String getEquationStr(String columnName, HashMap<String, List<String>> conditionMap) {
        List<String> eqList = conditionMap.get(columnName);
        StringBuilder findSql = new StringBuilder("");
        String sqlRet = "";
        int startIndex = 0;

        if (null != eqList && !eqList.isEmpty()) {
            for (int i = 0; i < eqList.size(); i++) {
                String equation = eqList.get(i);

                if (equation.charAt(0) == '*') {
                    equation = " or " + equation.substring(2, equation.length() - 1);
                }
                else {
                    equation = " and " + equation.substring(2, equation.length() - 1);
                }

                findSql.append(equation);
            }
            findSql.delete(0, 1);
            startIndex = findSql.indexOf(" ");

            return findSql.substring(startIndex);
        }

        return "";
    }


//    /**
//     * 根据当前的条件生成对应的 Sql 语句
//     * @return
//     */
//    public String generateSql() {
//        String sqlReturn = "";
//
//        if (null != eqConditionMap) {
//
//        }
//
//    }
}
