package com.zhq.util.JDBCUtil;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// SQL 语句中相关的查询条件
public class SqlCondition {
    // 操作类型 ( 增删查改 )
    private String opType;
    private HashMap<String, List<String>> eqConditionMap;

    public SqlCondition() {
        eqConditionMap = new HashMap<String, List<String>>();
    }

    /**
     * 将对应的大于条件添加到对象中
     * @param columnName
     * @param columnValue
     */
    public void gt(String columnName, String columnValue) {

    }

    /**
     * 添加对应的等于条件到对象中
     * @param columnName 等于条件的列
     * @param columnValue 等于条件的值
     * @param or 是否使用 or 拼接, 否则使用 and ( 通过前缀 * / # 区分 )
     */
    public void eq(String columnName, String columnValue, boolean or) {
        String sign = "=";
        // 解析传入的内容，转换为表达式
        String eqStr = parseEquation(columnName, columnValue, sign, or);
        addEquation(eqConditionMap, columnName, eqStr);

//        if (null == eqConditionMap.get(columnName)) {
//            List<String> eqList = new ArrayList<>();
//            eqList.add(eqStr);
//            eqConditionMap.put(columnName, eqList);
//        }
//        else {
//            eqConditionMap.get(columnName).add(eqStr);
//        }
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
        StringBuilder findSql = new StringBuilder("");
        int startIndex = 0;

        if (null != eqList && !eqList.isEmpty()) {
            for (int i = 0; i < eqList.size(); i++) {
                String equation = eqList.get(i);

                if (equation.charAt(0) == '*') {
                    equation = " or " + equation.substring(1);
                }
                else {
                    equation = " and " + equation.substring(1);
                }

                findSql.append(equation);
            }
            startIndex = findSql.indexOf("(");

            return findSql.substring(startIndex);
        }

        return "";
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

        parsedEq.append(columnName);
        parsedEq.append(sign);
        parsedEq.append("'" + columnValue + "'");

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
