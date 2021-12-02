package com.zhq.util.JDBCUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// SQL 语句中相关的查询条件
public class SqlCondition {
    // 操作类型 ( 增删查改 )
    private String opType;
    // where 部分的所有条件内容集合
    private List<HashMap<String, List<String>>> whereConditionList;


    // '>', '<', '=', between, in条件存储在 HashMap 中
    // 根据 列名 --> 列名对应的表达式集合 存储
    private HashMap<String, List<String>> eqConditionMap;
    private HashMap<String, List<String>> gtConditionMap;
    private HashMap<String, List<String>> ltConditionMap;
    private HashMap<String, List<String>> betweenConditionMap;
    private HashMap<String, List<String>> inConditionMap;
    // 操作列对应的值
    // 操作列
    private List<String> opColumns;
    // 表格名称
    private List<String> opTables;

    public SqlCondition() {
        // 优化, 使用 get + 懒加载
        ltConditionMap = new HashMap<String, List<String>>();
        gtConditionMap = new HashMap<String, List<String>>();
        eqConditionMap = new HashMap<String, List<String>>();
        betweenConditionMap = new HashMap<String, List<String>>();
        inConditionMap = new HashMap<String, List<String>>();
        whereConditionList = new ArrayList<HashMap<String, List<String>>>();

        opColumns = new ArrayList<>();
        opTables = new ArrayList<>();

        opType = ConstUtil.OP_SELECT;
        whereConditionList.add(ltConditionMap);
        whereConditionList.add(gtConditionMap);
        whereConditionList.add(eqConditionMap);
        whereConditionList.add(betweenConditionMap);
        whereConditionList.add(inConditionMap);
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }
    public String getOpType() {
        return opType;
    }

    /**
     * 将条件添加到条件存储对象中 ( 大于, 小于, 等于, between, in )
     * @param columnName 条件的列
     * @param columnValue 值
     * @param or 是否使用 or 拼接, 否则使用 and ( 通过前缀 * / # 区分 )
     */
    public void gt(String columnName, String columnValue, boolean or) {
        String sign = " > ";
        String gtStr = parseEquation(columnName, columnValue, sign, or);
        addConditionStr(gtConditionMap, columnName, gtStr);
    }
    public void eq(String columnName, String columnValue, boolean or) {
        String sign = " = ";
        // 解析传入的内容，转换为表达式
        String eqStr = parseEquation(columnName, columnValue, sign, or);
        addConditionStr(eqConditionMap, columnName, eqStr);
    }
    public void lt(String columnName, String columnValue, boolean or) {
        String sign = " < ";
        // 解析传入的内容，转换为表达式
        String ltStr = parseEquation(columnName, columnValue, sign, or);
        addConditionStr(eqConditionMap, columnName, ltStr);
    }
    public void between(String columnName, String bottom, String top, boolean or) {
        // 将传入内容解析为字符串
        String betweenStr = parseBetween(columnName, bottom, top, or);
        // 将解析后的 Between 语句加入到集合中
        addConditionStr(betweenConditionMap, columnName, betweenStr);
    }
    public void in(String columnName, List<String> rangeList, boolean or) {
        String inStr = parseIn(columnName, rangeList, or);
        addConditionStr(inConditionMap, columnName, inStr);
    }


    /**
     * 添加操作所需的列 或 表
     * @param columnName 列名称 / 表名称
     */
    public void onColumn(String columnName, String...columnNames) {
        if (!opColumns.contains(columnName)) {
            opColumns.add(columnName);
        }

        for (String extraColumn : columnNames) {
            if (!opColumns.contains(columnName)) {
                opColumns.add(columnName);
            }
        }
    }
    public void inTables(String tableName, String...tableNames) {
        if (!opTables.contains(tableName)) {
            opTables.add(tableName);
        }

        for (String extraTable : tableNames) {
            if (!opTables.contains(extraTable)) {
                opTables.add(extraTable);
            }
        }
    }


    public void join(String type) {

    }


    /**
     * 获取对应的表达式内容 ( 等式, 不等式 )
     * @param columnName 列名称
     * @return
     */
    public String getEqStr(String columnName) {
        return getConditionStr(columnName, eqConditionMap);
    }
    public String getGtStr(String columnName) {
        return getConditionStr(columnName, gtConditionMap);
    }
    public String getLtStr(String columnName) {
        return getConditionStr(columnName, ltConditionMap);
    }
    public String getBetweenStr(String columnName) {
        return getConditionStr(columnName, betweenConditionMap);
    }
    public String getInStr(String columnName) {
        return getConditionStr(columnName, inConditionMap);
    }


    /**
     * 将传入的条件解析为对应的表达式 ( 等式，不等式  )
     * @param columnName 列名称
     * @param columnValue 列值
     * @param or 表示是采取 or 拼接, 还是采取 and 拼接 ( 前缀区分 '*'/'#' )
     * @return 返回处理初次处理后的 SQL 语句
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
        parsedEq.append(columnValue + ")");

        return parsedEq.toString();
    }
    private String parseBetween(String columnName, String bottom, String top, boolean or) {
        StringBuilder parsedBetween = new StringBuilder("");

        if (or) {
            parsedBetween.append("*");
        }
        else {
            parsedBetween.append("#");
        }

        parsedBetween.append("(");
        parsedBetween.append(columnName + " BETWEEN " + bottom + " AND " + top);
        parsedBetween.append(")");

        return parsedBetween.toString();
    }
    private String parseIn(String columnName, List<String> rangeList, boolean or) {
        StringBuilder parsedBetween = new StringBuilder("");

        if (or) {
            parsedBetween.append("*");
        }
        else {
            parsedBetween.append("#");
        }

        parsedBetween.append("(");
        parsedBetween.append(columnName + " IN (");
        for (String range : rangeList) {
            parsedBetween.append("'" + range + "',");
        }
        parsedBetween.append(")");
        parsedBetween.append(")");
        parsedBetween.deleteCharAt(parsedBetween.length() - 3);

        return parsedBetween.toString();
    }

    /**
     * 将处理的条件语句插入到条件集合中
     * @param conditionMap 需要传入的条件集合
     * @param columnName 对应的条件列名
     * @param condStr 条件语句
     */
    private void addConditionStr(HashMap<String, List<String>> conditionMap, String columnName, String condStr) {
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
     * 获取对应列的条件内容
     * @param columnName 查询的列名
     * @param conditionMap 用于存放条件语句的集合
     * @return
     */
    private String getConditionStr(String columnName, HashMap<String, List<String>> conditionMap) {
        List<String> condStrList = conditionMap.get(columnName);
        StringBuilder findSql = new StringBuilder("");
        String sqlRet = "";
        int startIndex = 0;

        if (null != condStrList && !condStrList.isEmpty()) {
            for (int i = 0; i < condStrList.size(); i++) {
                String equation = condStrList.get(i);

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


    /**
     * 根据内部的配置信息生成对应的 Sql 语句, 通过操作类型分类 ( 增删查改 )
     * @return
     */
    public String generateSql() {
        switch (opType) {
            case ConstUtil.OP_UPDATE:
                return generateUpdateSql();

            case ConstUtil.OP_INSERT:
                return generateInsertSql();

            case ConstUtil.OP_DELETE:
                return generateDeleteSql();

            default:
                return generateSelectSql();
        }
    }

    /**
     * 根据内部配置生成不同的 Sql 语句
     * @return
     */
    private String generateInsertSql() {
        StringBuilder insertBuilder = new StringBuilder("");
        insertBuilder.append(opType);



        return insertBuilder.toString();
    }
    private String generateSelectSql() {
        StringBuilder selectBuilder = new StringBuilder("");

        // 逐次拼接对应的内容
        selectBuilder.append(opType);
        selectBuilder.append(" ");
        selectBuilder.append(columnsInSql());
        selectBuilder.append(" ");
        selectBuilder.append(fromInSql());
        selectBuilder.append(" ");
        selectBuilder.append(whereInSql());

        return selectBuilder.toString();
    }
    private String generateDeleteSql() {
        StringBuilder deleteBuilder = new StringBuilder("");


        return deleteBuilder.toString();
    }
    private String generateUpdateSql() {
        StringBuilder updateBuilder = new StringBuilder("");

        updateBuilder.append(opType);

        return updateBuilder.toString();
    }



    public String columnsInSql() {
        StringBuilder columnsStr = new StringBuilder("");

        for (String column : opColumns) {
            columnsStr.append(column + ",");
        }
        columnsStr.deleteCharAt(columnsStr.length() - 1);

        return columnsStr.toString();
    }

    private String fromInSql() {
        StringBuilder fromStr = new StringBuilder("");

        fromStr.append("from ");

        for (String table : opTables) {
            fromStr.append(table + ",");
        }
        fromStr.deleteCharAt(fromStr.length() - 1);

        return fromStr.toString();
    }

    public String whereInSql() {
        StringBuilder whereStr = new StringBuilder("where");

        for (HashMap<String, List<String>> part : whereConditionList) {
            for (Map.Entry<String, List<String>> entry : part.entrySet()) {
                String columnName = entry.getKey();
                whereStr.append(getConditionStr(columnName, part));
            }
        }

        return whereStr.toString();
    }

}
